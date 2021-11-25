package com.example.inventori.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventori.R;
import com.example.inventori.databinding.FragmentDashboardBinding;

public class DashBoardFragment extends Fragment implements View.OnClickListener{

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        binding.inflate(inflater, container, false);
        binding =FragmentDashboardBinding.inflate(inflater, container, false);
        binding.imgBtAboutUs.setOnClickListener(this);
        binding.imgBtDependency.setOnClickListener(this);
        binding.imgBtInventory.setOnClickListener(this);
        binding.imgBtProducts.setOnClickListener(this);
        binding.imgBtSector.setOnClickListener(this);
        binding.imgBtSetting.setOnClickListener(this);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.imgBtAboutUs:

                showAboutUs();
                break;

            case R.id.imgBtInventory:
                showAddInventory();
                break;

            case R.id.imgBtDependency:

                showDependency();
                break;
        }
    }

    private void showDependency() {

        NavHostFragment.findNavController(this).navigate(R.id.action_dashBoardFragment_to_dependenciasListFragment);
    }

    private void showAddInventory() {
        NavHostFragment.findNavController(this).navigate(R.id.action_dashBoardFragment_to_addInventoryFragment);
    }

    private void showAboutUs() {

       // NavHostFragment.findNavController(this).navigate();
    }

}