package com.example.trabajo_parejas_31_10;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOnHelper extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase Veterinaria) {
        Veterinaria.execSQL("Create Table Mascotas(idMascota integer primary key, NombreMascota Text, DueñoMascota Text, DomicilioMascota Text, EspecieMascota Text)");
    }

    public AdminSQLiteOnHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
