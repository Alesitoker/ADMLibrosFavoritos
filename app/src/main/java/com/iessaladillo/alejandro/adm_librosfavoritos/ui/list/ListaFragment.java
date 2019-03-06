package com.iessaladillo.alejandro.adm_librosfavoritos.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_librosfavoritos.R;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.AppDatabase;
import com.iessaladillo.alejandro.adm_librosfavoritos.databinding.FragmentListaBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

public class ListaFragment extends Fragment {

    private FragmentListaBinding b;
    private ListaFragmentViewModel viewModel;
    private NavController navController;
    private ListaFragmentAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentListaBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this,
                new ListaFragmentViewModelFactory(new RepositoryImpl(AppDatabase.getInstance(requireContext().getApplicationContext()).libroDao()))).get(ListaFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        setupViews();
    }

    private void setupViews() {
        setupRecyclerView();

        b.fab.setOnClickListener(v -> navigateToAgregar());
        b.lblEmptyView.setOnClickListener(v -> navigateToAgregar());
    }

    private void setupRecyclerView() {
        listAdapter = new ListaFragmentAdapter();

        b.lstLista.setHasFixedSize(true);
        b.lstLista.setLayoutManager(new GridLayoutManager(requireContext(),
                getResources().getInteger(R.integer.lst_columns)));
        b.lstLista.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));
        b.lstLista.setAdapter(listAdapter);

    }

    private void navigateToAgregar() {
        navController.navigate(R.id.actionListaToAgregar);
    }

    private void setupToolbar() {

    }
}
