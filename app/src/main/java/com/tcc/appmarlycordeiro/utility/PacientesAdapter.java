package com.tcc.appmarlycordeiro.utility;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tcc.appmarlycordeiro.R;

import java.util.List;

import com.tcc.appmarlycordeiro.business.Paciente;

public class PacientesAdapter extends RecyclerView.Adapter<PacientesAdapter.ViewHolderPaciente> {

    private List<Paciente> listaPacientes;

    public PacientesAdapter(List<Paciente> listaP){
        this.listaPacientes = listaP;
    }

    @Override
    public PacientesAdapter.ViewHolderPaciente onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_pacientes, parent, false);
        ViewHolderPaciente holderPaciente = new ViewHolderPaciente(view);

        return holderPaciente;
    }

    @Override
    public void onBindViewHolder(PacientesAdapter.ViewHolderPaciente holder, int position) {

        if ((listaPacientes != null) && (listaPacientes.size() > 0)){
            Paciente pac = listaPacientes.get(position);

            holder.txtNome.setText(pac.getNome());
            holder.txtTelefone.setText(pac.getTelefone());
        }
    }

    @Override
    public int getItemCount() {
        return listaPacientes.size();
    }

    public class ViewHolderPaciente extends RecyclerView.ViewHolder{

        public TextView txtNome, txtTelefone;

        public ViewHolderPaciente(View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.linha_cons_txt_consulta_id);
            txtTelefone = itemView.findViewById(R.id.linha_cli_txt_tel_id);
        }
    }

}
