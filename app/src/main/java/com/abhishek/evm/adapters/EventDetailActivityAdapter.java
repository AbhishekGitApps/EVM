package com.abhishek.evm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.evm.DataModel;
import com.abhishek.evm.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class EventDetailActivityAdapter extends FirebaseRecyclerAdapter<DataModel, EventDetailActivityAdapter.myviewholder> {
    private Context context;

    public EventDetailActivityAdapter(Context context, @NonNull FirebaseRecyclerOptions<DataModel> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull DataModel model) {

        holder.name.setText(model.getName());
//        holder.vote.setText(model.getVotes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), VoteSucess.class);
//                intent.putExtra("key", model.getName());
//                context.startActivity(intent);

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
