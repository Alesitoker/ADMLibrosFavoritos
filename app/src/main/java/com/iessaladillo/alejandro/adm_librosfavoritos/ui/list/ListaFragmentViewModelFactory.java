package com.iessaladillo.alejandro.adm_librosfavoritos.ui.list;

import com.iessaladillo.alejandro.adm_librosfavoritos.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListaFragmentViewModelFactory implements ViewModelProvider.Factory {

    Repository repository;

    public ListaFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListaFragmentViewModel(repository);
    }
}
