package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tcc.appmarlycordeiro.R;

public class ConsultasActivity extends Activity {

    private FloatingActionButton fbtCadastrarConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        inicializarComponentes();

        fbtCadastrarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConsultasActivity.this, CadastrarConsultasActivity.class);
                startActivity(i);
            }
        });

    }

    private void inicializarComponentes() {
        fbtCadastrarConsulta = (FloatingActionButton) findViewById(R.id.consultas_fbotao_id);
    }
}
