package com.abhishek.evm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abhishek.evm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {

    String pass;
    String name;
    Button login_button;
    EditText nameEditText;
    EditText passEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        nameEditText = findViewById(R.id.user_name);
        passEditText = findViewById(R.id.user_password);
        login_button = findViewById(R.id.login_btn);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("admin").child("name");
                DatabaseReference dbReference1 = FirebaseDatabase.getInstance().getReference("admin").child("pass");

                dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name = snapshot.getValue(String.class);
                        pass = snapshot.getValue(String.class);
                        if (nameEditText.getText().toString().equals(name) && passEditText.getText().toString().equals(pass)) {
                            Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Id/Password", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }
}