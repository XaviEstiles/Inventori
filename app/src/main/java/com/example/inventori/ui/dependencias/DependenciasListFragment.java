package com.example.inventori.ui.dependencias;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.inventori.R;
import com.example.inventori.databinding.FragmentDependenciasListBinding;

public class DependenciasListFragment extends Fragment {

    private  DependencyAdapter adapter;
    private FragmentDependenciasListBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1. se debe indicar a la activity que se quiere modificar el menu
        setHasOptionsMenu(true);

    }
    //2. sobre escribir /anular el metodo onCreatMenu de la activiti
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragmenlist_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    //3. Implementar lo que quieres que hagan los metodos
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_orderdependency:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvDependencias();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDependenciasListBinding.inflate(inflater);
        return binding.getRoot();
    }

    private void initRvDependencias(){
        //1. sera inicializar adapter
        adapter = new DependencyAdapter();
        //2. obligatoriamente se debe indicar que layout tendra el recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //3. Asignar el layout al recycler
        binding.rvDependencias.setLayoutManager(linearLayoutManager);
        //4. Asignar a la vista sus datos
        binding.rvDependencias.setAdapter(adapter);


    }
}