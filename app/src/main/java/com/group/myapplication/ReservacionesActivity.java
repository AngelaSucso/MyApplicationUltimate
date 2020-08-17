package com.group.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class ReservacionesActivity extends AppCompatActivity {

    private SQLiteDatabase conexion;
    private ListView lstDatos;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> res;
    private LoginOpenHelper datosOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservaciones);

        mostrarReservaciones();
    }

    public void mostrarReservaciones() {

        Toast.makeText(this, "entra a mostrar reservaciones", Toast.LENGTH_SHORT).show();
        lstDatos = (ListView) findViewById(R.id.listview);
        res = new ArrayList<String>();
        String sPelicula;
        String sHorario;

        try {
            datosOpenHelper = new LoginOpenHelper(this, "administracion", null, 1);
            conexion = datosOpenHelper.getWritableDatabase();
            String[] projection = {
                    FeedReaderContract.FeedEntry.COLUMN_DNI,
                    FeedReaderContract.FeedEntry.COLUMN_PELICULA,
                    FeedReaderContract.FeedEntry.COLUMN_HORARIO
            };

            Cursor resultado = conexion.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            Toast.makeText(this, "count = " + resultado.getCount(), Toast.LENGTH_SHORT).show();

            if (resultado.getCount() > 0) {
                resultado.moveToFirst();
                do {
                    sPelicula = resultado.getString(resultado.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_PELICULA));
                    sHorario = resultado.getString(resultado.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_HORARIO));

                    Toast.makeText(this, "pelicula es = ", Toast.LENGTH_SHORT).show();

                    res.add(sPelicula + ": " + sHorario);
                }
                while(resultado.moveToNext());
                resultado.close();
            }

            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,res);
            lstDatos.setAdapter(adaptador);
        }
        catch (Exception ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage(ex.getMessage());
            dlg.show();
        }
    }

    public void Regresar(View view) {
        Intent regresar = new Intent(this, OpcionesActivity.class);
        startActivity(regresar);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mostrarReservaciones();
    }
}
