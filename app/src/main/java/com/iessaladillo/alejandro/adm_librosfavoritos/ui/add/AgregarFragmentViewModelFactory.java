package com.iessaladillo.alejandro.adm_librosfavoritos.ui.add;

import com.iessaladillo.alejandro.adm_librosfavoritos.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AgregarFragmentViewModelFactory implements ViewModelProvider.Factory {

    Repository repository;

    public AgregarFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AgregarFragmentViewModel(repository);
    }
}
