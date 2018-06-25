package com.tcc.appmarlycordeiro.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.google.firebase.database.ValueEventListener;
import com.tcc.appmarlycordeiro.business.Consulta;
import com.tcc.appmarlycordeiro.business.Evento;
import com.tcc.appmarlycordeiro.business.Paciente;

public class ConexaoBanco {

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    private ConexaoBanco(){ };

    public static void inicializarConexaoBanco(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static void salvarPacienteBanco(String id, Paciente p){
        databaseReference.child("Pacientes").child(id).setValue(p);
    }

    public static void salvarConsultaBanco(String id, Consulta c){
        databaseReference.child("Consultas").child(id).setValue(c);
    }

    public static void salvarEventoBanco(String id, Evento e){
        databaseReference.child("Eventos").child(id).setValue(e);
    }

    public static Query getPacientesBanco() {
        Query query;
        query = databaseReference.child("Pacientes").orderByChild("nome");
        return query;
    }

    public static Query getConsultasBanco(){
        Query query;
        query = databaseReference.child("Consultas").orderByChild("dataConsulta");
        return query;
    }

    public static Query getEventosBanco(){
        Query query;
        query = databaseReference.child("Eventos").orderByChild("dataEvento");
        return query;
    }

    public static void removerPaciente(String id) {
        databaseReference.child("Pacientes").child(id).removeValue();
    }

    public static void removerConsulta(String id) {
        databaseReference.child("Consultas").child(id).removeValue();
    }

    public static void removerEvento(String id) {
        databaseReference.child("Eventos").child(id).removeValue();
    }
}
