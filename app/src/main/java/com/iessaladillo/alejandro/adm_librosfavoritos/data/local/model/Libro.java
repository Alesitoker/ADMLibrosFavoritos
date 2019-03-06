package com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Libro {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    long id;
    @ColumnInfo(name = "portada")
    String portada;
    @ColumnInfo(name = "titulo")
    String title;
    @ColumnInfo(name = "autor")
    String autor;
    @ColumnInfo(name = "year")
    String year;
    @ColumnInfo(name = "sinopsis")
    String sinopsis;

    public Libro(long id, String portada, String title, String autor, String year, String sinopsis) {
        this.id = id;
        this.portada = portada;
        this.title = title;
        this.autor = autor;
        this.year = year;
        this.sinopsis = sinopsis;
    }

    public long getId() {
        return id;
    }

    public String getPortada() {
        return portada;
    }

    public String getTitle() {
        return title;
    }

    public String getAutor() {
        return autor;
    }

    public String getYear() {
        return year;
    }

    public String getSinopsis() {
        return sinopsis;
    }
}
