package com.hair.ibl.fracionados.fracionados;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hair.ibl.fracionados.fracionados.Model.Blog.Adapter.PostAdapter;
import com.hair.ibl.fracionados.fracionados.Model.Blog.List.BlogList;
import com.hair.ibl.fracionados.fracionados.Model.Blog.List.Post;
import com.hair.ibl.fracionados.fracionados.Service.RetrofitService;
import com.hair.ibl.fracionados.fracionados.Service.ServiceGenerator;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * BlogListActivity
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class BlogListActivity extends AppCompatActivity {
    ListView lvPosts;
    BlogList blog_data;
    ProgressDialog dialog;
    ArrayList<String> ignore;
    ArrayList<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_list);

        dialog = ProgressDialog.show(BlogListActivity.this, "Aguarde", "Carregando a  dados...");

        lvPosts = (ListView) findViewById(R.id.lvPosts);
        requestPosts();

        lvPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(BlogListActivity.this, ShowContentActivity.class);
                intent.putExtra("slug", posts.get(position).getSlug());
                startActivity(intent);

            }
        });
    }

    private void requestPosts() {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<BlogList> call = service.getAllPosts();

        call.enqueue(new Callback<BlogList>() {
            @Override
            public void onResponse(Call<BlogList> call, Response<BlogList> response) {

                if (response.isSuccessful()) {

                    blog_data = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (blog_data != null) {
                        PostAdapter adapter = new PostAdapter(blog_data.getData().getPosts(), BlogListActivity.this);

                        lvPosts.setAdapter(adapter);
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
            public void onFailure(Call<BlogList> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
