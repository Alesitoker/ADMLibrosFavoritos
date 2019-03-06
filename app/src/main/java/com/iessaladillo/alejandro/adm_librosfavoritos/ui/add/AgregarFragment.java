package com.iessaladillo.alejandro.adm_librosfavoritos.ui.add;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_librosfavoritos.R;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.AppDatabase;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model.Libro;
import com.iessaladillo.alejandro.adm_librosfavoritos.databinding.FragmentAgregarBinding;
import com.iessaladillo.alejandro.adm_librosfavoritos.utils.KeyboardUtils;
import com.iessaladillo.alejandro.adm_librosfavoritos.utils.ValidationsUtils;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class AgregarFragment extends Fragment {

    private FragmentAgregarBinding b;
    private AgregarFragmentViewModel viewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentAgregarBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this,
                new AgregarFragmentViewModelFactory(new RepositoryImpl(AppDatabase.getInstance(requireContext().getApplicationContext()).libroDao()))).get(AgregarFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupViews();
    }

    private void setupViews() {
        b.txtPortada.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (ValidationsUtils.isValidUrl(b.txtPortada.getText().toString())) {
                    Picasso.with(requireContext()).load(
                            b.txtPortada.getText().toString()).into(b.imgPortada);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_agregar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        Libro libro;
        KeyboardUtils.hideSoftKeyboard(requireActivity());
        if (comprobarCampos()) {
            libro = new Libro(0, portada(),
                    b.txtTitle.getText().toString(), b.txtAutor.getText().toString(),
                    b.txtYear.getText().toString(), sinopsis());
            viewModel.addLibro(libro);
            navController.navigateUp();
        }
    }

    private String portada() {
        if (!ValidationsUtils.isValidUrl(b.txtPortada.getText().toString())) {
            return "no";
        }
        return b.txtPortada.getText().toString();
    }

    private String sinopsis() {
        if (b.txtSinopsis.getText().toString().isEmpty()) {
            return "Sinopsis no disponible";
        }
        return b.txtSinopsis.getText().toString();
    }

    private boolean validAutor() {
        if (!b.txtAutor.getText().toString().isEmpty()) {
            b.txtAutorLayout.setErrorEnabled(false);
            return true;
        } else {
            b.txtAutorLayout.setError("El campo no puede estar vacio");
            return false;
        }
    }

    private boolean validTitle() {
        if (!b.txtTitle.getText().toString().isEmpty()) {
            b.txtTitleLayout.setErrorEnabled(false);
            return true;
        } else {
            b.txtTitleLayout.setError("El campo no puede estar vacio");
            return false;
        }
    }

    private boolean validYear() {
        if (b.txtYear.getText().toString().matches("[0-9]{4}")) {
            b.txtYearLayout.setErrorEnabled(false);
            return true;
        } else {
            b.txtYearLayout.setError("Debe ser un año valido");
            return false;
        }
    }

    private boolean comprobarCampos() {
        boolean title, autor, year;
        title = validTitle();
        autor = validAutor();
        year = validYear();

        return title && autor && year;
    }
}
