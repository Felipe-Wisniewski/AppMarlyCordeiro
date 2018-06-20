package com.tcc.appmarlycordeiro.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tcc.appmarlycordeiro.R;
import com.tcc.appmarlycordeiro.business.Consulta;
import com.tcc.appmarlycordeiro.database.ConexaoBanco;

import java.util.List;

public class ConsultasAdapter extends RecyclerView.Adapter<ConsultasAdapter.ViewHolderConsultas>{

    private List<Consulta> listaConsultas;
    private Context context;

    public ConsultasAdapter(List<Consulta> listaC, Context ConsActivity) {
        this.listaConsultas = listaC;
        this.context = ConsActivity;
    }

    @Override
    public ConsultasAdapter.ViewHolderConsultas onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_consultas, parent, false);
        ViewHolderConsultas holderConsultas = new ViewHolderConsultas(view);

        return holderConsultas;
    }

    @Override
    public void onBindViewHolder(ConsultasAdapter.ViewHolderConsultas holder, final int position) {

        if((listaConsultas != null) && (listaConsultas.size() > 0)){
            final Consulta con = listaConsultas.get(position);

            holder.txtConsulta.setText(con.getTipoConsulta());
            holder.txtPaciente.setText(con.getNomePaciente());
            holder.txtData.setText(con.getDataConsulta());

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("Excluindo Consulta")
                            .setMessage("Tem certeza que deseja excluir esta consulta ?")
                            .setPositiveButton("Sim",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            listaConsultas.remove(position);
                                            notifyItemRemoved(position);
                                            notifyItemRangeChanged(position, listaConsultas.size());
                                            ConexaoBanco.inicializarConexaoBanco();
                                            ConexaoBanco.removerConsulta(con.getIdConsulta());
                                            Toast.makeText(context, "Consulta excluída!", Toast.LENGTH_LONG).show();
                                        }
                                    })
                            .setNegativeButton("Nâo", null)
                            .show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaConsultas.size();
    }

    public class ViewHolderConsultas extends RecyclerView.ViewHolder {

        public TextView txtConsulta, txtPaciente, txtData;
        public ImageButton btnDelete;

        public ViewHolderConsultas(View itemView) {
            super(itemView);

            txtConsulta = itemView.findViewById(R.id.linha_evento_txt_evento_id);
            txtPaciente = itemView.findViewById(R.id.linha_cons_txt_pac_id);
            txtData = itemView.findViewById(R.id.linha_cons_txt_data_id);
            btnDelete = itemView.findViewById(R.id.linha_cons_delete_button);
        }
    }
}
