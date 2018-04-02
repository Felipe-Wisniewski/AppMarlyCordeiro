package com.tcc.appmarlycordeiro.utility;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tcc.appmarlycordeiro.R;
import com.tcc.appmarlycordeiro.business.Consulta;

import java.util.List;

public class ConsultasAdapter extends RecyclerView.Adapter<ConsultasAdapter.ViewHolderConsultas>{

    private List<Consulta> listaConsultas;

    public ConsultasAdapter(List<Consulta> listaC){
        this.listaConsultas = listaC;
    }

    @Override
    public ConsultasAdapter.ViewHolderConsultas onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_consultas, parent, false);
        ViewHolderConsultas holderConsultas = new ViewHolderConsultas(view);

        return holderConsultas;
    }

    @Override
    public void onBindViewHolder(ConsultasAdapter.ViewHolderConsultas holder, int position) {

        if((listaConsultas != null) && (listaConsultas.size() > 0)){
            Consulta con = listaConsultas.get(position);

            holder.txtConsulta.setText(con.getTipoConsulta());
            holder.txtPaciente.setText(con.getNomePaciente());
            holder.txtData.setText(con.getDataConsulta());
        }
    }

    @Override
    public int getItemCount() {
        return listaConsultas.size();
    }

    public class ViewHolderConsultas extends RecyclerView.ViewHolder {

        public TextView txtConsulta, txtPaciente, txtData;

        public ViewHolderConsultas(View itemView) {
            super(itemView);

            txtConsulta = itemView.findViewById(R.id.linha_cons_txt_consulta_id);
            txtPaciente = itemView.findViewById(R.id.linha_cons_txt_pac_id);
            txtData = itemView.findViewById(R.id.linha_cons_txt_data_id);
        }
    }
}
