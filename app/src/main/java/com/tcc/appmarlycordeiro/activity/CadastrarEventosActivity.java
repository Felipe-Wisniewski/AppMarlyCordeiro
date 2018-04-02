package com.tcc.appmarlycordeiro.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.tcc.appmarlycordeiro.R;

import java.util.Calendar;

public class CadastrarEventosActivity extends Activity {
    private Spinner spnTipoEvento, spnTerapeutaEvento;
    private TextView txtVlocalEvento, txtVdateEvento, txtVtimeEvento;
    int PLACE_PICKER_REQUEST = 1;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private EditText txtObservacaoEvento;
    private Button btSelecionarPacientes, btCadastrarEvento;
    
    private String[] tipoEventos = {"*Evento", "Abordagem Sistêmica", "Constelação Familiar em Grupo",
            "Constelações Familiares", "Constelações Organizacionais", "Constelações Sistêmicas com Cavalos",
            "Constelações TSFI", "Jornada de Meditação", "Meditação Ativa do Osho", "Palestra",
            "Renascendo na percepção dos Sentidos", "Renascimento em Respiração", "Vivência em Grupo",
            "Workshop"};

    private String[] nomeTerapeutas = {"*Terapeuta", "Ana Maria", "Anna Battistel", "Ida Maria Mello",
            "Juca Amaral", "Leonardo de Albuquerque", "Marly Cordeiro", "Myrta Regina", "Sílvia Fleury",
            "Socorro Bastos", "Vagner Moreira"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_eventos);

        inicializarComponentes();
        inicializarSpinners();
        inicializarPlacePicker();
        inicializarDataPicker();
        inicializarTimePicker();
        
        btCadastrarEvento.setOnClickListener(new View.OnClickListener() {
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
        spnTipoEvento = findViewById(R.id.cadevento_spin_evento_id);
        spnTerapeutaEvento = findViewById(R.id.cadevento_spin_terap_id);
        txtVlocalEvento = findViewById(R.id.cadevento_txtv_local_id);
        txtVdateEvento = findViewById(R.id.cadevento_txtv_date_id);
        txtVtimeEvento = findViewById(R.id.cadevento_txtv_time_id);
        txtObservacaoEvento = findViewById(R.id.cadevento_txt_obs_id);
        btSelecionarPacientes = findViewById(R.id.cadevento_btn_selpac_id);
        btCadastrarEvento = findViewById(R.id.cadevento_btn_cadastrar_id);
    }

    private void inicializarSpinners() {
        ArrayAdapter<String> adapterTpEvento = new ArrayAdapter<String>(CadastrarEventosActivity.this, R.layout.spinner_item , tipoEventos);
        spnTipoEvento.setAdapter(adapterTpEvento);

        ArrayAdapter<String> adapterTerapeutas = new ArrayAdapter<String>(CadastrarEventosActivity.this, R.layout.spinner_item , nomeTerapeutas);
        spnTerapeutaEvento.setAdapter(adapterTerapeutas);
    }

    private void inicializarPlacePicker() {
        txtVlocalEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                Intent i;
                try {
                    i = builder.build(CadastrarEventosActivity.this);
                    startActivityForResult(i, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==PLACE_PICKER_REQUEST){
            if(resultCode==RESULT_OK){
                Place place = PlacePicker.getPlace(data, CadastrarEventosActivity.this);
                String address = String.format("Local: %s", place.getAddress());
                txtVlocalEvento.setText(address);

                alert(address);
            }
        }
    }

    private void inicializarDataPicker() {
        txtVdateEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CadastrarEventosActivity.this,
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
                txtVdateEvento.setText(date);
            }   
        };
    }
    
    private void inicializarTimePicker() {
        txtVtimeEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar t = Calendar.getInstance();
                int hour = t.get(Calendar.HOUR_OF_DAY);
                int minute = t.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(CadastrarEventosActivity.this,
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
                    txtVtimeEvento.setText(time);
                }else{
                    String time = hourOfDay + ":" + minute;
                    txtVtimeEvento.setText(time);
                }
            }
        };
    }

    private boolean checkInput() {
        if(spnTipoEvento.getSelectedItem().toString().equals("*Evento") ||
                spnTerapeutaEvento.getSelectedItem().toString().equals("*Terapeuta") ||
                txtVlocalEvento.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    private void efetuarCadastro() {
        

        limpaCampos();
        finish();
    }

    private void limpaCampos() {
    }

    private void alert(String s) {
        Toast.makeText(CadastrarEventosActivity.this, s, Toast.LENGTH_LONG).show();
    }
}

