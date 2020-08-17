package com.group.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_dni, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_dni = (EditText)findViewById(R.id.txt_reg_dni);
        et_password = (EditText)findViewById((R.id.txt_password));
    }
    // metodo para boton entrar
    public void Entrar(View view) {
        LoginOpenHelper adminLogin = new LoginOpenHelper(this, "usuarios", null,1);
        SQLiteDatabase BDUsuarios = adminLogin.getWritableDatabase();
        Intent siguiente = new Intent(this, OpcionesActivity.class);

        String dni = et_dni.getText().toString();
        String pass = et_password.getText().toString();

        if(!dni.isEmpty() && !pass.isEmpty()) {

            Cursor fila = BDUsuarios.rawQuery
                    ("select password from tabla_usuarios where dni =" + dni, null);
                    // ("SELECT password FROM tabla_usuarios WHERE dni =" + dni, null);
            if (fila.moveToFirst()){
                //if(pass == fila.getString(0)) {
                if(pass.equals(fila.getString(0))) {
                    Toast.makeText(this, "Bienvenido usuario", Toast.LENGTH_SHORT).show();
                    startActivity(siguiente);    // entrar a la pagina de las peliculas
                } else {
                    Toast.makeText(this,"Password incorrecto tontin :v",Toast.LENGTH_SHORT).show();
                }
            }

/// nos quedamos aquiiii xddd
            /*
            ContentValues entrada = new ContentValues(); //para recuperar los datos
            entrada.put("dni", dni);
            entrada.put("password", password);

            BDUsuarios.insert("tabla_usuarios", null, entrada);

            BDUsuarios.close();
            et_dni.setText("");
            et_password.setText("");

            Toast.makeText(this,"Bienvenido usuario",Toast.LENGTH_SHORT).show();
            */
        } else {
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
    }
    public void boton_registrar(View view) {
        Intent siguiente = new Intent(this, RegistroActivity.class);
        startActivity(siguiente);
    }
}
