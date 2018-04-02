package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;

import com.tcc.appmarlycordeiro.R;

public class EventosActivity extends Activity {

    private SearchView pesquisarEventos;
    private RecyclerView listaRecyEventos;
    private FloatingActionButton fbtCadastrarEventos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        iniciarComponentes();
        exibirListaEventos();

        fbtCadastrarEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventosActivity.this, CadastrarEventosActivity.class);
                startActivity(i);
            }
        });
    }

    private void iniciarComponentes() {
        pesquisarEventos = findViewById(R.id.eventos_search_id);
        listaRecyEventos = findViewById(R.id.eventos_recycle_id);
        fbtCadastrarEventos = findViewById(R.id.eventos_fbotao_id);
    }

    private void exibirListaEventos() {

    }

}
