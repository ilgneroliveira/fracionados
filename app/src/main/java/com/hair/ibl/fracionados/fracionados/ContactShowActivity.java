package com.hair.ibl.fracionados.fracionados;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bluejamesbond.text.DocumentView;
import com.hair.ibl.fracionados.fracionados.Model.Blog.Show.PostShow;
import com.hair.ibl.fracionados.fracionados.Model.Contact.Show.ContactShow;
import com.hair.ibl.fracionados.fracionados.Model.Contact.Show.Element;
import com.hair.ibl.fracionados.fracionados.Model.Contact.Show.Input;
import com.hair.ibl.fracionados.fracionados.Service.RetrofitService;
import com.hair.ibl.fracionados.fracionados.Service.ServiceGenerator;
import com.hair.ibl.fracionados.fracionados.Util.ExtractHTMLUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactShowActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvDescription;
    DocumentView dvContent;
    ContactShow contact_show;
    PostShow post_show;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_show);

        dialog = ProgressDialog.show(ContactShowActivity.this, "Aguarde", "Carregando a  dados...");

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        dvContent = (DocumentView) findViewById(R.id.dvContent);

        Intent intent = getIntent();
        String slug = intent.getStringExtra("slug");
        requestContact(slug);
    }

    private void requestContact(String slug) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<ContactShow> call = service.getContact(slug);

        call.enqueue(new Callback<ContactShow>() {
            @Override
            public void onResponse(Call<ContactShow> call, Response<ContactShow> response) {

                if (response.isSuccessful()) {

                    contact_show = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (contact_show != null) {
                        tvTitle.setText(contact_show.getData().getForm().getTitle());

                        ExtractHTMLUtil extractHTMLUtil = new ExtractHTMLUtil();
                        dvContent.setText(extractHTMLUtil.replaceAcutesHTML(contact_show.getData().getForm().getDescription().replaceAll("<.*?>", "")));
                        LinearLayout linear = (LinearLayout) findViewById(R.id.llDinamic);
                        for (Element element : contact_show.getData().getForm().getElements()) {
                            switch (element.getType()){
                                case "radio":
                                    RadioGroup group_radio = new RadioGroup(ContactShowActivity.this); //Usar this porque está trabalhando dentro da activity RadioButton radio = new RadioButton(this);

                                    TextView radio_title = new TextView(ContactShowActivity.this);
                                    radio_title.setText(element.getTitle());

                                    if(element.getItems() instanceof ArrayList){
                                        List<Object> inputs = Collections.singletonList(element.getItems());
                                    }else{

                                    }

                                    for (Input item : (ArrayList<Input>)element.getItems()) {

                                    }

//                                    RadioButton radio_y = new RadioButton(ContactShowActivity.this);
//                                    radio_y.setText("Sim");
//
//                                    RadioButton radio_n = new RadioButton(ContactShowActivity.this);
//                                    radio_n.setText("Não");

                                    group_radio.addView(radio_title);
//                                    group_radio.addView(radio_y);
//                                    group_radio.addView(radio_n);

                                    linear.addView(group_radio);
                                    break;
                                case "radio_yn":
                                    RadioGroup group = new RadioGroup(ContactShowActivity.this); //Usar this porque está trabalhando dentro da activity RadioButton radio = new RadioButton(this);

                                    TextView radio_yn_title = new TextView(ContactShowActivity.this);
                                    radio_yn_title.setText(element.getTitle());

                                    RadioButton radio_y = new RadioButton(ContactShowActivity.this);
                                    radio_y.setText("Sim");

                                    RadioButton radio_n = new RadioButton(ContactShowActivity.this);
                                    radio_n.setText("Não");

                                    group.addView(radio_yn_title);
                                    group.addView(radio_y);
                                    group.addView(radio_n);

                                    linear.addView(group);
                                    break;
                                case "checkbox":
                                    break;
                                case "text_box":
                                    break;
                                case "email":
                                    break;
                                case "submit":
                                    break;
                            }
                        }

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
            public void onFailure(Call<ContactShow> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
