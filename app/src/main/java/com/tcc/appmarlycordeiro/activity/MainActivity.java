package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.tcc.appmarlycordeiro.R;
import com.tcc.appmarlycordeiro.database.ConexaoLogin;

public class MainActivity extends Activity {

    private Button btPacientes, btConsultas, btEventos, btLogout;
    private TextView currentUser;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        btPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PacientesActivity.class));
            }
        });

        btConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConsultasActivity.class));
            }
        });

        btEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventosActivity.class));
            }
        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexaoLogin.logOut();
                finish();
            }
        });
    }

    private void inicializarComponentes() {
        btPacientes = findViewById(R.id.main_bt_pacientes_id);
        btConsultas = findViewById(R.id.main_bt_consultas_id);
        btEventos = findViewById(R.id.main_bt_eventos_id);
        btLogout = findViewById(R.id.main_bt_logout_id);
        currentUser = findViewById(R.id.main_txt_user_id);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = ConexaoLogin.getFirebaseAuth();
        user = ConexaoLogin.getFirebaseUser();
        verificaUser();
    }

    private void verificaUser() {
        if (user == null){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }else{
            currentUser.setText(user.getEmail());
        }
    }
}

