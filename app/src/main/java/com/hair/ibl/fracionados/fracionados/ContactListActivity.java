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

import com.hair.ibl.fracionados.fracionados.Model.Contact.List.Form;
import com.hair.ibl.fracionados.fracionados.Model.Content.List.Content;
import com.hair.ibl.fracionados.fracionados.Model.Content.Show.ContentShow;
import com.hair.ibl.fracionados.fracionados.Service.RetrofitService;
import com.hair.ibl.fracionados.fracionados.Service.ServiceGenerator;
import com.hair.ibl.fracionados.fracionados.Util.ExtractHTMLUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListActivity extends AppCompatActivity {
    ListView lvContact;
    ContentShow content_show;
    ProgressDialog dialog;
    ArrayList<String> slugs = new ArrayList<>();
    ArrayList<Form> forms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        dialog = ProgressDialog.show(ContactListActivity.this, "Aguarde", "Carregando a  dados...");

        lvContact = (ListView) findViewById(R.id.lvContact);
        requestContact("testes");

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ContactListActivity.this, ContactShowActivity.class);
                intent.putExtra("slug", slugs.get(position));
                startActivity(intent);

            }
        });
    }

    private void requestContact(String slug) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<ContentShow> call = service.getContent(slug);

        call.enqueue(new Callback<ContentShow>() {
            @Override
            public void onResponse(Call<ContentShow> call, Response<ContentShow> response) {

                if (response.isSuccessful()) {

                    content_show = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (content_show != null) {

                        ExtractHTMLUtil extractHTMLUtil = new ExtractHTMLUtil();
                        forms = extractHTMLUtil.splitForms(content_show.getData().getContent().getContent());

                        List<String> names = new ArrayList<String>();
                        for (Form form : forms) {
                            names.add(form.getTitle());
                            slugs.add(form.getSlug());
                        }
                        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(ContactListActivity.this, android.R.layout.simple_list_item_1, names);
                        lvContact.setAdapter(namesAdapter);
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
