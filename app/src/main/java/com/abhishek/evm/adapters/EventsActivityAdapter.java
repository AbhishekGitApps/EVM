package com.abhishek.evm.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.evm.DataModel;
import com.abhishek.evm.activity.EventDetailActivity;
import com.abhishek.evm.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EventsActivityAdapter extends FirebaseRecyclerAdapter<DataModel, EventsActivityAdapter.myviewholder> {
    String chidId;
    DatabaseReference dbReference;
    private Context context;

    public EventsActivityAdapter(Context context, @NonNull FirebaseRecyclerOptions<DataModel> options, DatabaseReference dbReference) {
        super(options);
        this.context = context;
        this.dbReference = dbReference;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull DataModel model) {

        holder.name.setText(model.getName());
//        holder.vote.setText(model.getVotes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Query queryUid= dbReference.orderByKey().limitToFirst(holder.getAbsoluteAdapterPosition() + 1);
                queryUid.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot datas : dataSnapshot.getChildren()) {
                            chidId =datas.getKey();
                        }
                        Intent intent = new Intent(view.getContext(), EventDetailActivity.class);
                        intent.putExtra("key", model.getName());
                        intent.putExtra("childId", chidId);
                        context.startActivity(intent);
//                        Toast.makeText(context.getApplicationContext(),test , Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_layout, parent, false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        TextView name, vote;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_textView);
            vote = itemView.findViewById(R.id.vote_textView);
        }
    }
}
