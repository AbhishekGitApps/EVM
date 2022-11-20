package com.abhishek.evm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.abhishek.evm.DataModel;
import com.abhishek.evm.R;
import com.abhishek.evm.adapters.EventsActivityAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<DataModel> dataList;
    EventsActivityAdapter adapter;
    DataModel value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recyclerView = findViewById(R.id.events_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        value = new DataModel();

        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Election");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()) {
                    value.setName("College Election");
                    dbReference.child("1").setValue(value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {            }
        });

        FirebaseRecyclerOptions<DataModel> options =
                new FirebaseRecyclerOptions.Builder<DataModel>()
                        .setQuery(dbReference, DataModel.class).build();

        adapter = new EventsActivityAdapter(this, options, dbReference);
        recyclerView.setAdapter(adapter);

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
    protected  void onResume() {
        super.onResume();
    }

}