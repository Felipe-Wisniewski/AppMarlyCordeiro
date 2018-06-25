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
import com.tcc.appmarlycordeiro.business.Evento;
import com.tcc.appmarlycordeiro.database.ConexaoBanco;
import java.util.List;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolderEventos> {

    private List<Evento> listaEventos;
    private Context context;

    public EventosAdapter(List<Evento> listaE, Context EvenActivity) {
        this.listaEventos = listaE;
        this.context = EvenActivity;
    }

    @Override
    public ViewHolderEventos onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_eventos, parent, false);
        ViewHolderEventos holderEventos = new ViewHolderEventos(view);

        return holderEventos;
    }

    @Override
    public void onBindViewHolder(final EventosAdapter.ViewHolderEventos holder, final int position) {

        if((listaEventos != null) && (listaEventos.size() > 0)) {
            final Evento eve = listaEventos.get(position);

            holder.txtEvento.setText(eve.getTipoEvento());
            holder.txtLocal.setText(eve.getLocalEvento());

           /* holder.txtLocal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(eve.getLocalUrl()));
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                }
            });*/

            holder.txtData.setText(eve.getDataEvento());

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("Excluindo Evento")
                            .setMessage("Tem certeza que deseja excluir este evento ?")
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    listaEventos.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, listaEventos.size());
                                    ConexaoBanco.inicializarConexaoBanco();
                                    ConexaoBanco.removerEvento(eve.getIdEvento());
                                    Toast.makeText(context, "Evento excluido", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Não", null)
                            .show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }

    public class ViewHolderEventos extends RecyclerView.ViewHolder {
        public TextView txtEvento, txtLocal, txtData;
        public ImageButton btnDelete;

        public ViewHolderEventos(View itemView) {
            super(itemView);

            txtEvento = itemView.findViewById(R.id.linha_evento_txt_evento_id);
            txtLocal = itemView.findViewById(R.id.linha_evento_txt_locall_id);
            txtData = itemView.findViewById(R.id.linha_evento_txt_data_id);
            btnDelete = itemView.findViewById(R.id.linha_evento_delete_button);
        }
    }
}
