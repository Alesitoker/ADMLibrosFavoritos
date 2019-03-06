package com.iessaladillo.alejandro.adm_librosfavoritos.data;

import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model.Libro;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {

    LiveData<List<Libro>> queryLibros();
    void insertLibro(Libro libro);
    void deleteLibro(Libro libro);
}
