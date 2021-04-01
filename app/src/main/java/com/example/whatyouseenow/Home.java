package com.example.whatyouseenow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.what_see_you_now, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if(item.getItemId()== R.id.ProximaVer){
            Intent proxima = new Intent(this,Proxima_a_ver.class);
            proxima.addFlags(proxima.FLAG_ACTIVITY_CLEAR_TOP | proxima.FLAG_ACTIVITY_CLEAR_TASK);
            startActivityForResult(proxima,0);
        }
        else if (item.getItemId()== R.id.LogOut){
            Intent salir = new Intent(this, InicioSesion.class);
            salir.addFlags(salir.FLAG_ACTIVITY_CLEAR_TOP | salir.FLAG_ACTIVITY_CLEAR_TASK);
            startActivityForResult(salir,0);
        }
        else if(item.getItemId()== R.id.NoVer){
           Intent Nover = new Intent(this, No_ver.class);
           Nover.addFlags(Nover.FLAG_ACTIVITY_CLEAR_TOP | Nover.FLAG_ACTIVITY_CLEAR_TASK);
           startActivityForResult(Nover,0);
       }/*
       switch (item.getItemId()){
            case R.id.ProximaVer:
                //Toast.makeText(this, "proxima ver", Toast.LENGTH_LONG).show();
                Intent proxima = new Intent(this,Proxima_a_ver.class);
                proxima.addFlags(proxima.FLAG_ACTIVITY_CLEAR_TOP | proxima.FLAG_ACTIVITY_CLEAR_TASK);
                startActivityForResult(proxima,0);
          case R.id.NoVer:

               // Toast.makeText(this, "No ver", Toast.LENGTH_LONG).show();
            case R.id.LogOut:
                Intent salir = new Intent(this, InicioSesion.class);
                salir.addFlags(salir.FLAG_ACTIVITY_CLEAR_TOP | salir.FLAG_ACTIVITY_CLEAR_TASK);
                startActivityForResult(salir,0);
               // Toast.makeText(this, "Salir", Toast.LENGTH_LONG).show();
            default:
                return super.onOptionsItemSelected(item);
        }*/
       return super.onOptionsItemSelected(item);
    }
}