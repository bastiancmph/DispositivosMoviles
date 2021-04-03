package com.example.whatyouseenow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);

        Button entrar = (Button) findViewById(R.id.login);
        Button registarse = (Button) findViewById(R.id.registrarse);
        EditText username = (EditText)findViewById(R.id.editTextTextPersonName);
        EditText password = (EditText)findViewById(R.id.editTextTextPassword);


        //accion del boton que te inicia secion
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("cristian") && password.getText().toString().equals("123456")){
                    Toast.makeText(getApplicationContext(),"Entrando...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(v.getContext(), Home.class);
                    startActivityForResult(intent,0);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Credenciales incorrectas",Toast.LENGTH_SHORT).show();

                }
            }
        });
        //accion del boton que te envia a registrar
        registarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Registrarse.class);
                startActivityForResult(intent,0);
            }
        });


    }



}