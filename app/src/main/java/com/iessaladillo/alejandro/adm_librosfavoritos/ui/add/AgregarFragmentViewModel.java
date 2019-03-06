package com.iessaladillo.alejandro.adm_librosfavoritos.ui.add;

import com.iessaladillo.alejandro.adm_librosfavoritos.data.Repository;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model.Libro;

import androidx.lifecycle.ViewModel;

public class AgregarFragmentViewModel extends ViewModel {

    private Repository repository;

    public AgregarFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    void addLibro(Libro libro) {
        repository.insertLibro(libro);
    }
}
