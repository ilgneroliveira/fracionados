package com.hair.ibl.fracionados.fracionados;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluejamesbond.text.DocumentView;
import com.hair.ibl.fracionados.fracionados.Model.Blog.Show.PostShow;
import com.hair.ibl.fracionados.fracionados.Service.RetrofitService;
import com.hair.ibl.fracionados.fracionados.Service.ServiceGenerator;
import com.hair.ibl.fracionados.fracionados.Util.ExtractHTMLUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostShowActivity extends AppCompatActivity {

    TextView tvTitle;
    private ImageView image;
    DocumentView dvContent;
    PostShow post_show;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_show);

        dialog = ProgressDialog.show(PostShowActivity.this, "Aguarde", "Carregando a  dados...");

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        dvContent = (DocumentView) findViewById(R.id.dvContent);
        image = (ImageView) findViewById(R.id.ivImage);

        Intent intent = getIntent();
        String slug = intent.getStringExtra("slug");
        requestContent(slug);
    }

    private void requestContent(String slug) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<PostShow> call = service.getPost(slug);

        call.enqueue(new Callback<PostShow>() {
            @Override
            public void onResponse(Call<PostShow> call, Response<PostShow> response) {

                if (response.isSuccessful()) {

                    post_show = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (post_show != null) {
                        tvTitle.setText(post_show.getData().getPost().getTitle());

                        ExtractHTMLUtil extractHTMLUtil = new ExtractHTMLUtil();
                        dvContent.setText(extractHTMLUtil.replaceAcutesHTML(post_show.getData().getPost().getContent().replaceAll("<.*?>", "")));

                        new PostShowActivity.DownloadImagemAsyncTask().execute(post_show.getData().getPost().getImage_path());

                        dialog.dismiss();
                    } else {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                } else {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                    finish();
                }

            }

            @Override
            public void onFailure(Call<PostShow> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private class DownloadImagemAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String urlString = "http://assets.izap.com.br/multifracionados.com.br/plus/images?src=" + params[0] + "&mode=max&width=1000&height=1000";
            try {
                URL url = new URL(urlString);
                HttpURLConnection conexao = (HttpURLConnection)
                        url.openConnection();
                conexao.setRequestMethod("GET");
                conexao.setDoInput(true);
                conexao.connect();

                InputStream is = conexao.getInputStream();
                return BitmapFactory.decodeStream(is);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                image.setImageBitmap(result);
            } else {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(PostShowActivity.this).
                                setTitle("Erro").
                                setMessage("Não foi possivel carregar a imagem, tente novamente mais tarde!").
                                setPositiveButton("OK", null);
                builder.create().show();
            }
        }
    }
}
