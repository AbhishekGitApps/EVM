package com.abhishek.evm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class VoteSucess extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_sucess);

        String name = getIntent().getStringExtra("key");

        text = findViewById(R.id.text);
        text.setText("You have successfully voted for: " + name);

    }
}