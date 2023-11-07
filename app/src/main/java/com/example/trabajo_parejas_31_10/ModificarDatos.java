package com.example.trabajo_parejas_31_10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModificarDatos extends AppCompatActivity {

    EditText ID, Nombre, Dueño, Domicilio, Especies;
    Button Modificar, Eliminar, Buscar, Volver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);

        ID = findViewById(R.id.txtNuevoId);
        Nombre = findViewById(R.id.txtNuevoNombre);
        Dueño = findViewById(R.id.txtNuevoDueño);
        Domicilio = findViewById(R.id.txtNuevoDomicilio);
        Especies = findViewById(R.id.txtNuevoEspecies);
        Modificar = findViewById(R.id.btnModificarMascota);
        Eliminar = findViewById(R.id.btnEliminarMascota);
        Buscar = findViewById(R.id.btnMostrarMascota);
        Volver = findViewById(R.id.btnVolverTabla);

        //clic en eliminar, realizar el showAlert
        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idCampo = ID.getText().toString();

                if (!idCampo.isEmpty()) {

                    showAlertDialog();
                    Toast.makeText(ModificarDatos.this, "Mascota Seleccionada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ModificarDatos.this, "El campo ID no debe esta vacio", Toast.LENGTH_SHORT).show();
                    //mensaje
                }

            }
        });

    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Mascota");
        builder.setMessage("¿Estas seguro que deseas eliminar la mascota?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EliminarMascota();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void ModificarMascota(View view) {

        AdminSQLiteOnHelper admin = new AdminSQLiteOnHelper(this, "Veterinaria", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String ID_Mascota = ID.getText().toString();
        String NombreMascota = Nombre.getText().toString();
        String DueñoMascota = Dueño.getText().toString();
        String DomicilioMascota = Domicilio.getText().toString();
        String EspeciesMascota = Especies.getText().toString();

        if (!NombreMascota.isEmpty() && !DueñoMascota.isEmpty() && !DomicilioMascota.isEmpty() && !EspeciesMascota.isEmpty()){
            ContentValues DatosUsuario = new ContentValues();

            DatosUsuario.put("NombreMascota", NombreMascota);
            DatosUsuario.put("DueñoMascota", DueñoMascota);
            DatosUsuario.put("DomicilioMascota", DomicilioMascota);
            DatosUsuario.put("EspecieMascota", EspeciesMascota);


            int Cantidad = BaseDatos.update("Mascotas", DatosUsuario,
                    "idMascota="+ ID_Mascota, null);
            BaseDatos.close();

            if (Cantidad == 1){
                Toast.makeText(this, "El registro se actualizo correctamente",
                        Toast.LENGTH_SHORT).show();
                ID.setText("");
                Nombre.setText("");
                Dueño.setText("");
                Domicilio.setText("");
                Especies.setText("");

                Intent intent = new Intent(ModificarDatos.this, TablaVeterinario.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "No se encontro el ID ingresado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    //=============================================================================================

    public void EliminarMascota() {

        AdminSQLiteOnHelper admin = new AdminSQLiteOnHelper(this, "Veterinaria", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String id_Mascota = ID.getText().toString();
        if (!id_Mascota.isEmpty()){

            int Eliminar = BaseDatos.delete("Mascotas", "idMascota="+ id_Mascota, null);
            BaseDatos.close();

            if(Eliminar == 1){
                Toast.makeText(this, "El registro se elimino correctamente",
                        Toast.LENGTH_SHORT).show();
                ID.setText("");
                Nombre.setText("");
                Dueño.setText("");
                Domicilio.setText("");
                Especies.setText("");

                Intent intent = new Intent(ModificarDatos.this, TablaVeterinario.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "El ID que intenta eliminar no existe",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Campo ID usuario no puede estar vacio",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void BuscarMascota(View view) {

        AdminSQLiteOnHelper admin = new AdminSQLiteOnHelper(this, "Veterinaria", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String IDmascota = ID.getText().toString();

        if(!IDmascota.isEmpty()){
            Cursor fila = BaseDatos.rawQuery("Select NombreMascota, DueñoMascota, DomicilioMascota, EspecieMascota from Mascotas where idMascota="+ IDmascota, null);
            if(fila.moveToFirst()){
                Nombre.setText(fila.getString(0));
                Dueño.setText(fila.getString(1));
                Domicilio.setText(fila.getString(2));
                Especies.setText(fila.getString(3));
                BaseDatos.close();
            } else {
                Toast.makeText(this, "No se encontro el ID ingresado",
                        Toast.LENGTH_SHORT).show();
                BaseDatos.close();
            }

        } else {
            Toast.makeText(this, "Campo ID no puede estar vacio",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void VolverTabla(View view) {

        Intent intent = new Intent(ModificarDatos.this, TablaVeterinario.class);
        startActivity(intent);
    }
}