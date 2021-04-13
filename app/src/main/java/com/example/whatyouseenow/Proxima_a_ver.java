package com.example.whatyouseenow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Proxima_a_ver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxima_a_ver);

        Bundle datos = new Bundle();

        ImageView imagensita = (ImageView) findViewById(R.id.imageView3);

        if(getIntent().getExtras().getString("vector1")!= null){
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/original/"+ getIntent().getExtras().getString("vector1"))
                    //.resize(1080,2160)
                    .into(imagensita);
        }

    }
}