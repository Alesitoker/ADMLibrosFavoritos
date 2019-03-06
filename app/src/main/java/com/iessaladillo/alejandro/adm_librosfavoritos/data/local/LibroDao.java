package com.iessaladillo.alejandro.adm_librosfavoritos.data.local;

import com.iessaladillo.alejandro.adm_librosfavoritos.base.BaseDao;
import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model.Libro;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

public interface LibroDao extends BaseDao<Libro> {

    @Query("SELECT * FROM libro")
    LiveData<List<Libro>> queryLibros();
}
