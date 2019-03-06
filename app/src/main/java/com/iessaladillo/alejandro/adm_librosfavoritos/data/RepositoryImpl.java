package com.iessaladillo.alejandro.adm_librosfavoritos.data;

import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.LibroDao;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model.Libro;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RepositoryImpl implements Repository {

    private final LibroDao libroDao;

    public RepositoryImpl(LibroDao libroDao) {
        this.libroDao = libroDao;
    }

    @Override
    public LiveData<List<Libro>> queryLibros() {
        return null;
    }

    @Override
    public void insertLibro(Libro libro) {

    }

    @Override
    public void deleteLibro(Libro libro) {

    }
}
