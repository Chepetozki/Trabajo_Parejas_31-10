package com.example.trabajo_parejas_31_10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText ID, Nombre, Dueño, Domicilio, Especies;
    Button Agregar, Actualizar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = findViewById(R.id.txtID_Mascota);
        Nombre = findViewById(R.id.txtNombreMascota);
        Dueño = findViewById(R.id.txtDueñoMascota);
        Domicilio = findViewById(R.id.txtDomicilio);
        Especies = findViewById(R.id.txtEspecies);

        Agregar = findViewById(R.id.btnAgregar);
        Actualizar = findViewById(R.id.btnMostrar);


        /*
        //Lista
        String EspeciesMascota [] = {"Seleccione:", "Gato", "Perro", "Conejo", "Cuy"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, EspeciesMascota);
        Especies.setAdapter(adapter);*/


    }

    //==============================================================================================
    public void AgregarMascota(View view) {

        //Usar base de datos
        AdminSQLiteOnHelper admin = new AdminSQLiteOnHelper(this, "Veterinaria", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String ID_Mascota = ID.getText().toString();
        String nombreMascota = Nombre.getText().toString();
        String dueñoMascota = Dueño.getText().toString();
        String domicilioMascota = Domicilio.getText().toString();
        String especieMascota = Especies.getText().toString();

        if (!ID_Mascota.isEmpty() && !nombreMascota.isEmpty() && !dueñoMascota.isEmpty() && !domicilioMascota.isEmpty() && !especieMascota.equals("Seleccione:")) {

            ContentValues DatosUsuario = new ContentValues();

            //put(debe tener columna de la base de datos, string del dato ingresado)
            DatosUsuario.put("idMascota", ID_Mascota);
            DatosUsuario.put("NombreMascota", nombreMascota);
            DatosUsuario.put("DueñoMascota", dueñoMascota);
            DatosUsuario.put("DomicilioMascota", domicilioMascota);
            DatosUsuario.put("EspecieMascota", especieMascota);

            BaseDatos.insert("Mascotas", null, DatosUsuario);
            BaseDatos.close();

            ID.setText("");
            Nombre.setText("");
            Dueño.setText("");
            Domicilio.setText("");
            Especies.setText("");

            Toast.makeText(this, "Mascota Agregada Exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No pueden quedar campos vacios", Toast.LENGTH_SHORT).show();
        }
    }
    
    //==============================================================================================
    public void MostrarMascota(View view) {

        Intent intent = new Intent(MainActivity.this, TablaVeterinario.class);
        startActivity(intent);
    }
}