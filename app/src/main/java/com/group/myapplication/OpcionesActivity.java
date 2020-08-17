package com.group.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class OpcionesActivity extends AppCompatActivity {

    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7;
    private String pel, hor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        rb1 = (RadioButton)findViewById(R.id.rb_pelicula1);
        rb2 = (RadioButton)findViewById(R.id.rb_pelicula2);
        rb3 = (RadioButton)findViewById(R.id.rb_pelicula3);
        rb4 = (RadioButton)findViewById(R.id.rb_pelicula4);
        rb5 = (RadioButton)findViewById(R.id.rb_horario1);
        rb6 = (RadioButton)findViewById(R.id.rb_horario2);
        rb7 = (RadioButton)findViewById(R.id.rb_horario3);
    }

    //metodo para hacer reserva
    public void Reservar(View view) {

        LoginOpenHelper reserva = new LoginOpenHelper(this, "administracion",null, 1);
        SQLiteDatabase BDUsuarios = reserva.getWritableDatabase();

        if (rb1.isChecked()) {
            pel = rb1.getText().toString();}
        else if (rb2.isChecked()) {
            pel = rb2.getText().toString();}
        else if (rb3.isChecked()) {
            pel = rb3.getText().toString();}
        else if (rb4.isChecked()) {
            pel = rb4.getText().toString();}
        else if (rb5.isChecked()) {
            hor = rb5.getText().toString();}
        else if (rb6.isChecked()) {
            hor = rb6.getText().toString();}
        else if (rb7.isChecked()) {
            hor = rb7.getText().toString();}

        ContentValues reser = new ContentValues();

        reser.put("pel", pel);
        Toast.makeText(this, "pelicula = " + pel, Toast.LENGTH_SHORT).show();
        reser.put("hor", hor);
        BDUsuarios.insert("tabla_reservas", null, reser);

        Toast.makeText(this, "Reservacion exitosa", Toast.LENGTH_SHORT).show();
        BDUsuarios.close();
    }

    public void MostrarReservas(View view) {

        Intent siguiente = new Intent(this, ReservacionesActivity.class);
        startActivity(siguiente);
        //llamada al activity reservaciones

        //LoginOpenHelper reserva = new LoginOpenHelper(this, "administracion", null, 1);
        //SQLiteDatabase BDUsuarios = reserva.getWritableDatabase();
    }

}