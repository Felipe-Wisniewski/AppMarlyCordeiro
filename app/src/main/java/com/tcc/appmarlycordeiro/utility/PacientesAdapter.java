package com.tcc.appmarlycordeiro.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.tcc.appmarlycordeiro.R;
import java.util.List;
import com.tcc.appmarlycordeiro.business.Paciente;
import com.tcc.appmarlycordeiro.database.ConexaoBanco;

public class PacientesAdapter extends RecyclerView.Adapter<PacientesAdapter.ViewHolderPaciente> {

    private List<Paciente> listaPacientes;
    private Context context;

    public PacientesAdapter(List<Paciente> listaP, Context PacActivity){
        this.listaPacientes = listaP;
        this.context = PacActivity;
    }

    @Override
    public PacientesAdapter.ViewHolderPaciente onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_pacientes, parent, false);
        ViewHolderPaciente holderPaciente = new ViewHolderPaciente(view);

        return holderPaciente;
    }

    @Override
    public void onBindViewHolder(final PacientesAdapter.ViewHolderPaciente holder, final int position) {

        if ((listaPacientes != null) && (listaPacientes.size() > 0)){
            final Paciente pac = listaPacientes.get(position);

            holder.txtNome.setText(pac.getNome());
            holder.txtTelefone.setText(pac.getTelefone());

            holder.btnLigar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:" + pac.getTelefone());
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    context.startActivity(intent);
                }
            });

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("Excluindo Paciente")
                            .setMessage("Tem certeza que deseja excluir " + pac.getNome() + " da lista ?")
                            .setPositiveButton("Sim",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            listaPacientes.remove(position);
                                            notifyItemRemoved(position);
                                            notifyItemRangeChanged(position, listaPacientes.size());
                                            ConexaoBanco.inicializarConexaoBanco();
                                            ConexaoBanco.removerPaciente(pac.getIdPac());
                                            Toast.makeText(context, "Paciente excluído!", Toast.LENGTH_LONG).show();
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
        return listaPacientes.size();
    }

    public class ViewHolderPaciente extends RecyclerView.ViewHolder {

        public TextView txtNome, txtTelefone;
        public ImageButton btnLigar, btnDelete;

        public ViewHolderPaciente(View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.linha_evento_txt_evento_id);
            txtTelefone = itemView.findViewById(R.id.linha_cli_txt_tel_id);
            btnLigar = itemView.findViewById(R.id.linha_telefone_button);
            btnDelete = itemView.findViewById(R.id.linha_evento_delete_button);
        }
    }

}
