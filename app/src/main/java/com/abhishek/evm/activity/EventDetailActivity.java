package com.abhishek.evm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abhishek.evm.DataModel;
import com.abhishek.evm.R;
import com.abhishek.evm.adapters.EventDetailActivityAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EventDetailActivity extends AppCompatActivity {
    TextView text;
    RecyclerView recyclerView;
    ArrayList<DataModel> dataList;
    EventDetailActivityAdapter adapter;
    Button deleteButton;
    Button addButton;
    String childId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        recyclerView = findViewById(R.id.event_detail_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        deleteButton = findViewById(R.id.delete_button);
        addButton = findViewById(R.id.add_button);


        String name = getIntent().getStringExtra("key");
        childId = getIntent().getStringExtra("childId");
        setTitle(name);


        FirebaseRecyclerOptions<DataModel> options =
                new FirebaseRecyclerOptions.Builder<DataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Election").child(childId).child("candidates"),
                                DataModel.class).build();

        adapter = new EventDetailActivityAdapter(this, options);
        recyclerView.setAdapter(adapter);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                firebase.child(id).removeValue();
                FirebaseDatabase.getInstance().getReference("Election").child(childId).removeValue();
                finish();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventDetailActivity.this, AddCandidateActivity.class);
                intent.putExtra("childId", childId);
                startActivity(intent);

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}