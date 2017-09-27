package com.hair.ibl.fracionados.fracionados;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/**
 * TelaInicialActivity
 *
 * @author Ilgner Fagundes <ilgner.fagundes@multifracionados.com.br>
 * @version 1.0
 */
public class TelaInicialActivity extends AppCompatActivity {
    Button btInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        ActionBar actionbar = getActionBar();

        //Setting up Action bar color using # color code.
        if (actionbar != null) {
            actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00cfaa")));
        }

        btInformation = (Button) findViewById(R.id.btInformation);

        btInformation.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaInicialActivity.this,ContentListActivity.class);
                startActivity(intent);
            }
        });
    }
}
