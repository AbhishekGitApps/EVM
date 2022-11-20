package com.abhishek.evm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.abhishek.evm.R;

public class VoteSucessActivity extends AppCompatActivity {
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