package com.example.dhirajchhabraeng.soint.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.engineer.dhirajchhabradevbestworld.soint.R;
import com.example.dhirajchhabraeng.soint.Activities.ChatScreenActivity;
import com.example.dhirajchhabraeng.soint.Pojos.Chat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommonListAdapter extends RecyclerView.Adapter<CommonListAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Chat> chatsList;

    public CommonListAdapter(Context context, ArrayList<Chat> chatsList) {
        this.ctx = context;
        this.chatsList = chatsList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        de.hdodenhof.circleimageview.CircleImageView personImageView;
        ImageView seenFlagImageView;
        TextView personNameView, timeTextView, lastMessageTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            personImageView = itemView.findViewById(R.id.person_image);
            personNameView = itemView.findViewById(R.id.person_name);
            timeTextView = itemView.findViewById(R.id.time_view);
            lastMessageTextView = itemView.findViewById(R.id.lastMessage_view);
            seenFlagImageView = itemView.findViewById(R.id.seen_flag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, ChatScreenActivity.class);
                    ctx.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(ctx).inflate(R.layout.list_item_chats, parent, false);

        ViewHolder viewHolder = new ViewHolder(inflatedView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(chatsList.get(position).getPersonImageURL()).placeholder(R.mipmap.ic_launcher_round).into(holder.personImageView);
        holder.personNameView.setText(chatsList.get(position).getPersonName());
        holder.timeTextView.setText(chatsList.get(position).getTimeText());
        holder.seenFlagImageView.setImageDrawable(chatsList.get(position).getSeenFlag());
        holder.lastMessageTextView.setText(chatsList.get(position).getLastMessage());
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }

}

