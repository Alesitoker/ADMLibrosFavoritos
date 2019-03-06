package com.iessaladillo.alejandro.adm_librosfavoritos.ui.add;

import com.iessaladillo.alejandro.adm_librosfavoritos.data.Repository;

import androidx.lifecycle.ViewModel;

public class AgregarFragmentViewModel extends ViewModel {

    Repository repository;

    public AgregarFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}
