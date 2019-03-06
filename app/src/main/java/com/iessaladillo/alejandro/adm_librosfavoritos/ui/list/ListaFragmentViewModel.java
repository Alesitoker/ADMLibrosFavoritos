package com.iessaladillo.alejandro.adm_librosfavoritos.ui.list;

import com.iessaladillo.alejandro.adm_librosfavoritos.data.Repository;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model.Libro;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListaFragmentViewModel extends ViewModel {

    private final Repository repository;
    private final LiveData<List<Libro>> libros;
    private Libro libroDelete;
    private final MutableLiveData<Libro> deleteTrigger = new MutableLiveData<>();

    public ListaFragmentViewModel(Repository repository) {
        this.repository = repository;
        libros = repository.queryLibros();

    }

    void deleteLibro(Libro libro) {
        libroDelete = libro;
        repository.deleteLibro(libro);
    }

    public LiveData<List<Libro>> getLibros() {
        return libros;
    }

    public void addDeleteLibro() {
        repository.insertLibro(libroDelete);
    }
}
