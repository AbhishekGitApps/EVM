package com.abhishek.evm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.abhishek.evm.DataModel;
import com.abhishek.evm.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCandidateActivity extends AppCompatActivity {
    EditText name;
    Button saveButton;
    String childId;
    DataModel value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);

        name = findViewById(R.id.user_name);
        saveButton = findViewById(R.id.save);
        childId = getIntent().getStringExtra("childId");
        value = new DataModel();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value.setName(name.getText().toString());
                value.setVotes("0");
                value.setLogo("url");

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Election").child(childId).child("candidates");
//                myRef.child("1").setValue(value);
                myRef.push().setValue(value);
                finish();
            }
        });
    }
}