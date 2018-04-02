package com.tcc.appmarlycordeiro.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.google.firebase.database.ValueEventListener;
import com.tcc.appmarlycordeiro.business.Consulta;
import com.tcc.appmarlycordeiro.business.Paciente;

public class ConexaoBanco {

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    private static String strPaciId;

    private ConexaoBanco(){ };

    public static void inicializarConexaoBanco(){
    //    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static void salvarPacienteBanco(String id, Paciente p){
        databaseReference.child("Pacientes").child(id).setValue(p);
    }

    public static void salvarConsultaBanco(String id, Consulta c){
        databaseReference.child("Consultas").child(id).setValue(c);
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

    public static String getPacienteId(String nome){
        Query query;
        query = databaseReference.child("Pacientes").orderByChild("nome").equalTo(nome);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                        strPaciId = objSnapshot.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return strPaciId;
    }
}
