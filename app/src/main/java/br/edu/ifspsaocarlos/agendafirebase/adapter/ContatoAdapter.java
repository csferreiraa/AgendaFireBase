package br.edu.ifspsaocarlos.agendafirebase.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import br.edu.ifspsaocarlos.agendafirebase.model.Contato;
import br.edu.ifspsaocarlos.agendafirebase.R;

public class ContatoAdapter extends FirebaseRecyclerAdapter<Contato,ContatoAdapter.ContatoViewHolder> {

    private static ItemClickListener clickListener;

    public ContatoAdapter(FirebaseRecyclerOptions<Contato> options) {
        super(options);
    }

    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contato_celula, parent, false);

        return new ContatoViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(ContatoViewHolder holder, int position, final Contato model) {
        holder.nome.setText(model.getNome()+ " Tel: " + model.getFone());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Tipo do contato: " + model.getTipoContato(), Toast.LENGTH_SHORT).show();
            }
        });
    }

   public void setClickListener(ItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }

    public  class ContatoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView nome;
        final CardView cardView;

        ContatoViewHolder(View view) {
            super(view);
            nome = (TextView)view.findViewById(R.id.displayInfo);
            cardView = view.findViewById(R.id.card_view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (clickListener != null)
                clickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }
}