package com.abhishek.evm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VoteActivityAdapter extends FirebaseRecyclerAdapter<DataModel, VoteActivityAdapter.myviewholder> {
    private Context context;

    public VoteActivityAdapter(Context context, @NonNull FirebaseRecyclerOptions<DataModel> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull DataModel model) {

        holder.name.setText(model.getName());
        holder.voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String voteCount = model.getVotes();
                int updateCount = Integer.parseInt(voteCount) + 1;

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Election").child("1").child("candidates").child(Integer.toString(1 + holder.getAbsoluteAdapterPosition()));
                myRef.child("votes").setValue(Integer.toString(updateCount));

                Intent intent = new Intent(view.getContext(), VoteSucess.class);
                intent.putExtra("key", model.getName());
                context.startActivity(intent);
                ((Activity)view.getContext()).finish();



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
