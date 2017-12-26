package com.hair.ibl.fracionados.fracionados;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hair.ibl.fracionados.fracionados.Model.Content.List.ContentList;
import com.hair.ibl.fracionados.fracionados.Model.Content.List.Content;
import com.hair.ibl.fracionados.fracionados.Service.RetrofitService;
import com.hair.ibl.fracionados.fracionados.Service.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ContentListActivity
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class ContentListActivity extends AppCompatActivity {
    ListView lvContents;
    ContentList content_data;
    ProgressDialog dialog;
    ArrayList<String> ignore;
    ArrayList<Content> contents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list);

        ignore = new ArrayList<>();
        ignore.add("categorias-pagina-inicial");
        ignore.add("testes");

        dialog = ProgressDialog.show(ContentListActivity.this, "Aguarde", "Carregando a  dados...");

        lvContents = (ListView) findViewById(R.id.lvContents);
        requestContents();

        lvContents.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ContentListActivity.this, ContentShowActivity.class);
                intent.putExtra("slug", contents.get(position).getSlug());
                startActivity(intent);

            }
        });
    }

    private void requestContents() {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<ContentList> call = service.getAllContents();

        call.enqueue(new Callback<ContentList>() {
            @Override
            public void onResponse(Call<ContentList> call, Response<ContentList> response) {

                if (response.isSuccessful()) {

                    content_data = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (content_data != null) {
                        List<String> names = new ArrayList<String>();
                        for (Content content_item : content_data.getData().getContent_item()) {
                            if(ignore.indexOf(content_item.getSlug()) < 0){
                                names.add(content_item.getTitle());
                                contents.add(content_item);
                            }
                        }
                        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(ContentListActivity.this, android.R.layout.simple_list_item_1, names);
                        lvContents.setAdapter(namesAdapter);
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
            public void onFailure(Call<ContentList> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
