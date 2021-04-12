package com.example.whatyouseenow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Home extends AppCompatActivity implements Callback<PeliculasResponse> {

private FirebaseAuth mAuth;

private List<Result> peliculasencontradas;
    ImageView imagen;
    int index = 0;
    private boolean cargo = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        Call<PeliculasResponse> call = DiagnosticVetApiAdapter.getApiService().getPeliculasResponse();
        call.enqueue(this);


        imagen = findViewById(R.id.tarimg);
        TextView titulo = (TextView)findViewById(R.id.info_text);

        Button entrar = (Button) findViewById(R.id.nextt);
        Button anterior= (Button) findViewById(R.id.prevv);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cargo && peliculasencontradas.size()>index){
                    titulo.setText(peliculasencontradas.get(index).getTitle());
                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/original/"+peliculasencontradas.get(index).getPosterPath())
                            //.resize(1080,2160)
                            .into(imagen);
                    index++;

                    Log.d("index", ""+index);
                }
            }
        });


        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cargo && index>0){
                    titulo.setText(peliculasencontradas.get(index).getTitle());
                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/original/"+peliculasencontradas.get(index).getPosterPath())
                            //.resize(1080,2160)
                            .into(imagen);
                    index--;

                    Log.d("index", ""+index);
                }
            }
        });




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
            mAuth.signOut();
            Intent salir = new Intent(this, InicioSesion.class);
            salir.addFlags(salir.FLAG_ACTIVITY_CLEAR_TOP | salir.FLAG_ACTIVITY_CLEAR_TASK);
            startActivityForResult(salir,0);
            finish();
        }
        else if(item.getItemId()== R.id.NoVer){
           Intent Nover = new Intent(this, No_ver.class);
           Nover.addFlags(Nover.FLAG_ACTIVITY_CLEAR_TOP | Nover.FLAG_ACTIVITY_CLEAR_TASK);
           startActivityForResult(Nover,0);
       }





        /*




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


    @Override
    public void onResponse(Call<PeliculasResponse> call, Response<PeliculasResponse> response) {

        if (response.isSuccessful()){
            peliculasencontradas = response.body().getResults();

            Log.d("ssa","dsd " + peliculasencontradas.size());
            cargo = true;
            Log.d("ssss","FUNCIONO");
        }
    }

    @Override
    public void onFailure(Call<PeliculasResponse> call, Throwable t) {
        Log.d("ssss","NONAS FUNCIONO");
        cargo = false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}