package com.iessaladillo.alejandro.adm_librosfavoritos.data.local;

import android.content.Context;

import com.iessaladillo.alejandro.adm_librosfavoritos.data.local.model.Libro;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Libro.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Libros";

    private static AppDatabase instance;

    public abstract LibroDao libroDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(), AppDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
