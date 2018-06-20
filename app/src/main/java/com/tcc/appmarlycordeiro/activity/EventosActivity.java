package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tcc.appmarlycordeiro.R;
import com.tcc.appmarlycordeiro.business.Evento;
import com.tcc.appmarlycordeiro.database.ConexaoBanco;
import com.tcc.appmarlycordeiro.utility.EventosAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventosActivity extends Activity {

    private SearchView pesquisarEventos;
    private RecyclerView listaRecyEventos;
    private FloatingActionButton fbtCadastrarEventos;
    private EventosAdapter eventosAdapter;

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
        pesquisarEventos = findViewById(R.id.pacientes_search_id);
        listaRecyEventos = findViewById(R.id.eventos_recycle_id);
        fbtCadastrarEventos = findViewById(R.id.eventos_fbotao_id);
    }

    private void exibirListaEventos() {
        ConexaoBanco.inicializarConexaoBanco();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaRecyEventos.setLayoutManager(linearLayoutManager);

        Query query;
        query = ConexaoBanco.getEventosBanco();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Evento> listaEventos = new ArrayList<>();

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Evento e = objSnapshot.getValue(Evento.class);
                    listaEventos.add(e);
                }
                eventosAdapter = new EventosAdapter(listaEventos, EventosActivity.this);
                listaRecyEventos.setAdapter(eventosAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
