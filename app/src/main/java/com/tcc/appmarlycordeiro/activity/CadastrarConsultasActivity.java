package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tcc.appmarlycordeiro.R;
import com.tcc.appmarlycordeiro.business.Consulta;
import com.tcc.appmarlycordeiro.business.Paciente;
import com.tcc.appmarlycordeiro.database.ConexaoBanco;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class CadastrarConsultasActivity extends Activity {
    private Spinner spnPacienteConsulta, spnTipoConsulta, spnTerapeutaConsulta;
    private TextView txtVdate, txtVtime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private EditText txtObsConsulta;
    private Button btCadastrarCons;

    private String[] tipoConsultas = {"*Consulta","Acunpuntura", "Aromaterapia", "Astrologia",
            "Coaching com Cavalos", "Coaching Sistêmico", "Constelação Familiar", "Educação Sistêmica",
            "Fitoterapia", "Geoterapia Shiatsu", "Ioga", "Meditação", "Reiki", "Terapia Sistêmica Familiar",
            "Terapia Sistêmica Musical", "Watsu"};

    private String[] nomeTerapeutas = {"*Terapeuta", "Ana Maria", "Anna Battistel", "Ida Maria Mello",
            "Juca Amaral", "Leonardo de Albuquerque", "Marly Cordeiro", "Myrta Regina", "Sílvia Fleury",
            "Socorro Bastos", "Vagner Moreira"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_consultas);

        inicializarComponentes();
        inicializarSpinnerPacientes();
        inicializarSpinners();
        inicializarDatePicker();
        inicializarTimePicker();

        btCadastrarCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    efetuarCadastro();
                }else{
                    alert("Preencha todos os *campos !");
                }
            }
        });
    }

    private void inicializarComponentes() {
        spnPacienteConsulta = findViewById(R.id.cadcons_spin_paci_id);
        spnTipoConsulta = findViewById(R.id.cadcons_spin_cons_id);
        spnTerapeutaConsulta = findViewById(R.id.cadcons_spin_terap_id);
        txtVdate = findViewById(R.id.cadcons_txtv_date_id);
        txtVtime = findViewById(R.id.cadcons_txtv_time_id);
        txtObsConsulta = findViewById(R.id.cadcons_txt_obs_id);
        btCadastrarCons = findViewById(R.id.cadcons_btn_cadastrar_id);
    }

    private void inicializarSpinnerPacientes() {
        ConexaoBanco.inicializarConexaoBanco();
        Query query;

        query = ConexaoBanco.getPacientesBanco();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Paciente vazio = new Paciente();
                List<Paciente> listaPacientes = new ArrayList<>();

                vazio.setNome("*Paciente");
                listaPacientes.add(vazio);

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Paciente p = objSnapshot.getValue(Paciente.class);
                    listaPacientes.add(p);
                }
                ArrayAdapter<Paciente> adapterPacientes = new ArrayAdapter<Paciente>(CadastrarConsultasActivity.this, R.layout.spinner_item , listaPacientes);
                spnPacienteConsulta.setAdapter(adapterPacientes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializarSpinners() {
        ArrayAdapter<String> adapterTpConsultas = new ArrayAdapter<String>(CadastrarConsultasActivity.this, R.layout.spinner_item , tipoConsultas);
        spnTipoConsulta.setAdapter(adapterTpConsultas);

        ArrayAdapter<String> adapterTerapeutas = new ArrayAdapter<String>(CadastrarConsultasActivity.this, R.layout.spinner_item , nomeTerapeutas);
        spnTerapeutaConsulta.setAdapter(adapterTerapeutas);
    }

    private void inicializarDatePicker() {
        txtVdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CadastrarConsultasActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                txtVdate.setText(date);
            }
        };
    }

    private void inicializarTimePicker() {
        txtVtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar t = Calendar.getInstance();
                int hour = t.get(Calendar.HOUR_OF_DAY);
                int minute = t.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(CadastrarConsultasActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener, hour, minute, true);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(minute < 10){
                    String time = hourOfDay + ":0" + minute;
                    txtVtime.setText(time);
                }else{
                    String time = hourOfDay + ":" + minute;
                    txtVtime.setText(time);
                }
            }
        };
    }

    private Boolean checkInput() {
        if(spnPacienteConsulta.getSelectedItem().toString().equals("*Paciente") ||
                spnTipoConsulta.getSelectedItem().toString().equals("*Consulta") ||
                spnTerapeutaConsulta.getSelectedItem().toString().equals("*Terapeuta")){
            return false;
        }else{
            return true;
        }
    }

   private void efetuarCadastro() {
        String spnPaciSelect, strPaciId;
        spnPaciSelect = spnPacienteConsulta.getSelectedItem().toString().trim();
        strPaciId = ConexaoBanco.getPacienteId(spnPaciSelect);

        Consulta c = new Consulta();

        c.setIdConsulta(UUID.randomUUID().toString());
        c.setTipoConsulta(spnTipoConsulta.getSelectedItem().toString());
        c.setNomeTerapeuta(spnTerapeutaConsulta.getSelectedItem().toString());
        c.setDataConsulta(txtVdate.getText().toString());
        c.setHoraConsulta(txtVtime.getText().toString());
        c.setObservacaoConsulta(txtObsConsulta.getText().toString());
        c.setNomePaciente(spnPaciSelect);

        ConexaoBanco.salvarConsultaBanco(c.getIdConsulta(), c);

        limpaCampos();
        finish();
    }

    private void limpaCampos() {

    }

    private void alert(String s) {
        Toast.makeText(CadastrarConsultasActivity.this, s, Toast.LENGTH_LONG).show();
    }

}
