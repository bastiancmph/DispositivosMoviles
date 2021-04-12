package com.example.whatyouseenow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registrarse extends AppCompatActivity {
    private EditText  nombre;
    private EditText  email;
    private EditText  edad;
    private EditText  contraseña;
    private Button registrar;

    // variables de datos a registrar
    private String name = "";
    private String gmail = "";
    private String anos= "";
    private String password = "";

    FirebaseAuth mAuth;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();

        nombre = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.Email);
        edad = (EditText) findViewById(R.id.edad);
        contraseña = (EditText) findViewById(R.id.contraseña);
        registrar = (Button) findViewById(R.id.enviar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nombre.getText().toString();
                gmail = email.getText().toString();
                anos = edad.getText().toString();
                password = contraseña.getText().toString();

                if(!name.isEmpty() && !gmail.isEmpty() && !anos.isEmpty() && !password.isEmpty()){
                   if (password.length() >= 6){
                       registerUser();
                   }
                    Toast.makeText(Registrarse.this,"debe tener 6 caracteres",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Registrarse.this,"debe completar los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(gmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String,Object> map = new HashMap<>();
                    map.put ("name", name);
                    map.put ("email", gmail);
                    map.put ("edad", anos);
                    map.put ("password", password);

                    String id = mAuth.getCurrentUser().getUid();
                    mData.child("User").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                startActivity(new Intent(Registrarse.this, Home.class));
                                finish();
                            }
                            else {
                                Toast.makeText(Registrarse.this,"no se pudo crear los datos correctamente",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Registrarse.this,"no se pudo registar el usuario",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}