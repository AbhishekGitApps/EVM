package com.abhishek.evm.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.evm.DataModel;
import com.abhishek.evm.R;
import com.abhishek.evm.activity.EventDetailActivity;
import com.abhishek.evm.activity.VoteSucessActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class VoteActivityAdapter extends FirebaseRecyclerAdapter<DataModel, VoteActivityAdapter.myviewholder> {
    private Context context;
    String chidId;
    DatabaseReference dbReference;

    public VoteActivityAdapter(Context context, @NonNull FirebaseRecyclerOptions<DataModel> options, DatabaseReference dbReference) {
        super(options);
        this.context = context;
        this.dbReference = dbReference;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull DataModel model) {

        holder.name.setText(model.getName());
        holder.voteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Query queryUid = dbReference.orderByKey().limitToFirst(holder.getAbsoluteAdapterPosition() + 1);
                queryUid.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot datas : dataSnapshot.getChildren()) {
                            chidId = datas.getKey();
                        }

                        String voteCount = model.getVotes();
                        int updateCount = Integer.parseInt(voteCount) + 1;

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Election").child("1").child("candidates").child(chidId);
                        myRef.child("votes").setValue(Integer.toString(updateCount)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(view.getContext(), VoteSucessActivity.class);
                                intent.putExtra("key", model.getName());
                                context.startActivity(intent);
                            }
                        });


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


//                Toast.makeText(view.getContext(), model.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vote_layout, parent, false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        TextView name;
        Button voteButton;
        ImageView logo;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_textView);
            voteButton = itemView.findViewById(R.id.vote_button);
            logo = itemView.findViewById(R.id.logo);
        }
    }
}
