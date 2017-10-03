package com.hair.ibl.fracionados.fracionados.Model.Blog.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hair.ibl.fracionados.fracionados.BlogListActivity;
import com.hair.ibl.fracionados.fracionados.Model.Blog.List.Post;
import com.hair.ibl.fracionados.fracionados.R;
import com.hair.ibl.fracionados.fracionados.Util.ExtractHTMLUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * PostAdapter
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class PostAdapter extends BaseAdapter {
    private final List<Post> posts;
    private final Activity act;
    private ImageView imagem;

    public PostAdapter(ArrayList<Post> posts, Activity act) {
        this.posts = posts;
        this.act = act;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.post_item, parent, false);
        Post post = posts.get(position);

        //pegando as referências das Views
        TextView nome = (TextView) view.findViewById(R.id.lista_curso_personalizada_nome);
        TextView descricao = (TextView) view.findViewById(R.id.lista_curso_personalizada_descricao);
        imagem = (ImageView) view.findViewById(R.id.lista_curso_personalizada_imagem);

        //populando as Views
        nome.setText(post.getTitle());

        ExtractHTMLUtil extractHTMLUtil = new ExtractHTMLUtil();
        descricao.setText(extractHTMLUtil.replaceAcutesHTML(post.getDescription().replaceAll("<.*?>","")));
        new DownloadImagemAsyncTask().execute(post.getImage_path());

        return view;
    }

    private class DownloadImagemAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String urlString = "http://assets.izap.com.br/multifracionados.com.br/plus/images?src="+params[0]+"&mode=max&width=480&height=480";

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
                imagem.setImageBitmap(result);
            } else {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(act).
                                setTitle("Erro").
                                setMessage("Não foi possivel carregar imagem, tente novamente mais tarde!").
                                setPositiveButton("OK", null);
                builder.create().show();
            }
        }
    }
}
