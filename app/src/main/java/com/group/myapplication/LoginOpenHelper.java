package com.group.myapplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginOpenHelper extends SQLiteOpenHelper{

    public LoginOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BDUsuarios) {
        BDUsuarios.execSQL("create table tabla_usuarios(dni int, nombre text, celular int, password text)"); //crea tabla
        BDUsuarios.execSQL("create table tabla_reservas(dni int, pelicula int, horario int)"); //crea tabla
        //BDUsuarios.execSQL("create table tabla_peliculas(int id_pelicula, string item_pelicula)"); //crea tabla
        //BDUsuarios.execSQL("create table tabla_horarios(int id_horario, string item_horario)"); //crea tabla


    }

    @Override
    public void onUpgrade(SQLiteDatabase BDUsuarios, int i, int i1) {

    }
}