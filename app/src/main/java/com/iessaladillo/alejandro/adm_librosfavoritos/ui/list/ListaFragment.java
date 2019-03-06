package com.iessaladillo.alejandro.adm_librosfavoritos.ui.list;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.iessaladillo.alejandro.adm_librosfavoritos.R;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.AppDatabase;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model.Libro;
import com.iessaladillo.alejandro.adm_librosfavoritos.databinding.FragmentListaBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ListaFragment extends Fragment {

    private FragmentListaBinding b;
    private ListaFragmentViewModel viewModel;
    private NavController navController;
    private ListaFragmentAdapter listAdapter;
    private BottomSheetBehavior<ConstraintLayout> bsb;
    private SharedPreferences settings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
                new ListaFragmentViewModelFactory(new RepositoryImpl(AppDatabase.getInstance(requireContext()).libroDao()))).get(ListaFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        settings = PreferenceManager.getDefaultSharedPreferences(requireContext());
        setupViews();
        observeLibros();
    }

    private void observeLibros() {
        viewModel.getLibros().observe(this, libros -> {
            listAdapter.submitList(libros);
            b.lblEmptyView.setVisibility(libros.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void setupViews() {
        setupRecyclerView();

        b.fab.setOnClickListener(v -> navigateToAgregar());
        b.lblEmptyView.setOnClickListener(v -> navigateToAgregar());
        bsb = BottomSheetBehavior.from(b.bSheet.bottomSheet);
    }

    private void setupBottomSheet(String title, String sinopsis) {
        bsb.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    private void setupRecyclerView() {
        listAdapter = new ListaFragmentAdapter();
        listAdapter.setOnSinopsiShow(position -> setupBottomSheet(listAdapter.getItem(position).getTitle(), listAdapter.getItem(position).getSinopsis()));

        b.lstLista.setHasFixedSize(true);
        b.lstLista.setLayoutManager(new GridLayoutManager(requireContext(),
                getResources().getInteger(R.integer.lst_columns)));
        b.lstLista.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));
        b.lstLista.setAdapter(listAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.END) {
                    String nombre = listAdapter.getItem(viewHolder.getAdapterPosition()).getTitle();
                    viewModel.deleteLibro(listAdapter.getItem(viewHolder.getAdapterPosition()));
                    if (settings.getBoolean(getString(R.string.prefDeshacer_key), true)) {
                        Snackbar.make(b.lblEmptyView, getString(R.string.snackBar_deshacer, nombre),
                                Snackbar.LENGTH_LONG).setAction(getString(R.string.deshacer_action),
                                v -> viewModel.addDeleteLibro()).show();
                    }
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(b.lstLista);
    }

    private void navigateToAgregar() {
        navController.navigate(R.id.actionListaToAgregar);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_settings, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
}
