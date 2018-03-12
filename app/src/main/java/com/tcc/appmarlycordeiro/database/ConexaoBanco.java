package com.tcc.appmarlycordeiro.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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


    public static Query getPacientesBanco() {

        Query query;
        query = databaseReference.child("Pacientes").orderByChild("idPac");
        return query;
    }


}
