package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tcc.appmarlycordeiro.R;
import com.tcc.appmarlycordeiro.business.Paciente;
import com.tcc.appmarlycordeiro.database.ConexaoBanco;
import com.tcc.appmarlycordeiro.utility.PacientesAdapter;

import java.util.ArrayList;
import java.util.List;

public class PacientesActivity extends Activity {

    private SearchView pesquisarPacientes;
    private RecyclerView listaRecyPacientes;
    private FloatingActionButton fBotaoCadastrarPacientes;

    private PacientesAdapter pacientesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        inicializarComponentes();

        exibirListaPacientes();

        fBotaoCadastrarPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PacientesActivity.this, CadastrarPacientesActivity.class);
                startActivity(i);
            }
        });
    }


    private void inicializarComponentes() {
        pesquisarPacientes = (SearchView) findViewById(R.id.eventos_search_id);
        listaRecyPacientes = (RecyclerView) findViewById(R.id.pacientes_recycle_id);
        fBotaoCadastrarPacientes = (FloatingActionButton) findViewById(R.id.pacientes_fbotao_id);
    }

    private void exibirListaPacientes() {
        ConexaoBanco.inicializarConexaoBanco();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaRecyPacientes.setLayoutManager(linearLayoutManager);

        Query query;
        query = ConexaoBanco.getPacientesBanco();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Paciente> listaPacientes = new ArrayList<>();

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Paciente p = objSnapshot.getValue(Paciente.class);
                    listaPacientes.add(p);
                }
                pacientesAdapter = new PacientesAdapter(listaPacientes);
                listaRecyPacientes.setAdapter(pacientesAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
