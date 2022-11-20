package com.abhishek.evm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.abhishek.evm.DataModel;
import com.abhishek.evm.R;
import com.abhishek.evm.adapters.ResultActivityAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<DataModel> dataList;
    ResultActivityAdapter adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = findViewById(R.id.result_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();

        FirebaseRecyclerOptions<DataModel> options =
                new FirebaseRecyclerOptions.Builder<DataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Election").child("1").child("candidates"),
                                DataModel.class).build();

        adapter1 = new ResultActivityAdapter(this, options);
        recyclerView.setAdapter(adapter1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        adapter1.stopListening();
    }

    @Override
    protected  void onResume() {
        super.onResume();
    }

}