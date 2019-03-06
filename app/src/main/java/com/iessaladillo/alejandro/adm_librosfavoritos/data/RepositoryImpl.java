package com.iessaladillo.alejandro.adm_librosfavoritos.data;

import android.os.AsyncTask;

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
        return libroDao.queryLibros();
    }

    @Override
    public void insertLibro(Libro libro) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            libroDao.insert(libro);
        });
    }

    @Override
    public void deleteLibro(Libro libro) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            libroDao.delete(libro);
        });
    }
}
