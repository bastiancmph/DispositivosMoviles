package com.example.whatyouseenow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class No_ver extends AppCompatActivity {

    //private Bundle datos = this.getIntent().getExtras();


    //private Button actualizar = (Button) findViewById(R.id.actualizar);


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getApplicationContext();
        setContentView(R.layout.activity_no_ver);

        Bundle datos = new Bundle();

        ImageView imagensita = (ImageView) findViewById(R.id.imageView7);

        if(getIntent().getExtras().getString("vector")!= null){
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/original/"+ getIntent().getExtras().getString("vector"))
                    //.resize(1080,2160)
                    .into(imagensita);
        }


    }



}