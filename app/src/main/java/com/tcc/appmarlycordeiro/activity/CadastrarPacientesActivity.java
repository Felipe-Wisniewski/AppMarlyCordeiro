package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.UUID;

import com.tcc.appmarlycordeiro.business.Paciente;

import com.tcc.appmarlycordeiro.R;
import com.tcc.appmarlycordeiro.database.ConexaoBanco;

public class CadastrarPacientesActivity extends Activity {

    private EditText txtNome, txtEmail, txtTelefone, txtEndereco, txtCidade, txtObservacao;
    private RadioGroup radioGroup;
    private RadioButton radioSexo;
    private Button btCadastrarCli;
    private Spinner spnEstado;
    private String[] estados = {"Acre","Alagoas","Amapá","Amazonas","Bahia","Ceará","Distrito Federal","Espírito Santo","Goiás","Maranhão",
            "Mato Grosso","Mato Grosso do Sul","Minas Gerais","Paraná","Paraíba","Pará","Pernambuco","Piauí",
            "Rio de Janeiro","Rio Grande do Norte","Rio Grande do Sul","Rondônia","Roraíma","São Paulo","Santa Catarina",
            "Sergipe","Tocantins"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pacientes);

        inicializarComponentes();

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(CadastrarPacientesActivity.this, android.R.layout.simple_list_item_1, estados);
        spnEstado.setAdapter(adapterSpinner);

        btCadastrarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    efetuaCadastro();
                    alert("paciente cadastrado");
                    enviaEmail();
                    limpaCampos();
                    finish();
                }else{
                    alert("preencha os campos obrigatórios(*)");
                }
            }
        });
    }

    private boolean checkInput() {
        if(txtNome.getText().toString().equals("") || txtEmail.getText().toString().equals("") || txtTelefone.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    private void inicializarComponentes() {
        txtNome = findViewById(R.id.cadpaci_txt_nome_id);
        txtEmail = findViewById(R.id.cadpaci_txt_email_id);
        txtTelefone = findViewById(R.id.cadpaci_txt_telefone_id);
        txtEndereco = findViewById(R.id.cadpaci_txt_end_id);
        txtCidade = findViewById(R.id.cadpaci_txt_cidade_id);
        txtObservacao = findViewById(R.id.cadpaci_txt_obs_id);
        radioGroup = findViewById(R.id.cadpaci_rdig_sexo_id);
        btCadastrarCli = findViewById(R.id.cadpaci_btn_cadastrar_id);
        spnEstado = findViewById(R.id.cadpaci_sp_estado_id);
    }

    private void efetuaCadastro() {
        int idSexoEscolido = radioGroup.getCheckedRadioButtonId();
        radioSexo = findViewById(idSexoEscolido);

        Paciente p = new Paciente();
        p.setIdPac(UUID.randomUUID().toString());
        p.setNome(txtNome.getText().toString());
        p.setEmail(txtEmail.getText().toString());
        p.setTelefone(txtTelefone.getText().toString());
        p.setEndereco(txtEndereco.getText().toString());
        p.setEstado(spnEstado.getSelectedItem().toString());
        p.setCidade(txtCidade.getText().toString());
        p.setSexo(radioSexo.getText().toString());
        p.setObservacao(txtObservacao.getText().toString());

        ConexaoBanco.inicializarConexaoBanco();
        ConexaoBanco.salvarPacienteBanco(p.getIdPac(), p);
    }

    private void enviaEmail() {
        String[] to = { txtEmail.getText().toString() };
        String subject = "Cadastro iTherapeutic";
        String message = "Olá " + txtNome.getText().toString() + ", seu cadastro foi realizado no iTherapeutic, conhecça nossos serviços e participe dos nossos eventos. \n" +
                "Acesse: www.itherapeutic.com e fique por dentro dos últimos acontecimentos.";

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, to);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(email, "E-mail"));
    }

    private void limpaCampos() {
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
        radioSexo.clearFocus();
        txtObservacao.setText("");
    }

    private void alert(String s) {
        Toast.makeText(CadastrarPacientesActivity.this, s, Toast.LENGTH_LONG).show();
    }

}
