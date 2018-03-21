package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tcc.appmarlycordeiro.R;
import com.tcc.appmarlycordeiro.business.Paciente;
import com.tcc.appmarlycordeiro.database.ConexaoBanco;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CadastrarConsultasActivity extends Activity {

    private Spinner spnPacienteConsulta, spnTipoConsulta, spnTerapeutaConsulta;
    private TextView txtVdate, txtVtime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private EditText txtObsConsulta;
    private Button btCadastrarCons;

    private String[] tipoConsultas = {"Coaching com Cavalos", "Coaching Sistêmico", "Constelações com Cavalos",
                                    "Constelações Familiares", "Constelações Organizacionais", "Constelações TSFI",
                                    "Abordagem Sistêmica", "Educação Sistêmica", "Terapia Sistêmica Familiar",
                                    "Terapia Sistêmica Musical"};

    private String[] nomeTerapeutas = {"Marly Cordeiro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_consultas);

        inicializarComponentes();
        inicializarSpinnerPacientes();
        inicializarSpinners();
        inicializarDatePicker();
        inicializarTimePicker();

    }

    private void inicializarComponentes() {
        spnPacienteConsulta = (Spinner) findViewById(R.id.cadcons_spin_paci_id);
        spnTipoConsulta = (Spinner) findViewById(R.id.cadcons_spin_cons_id);
        spnTerapeutaConsulta = (Spinner) findViewById(R.id.cadcons_spin_terap_id);
        txtVdate = (TextView) findViewById(R.id.cadcons_txtv_date_id);
        txtVtime = (TextView) findViewById(R.id.cadcons_txtv_time_id);
        txtObsConsulta = (EditText) findViewById(R.id.cadcons_txt_obs_id);
        btCadastrarCons = (Button) findViewById(R.id.cadcons_btn_cadastrar_id);
    }

    private void inicializarSpinnerPacientes() {
        ConexaoBanco.inicializarConexaoBanco();

        Query query;
        query = ConexaoBanco.getPacientesBanco();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Paciente> listaPacientes = new ArrayList<>();

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

                String time = hourOfDay + " : " + minute + "h";
                txtVtime.setText(time);
            }
        };
    }
}
