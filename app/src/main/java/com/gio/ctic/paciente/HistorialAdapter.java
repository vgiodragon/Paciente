package com.gio.ctic.paciente;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by user on 07/06/2016.
 */
public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistoriaViewHolder>{
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Historial> mHistorialSet;
    private static MyClickListener myClickListener;

    public static class HistoriaViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        TextView tfecha;
        TextView tdoctor;
        TextView tsintoma;
        TextView tdescripcion;
        TextView tespecialidad;

        public HistoriaViewHolder(View itemView) {
            super(itemView);
            tfecha =(TextView)itemView.findViewById(R.id.textView);
            tdoctor =(TextView)itemView.findViewById(R.id.textView2);
            tsintoma =(TextView)itemView.findViewById(R.id.textView3);
            tdescripcion =(TextView)itemView.findViewById(R.id.textView4);
            tespecialidad=(TextView)itemView.findViewById(R.id.textView5);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public HistorialAdapter(ArrayList<Historial> mHistorialSet) {
        this.mHistorialSet = mHistorialSet;
        Ordenar(0);
    }

    @Override
    public HistoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        return new HistoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoriaViewHolder holder, int position) {
        holder.tfecha.setText(mHistorialSet.get(position).getFecha());
        holder.tdoctor.setText(mHistorialSet.get(position).getDoctor());
        holder.tdescripcion.setText(mHistorialSet.get(position).getDescripcion());
        holder.tsintoma.setText(mHistorialSet.get(position).getSintomas());
        holder.tespecialidad.setText(mHistorialSet.get(position).getEspecialidad());
    }

    public void addItem(Historial dataObj, int index) {
        mHistorialSet.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mHistorialSet.remove(index);
        notifyItemRemoved(index);
    }

    public void Ordenar(int op){
        // Sorting
        switch (op){
            case 0:
                Collections.sort(mHistorialSet, new Comparator<Historial>() {
                    @Override
                    public int compare(Historial lhs, Historial rhs) {
                        return lhs.getFecha().compareTo(rhs.getFecha());
                    }
                });
                break;
            case 1:
                Collections.sort(mHistorialSet, new Comparator<Historial>() {
                    @Override
                    public int compare(Historial lhs, Historial rhs) {
                        return lhs.getDoctor().compareTo(rhs.getDoctor());
                    }
                });
                break;
            case 2:
                Collections.sort(mHistorialSet, new Comparator<Historial>() {
                    @Override
                    public int compare(Historial lhs, Historial rhs) {
                        return lhs.getEspecialidad().compareTo(rhs.getEspecialidad());
                    }
                });
                break;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHistorialSet.size();
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}
