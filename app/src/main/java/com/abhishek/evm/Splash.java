package com.abhishek.evm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.abhishek.evm.activity.MainActivity;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // this code will be executed after 2 seconds
                Intent myIntent = new Intent(Splash.this, MainActivity.class);
                startActivity(myIntent);
                finish();

            }
        }, 1000);

        Objects.requireNonNull(getSupportActionBar()).hide();

    }
}