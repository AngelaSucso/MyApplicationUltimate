package com.group.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private EditText et2_nombre, et2_dni, et2_celular, et2_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et2_dni = (EditText) findViewById(R.id.txt_reg_dni);
        et2_nombre = (EditText) findViewById(R.id.txt_reg_nombre);
        et2_celular = (EditText) findViewById((R.id.txt_reg_celular));
        et2_password = (EditText) findViewById(R.id.txt_reg_password);
    }

    // metodo para boton entrar
    public void Registrar(View view) {
        LoginOpenHelper adminLogin = new LoginOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase BDUsuarios = adminLogin.getWritableDatabase();

        String dni = et2_dni.getText().toString();
        String nombre = et2_nombre.getText().toString();
        String celular = et2_celular.getText().toString();
        String password = et2_password.getText().toString();

        if (!nombre.isEmpty() && !dni.isEmpty() && !celular.isEmpty() && !password.isEmpty()) {
            ContentValues registro = new ContentValues(); //para recuperar los datos
            registro.put("dni", dni);
            registro.put("nombre", nombre);
            registro.put("celular", celular);
            registro.put("password", password);

            BDUsuarios.insert("tabla_usuarios", null, registro); //inserta datos en la tabla

            BDUsuarios.close();
            et2_nombre.setText("");
            et2_dni.setText("");
            et2_celular.setText("");
            et2_password.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    public void Regresar(View view) {
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}