package com.example.whatyouseenow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Home extends AppCompatActivity implements Callback<PeliculasResponse> {

private FirebaseAuth mAuth;
private ArrayList<Result> Arraylistnover = new ArrayList<Result>();
    private ArrayList<Result> Arraylistver = new ArrayList<Result>();

private List<Result> peliculasencontradas;
    ImageView imagen;
    int index = 0;
    private boolean cargo = false;

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getApplicationContext();
        mAuth = FirebaseAuth.getInstance();

        Call<PeliculasResponse> call = DiagnosticVetApiAdapter.getApiService().getPeliculasResponse();
        call.enqueue(this);


        imagen = findViewById(R.id.tarimg);
        TextView titulo = (TextView)findViewById(R.id.info_text);
        TextView puntuacion = (TextView)findViewById(R.id.puntuacion);

        Button entrar = (Button) findViewById(R.id.nextt);
        Button anterior= (Button) findViewById(R.id.prevv);
        ImageButton Nover= (ImageButton) findViewById(R.id.noverBo);
        ImageButton proxima = (ImageButton) findViewById(R.id.vermastarde) ;

        Nover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arraylistnover.add(peliculasencontradas.get(index));

            }
        });

        proxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arraylistver.add(peliculasencontradas.get(index));
            }
        });






        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cargo && peliculasencontradas.size()>index){

                    index++;
                    titulo.setText(peliculasencontradas.get(index).getTitle());
                    System.out.println(String.valueOf(String.valueOf(peliculasencontradas.get(index).getVote_average())));
                    puntuacion.setText(String.valueOf(peliculasencontradas.get(index).getVote_average()));
                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/original/"+peliculasencontradas.get(index).getPosterPath())
                            //.resize(1080,2160)
                            .into(imagen);



                    Log.d("index", ""+index);
                }
            }
        });


        Nover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arraylistnover.add(peliculasencontradas.get(index));

            }
        });




        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cargo && index>0){
                    index--;
                    titulo.setText(peliculasencontradas.get(index).getTitle());
                    puntuacion.setText(String.valueOf(peliculasencontradas.get(index).getVote_average()));
                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/original/"+peliculasencontradas.get(index).getPosterPath())
                            //.resize(1080,2160)
                            .into(imagen);


                    Log.d("index", ""+peliculasencontradas.get(index).getVote_average());
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
            proxima.putExtra("vector1",Arraylistver.get(index).getPosterPath());
           // proxima.addFlags(proxima.FLAG_ACTIVITY_CLEAR_TOP | proxima.FLAG_ACTIVITY_CLEAR_TASK);
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
           Intent Nover = new Intent(getApplicationContext(), No_ver.class);
           Nover.putExtra("vector",Arraylistnover.get(index).getPosterPath());
           //Nover.addFlags(Nover.FLAG_ACTIVITY_CLEAR_TOP | Nover.FLAG_ACTIVITY_CLEAR_TASK);

           startActivity(Nover);
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

            Picasso.get()
                    .load("https://image.tmdb.org/t/p/original/"+peliculasencontradas.get(0).getPosterPath())
                    //.resize(1080,2160)
                    .into(imagen);

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