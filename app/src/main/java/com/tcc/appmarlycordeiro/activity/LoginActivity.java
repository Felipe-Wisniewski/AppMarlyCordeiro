package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.tcc.appmarlycordeiro.R;
import com.tcc.appmarlycordeiro.database.ConexaoLogin;

public class LoginActivity extends Activity {

    private EditText loginEmail, loginSenha;
    private Button loginBotao;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarComponentes();

        loginBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String senha = loginSenha.getText().toString().trim();
                login(email, senha);
            }
        });
    }

    private void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                        }else{
                            alert("email ou senha incorretos");
                        }
                    }
                });
    }

    private void alert(String alert) {
        Toast.makeText(LoginActivity.this, alert, Toast.LENGTH_LONG).show();
    }

    private void inicializarComponentes() {
        loginEmail = (EditText) findViewById(R.id.login_email_id);
        loginSenha = (EditText) findViewById(R.id.login_senha_id);
        loginBotao = (Button) findViewById(R.id.login_botao_id);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = ConexaoLogin.getFirebaseAuth();
    }
}
