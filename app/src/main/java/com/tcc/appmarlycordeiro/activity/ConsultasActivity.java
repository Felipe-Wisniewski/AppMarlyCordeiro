package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
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
import com.tcc.appmarlycordeiro.business.Consulta;
import com.tcc.appmarlycordeiro.database.ConexaoBanco;
import com.tcc.appmarlycordeiro.utility.ConsultasAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConsultasActivity extends Activity {

    private SearchView pesquisarConsultas;
    private RecyclerView listaRecyConsultas;
    private FloatingActionButton fbtCadastrarConsulta;

    private ConsultasAdapter consultasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        inicializarComponentes();
        exibirListaConsultas();

        fbtCadastrarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConsultasActivity.this, CadastrarConsultasActivity.class);
                startActivity(i);
            }
        });

    }

    private void inicializarComponentes() {
        pesquisarConsultas = findViewById(R.id.eventos_search_id);
        listaRecyConsultas = findViewById(R.id.consultas_recycle_id);
        fbtCadastrarConsulta = findViewById(R.id.consultas_fbotao_id);
    }

    private void exibirListaConsultas() {
        ConexaoBanco.inicializarConexaoBanco();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaRecyConsultas.setLayoutManager(linearLayoutManager);

        Query query;
        query = ConexaoBanco.getConsultasBanco();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Consulta> listaConsultas = new ArrayList<>();

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Consulta c = objSnapshot.getValue(Consulta.class);
                    listaConsultas.add(c);
                }
                consultasAdapter = new ConsultasAdapter(listaConsultas);
                listaRecyConsultas.setAdapter(consultasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
