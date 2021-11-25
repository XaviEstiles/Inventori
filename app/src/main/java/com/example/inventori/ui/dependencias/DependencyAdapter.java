package com.example.inventori.ui.dependencias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventori.R;
import com.example.inventori.data.model.Dependency;

import java.util.ArrayList;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {
    private ArrayList<Dependency> list;

    public DependencyAdapter() {
        this.list = new ArrayList<Dependency>();
        list.add(new Dependency("Aula 1º GSDAM","1GSDAM","",""));
        list.add(new Dependency("Aula 1º GSDAM","1GSDAM","",""));
        list.add(new Dependency("Aula 2º GSDAM","2GSDAM","",""));
        list.add(new Dependency("Aula 2º GSDAM","2GSDAM","",""));
        list.add(new Dependency("Aula 2º GSDAM","2GSDAM","",""));

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());
    }

    /**
     * tiene que devolver el tamaño de la lista porque si pones 0 no devolvera nada
     **/
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvName);
        }
    }
}
