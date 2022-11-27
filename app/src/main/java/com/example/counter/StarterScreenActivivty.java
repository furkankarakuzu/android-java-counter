package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StarterScreenActivivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter_screen_activivty);

        (new Handler()).postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity((new Intent(StarterScreenActivivty.this, MainActivity.class)));
            }
        },2000);
    }
}