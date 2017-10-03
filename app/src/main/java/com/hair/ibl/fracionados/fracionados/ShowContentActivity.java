package com.hair.ibl.fracionados.fracionados;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.bluejamesbond.text.DocumentView;
import com.hair.ibl.fracionados.fracionados.Model.Content.Show.ContentShow;
import com.hair.ibl.fracionados.fracionados.Service.RetrofitService;
import com.hair.ibl.fracionados.fracionados.Service.ServiceGenerator;
import com.hair.ibl.fracionados.fracionados.Util.ExtractHTMLUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowContentActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvContent;
    DocumentView dvContent;
    ContentShow content_show;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);

        dialog = ProgressDialog.show(ShowContentActivity.this, "Aguarde", "Carregando a  dados...");

        tvTitle = (TextView) findViewById(R.id.tvTitle);
//        tvContent = (TextView) findViewById(R.id.tvContent);
        dvContent = (DocumentView) findViewById(R.id.dvContent);

        Intent intent = getIntent();
        String slug = intent.getStringExtra("slug");
        requestContent(slug);

    }

    private void requestContent(String slug) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<ContentShow> call = service.getContent(slug);

        call.enqueue(new Callback<ContentShow>() {
            @Override
            public void onResponse(Call<ContentShow> call, Response<ContentShow> response) {

                if (response.isSuccessful()) {

                    content_show = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (content_show != null) {
                        tvTitle.setText(content_show.getData().getContent().getTitle());

                        ExtractHTMLUtil extractHTMLUtil = new ExtractHTMLUtil();
                        dvContent.setText(extractHTMLUtil.replaceAcutesHTML(content_show.getData().getContent().getContent().replaceAll("<.*?>","")));


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
            public void onFailure(Call<ContentShow> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
