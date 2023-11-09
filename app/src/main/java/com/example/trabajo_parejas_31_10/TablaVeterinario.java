package com.example.trabajo_parejas_31_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TablaVeterinario extends AppCompatActivity {

    ListView lista;
    Button Gestionar, Volver;
//hola claudio
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_veterinario2);

        lista = findViewById(R.id.ListView_Veterinario);
        Gestionar = findViewById(R.id.btnGestionar);
        Volver = findViewById(R.id.btnVolver);

        CargaMascotas();
    }

    public void CargaMascotas() {

        AdminSQLiteOnHelper admin = new AdminSQLiteOnHelper(this, "Veterinaria", null , 1);
        SQLiteDatabase BaseDatos = admin.getReadableDatabase();

        Cursor fila = BaseDatos.rawQuery("Select * from Mascotas", null);
        ArrayList<String>  ListaMascotas = new ArrayList<>();

        if (fila.moveToFirst()){
            do {
                String IDmascota = fila.getString(0);
                String NombreMascota = fila.getString(1);
                String DueñoMascota = fila.getString(2);
                String DomicilioMascota = fila.getString(3);
                String especieMascota = fila.getString(4);

                String userInfo = "ID: " + IDmascota + "\n" + "Nombre Mascota: " + NombreMascota + "\n" + "Dueño Mascota: " + DueñoMascota+ "\n" + "Domicilio Mascota: " + DomicilioMascota + "\n" + "Especie: " + especieMascota;

                ListaMascotas.add(userInfo);

            } while (fila.moveToNext());
        }
        BaseDatos.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListaMascotas);
        lista.setAdapter(adapter);
    }

    public void GestionarMascota(View view) {

        Intent intent = new Intent(TablaVeterinario.this, ModificarDatos.class);
        startActivity(intent);
    }

    public void Volver(View view) {
        Intent intent = new Intent(TablaVeterinario.this, MainActivity.class);
        startActivity(intent);
    }
}