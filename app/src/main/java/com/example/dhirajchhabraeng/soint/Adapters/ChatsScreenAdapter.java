package com.example.dhirajchhabraeng.soint.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.engineer.dhirajchhabradevbestworld.soint.R;
import com.example.dhirajchhabraeng.soint.Pojos.ImageMessage;
import com.example.dhirajchhabraeng.soint.Pojos.TextMessage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatsScreenAdapter extends RecyclerView.Adapter {

    ArrayList<Object> messagesList;
    Context ctx;

    public ChatsScreenAdapter(ArrayList<Object> messagesList, Context ctx) {
        this.messagesList = messagesList;
        this.ctx = ctx;
    }

    @Override
    public int getItemViewType(int position) {
        Object currentObject = messagesList.get(position);
        if (currentObject instanceof TextMessage) {
            if (((TextMessage) currentObject).getHorizontalPosition() == 0) {
                return 0;
            } else {
                return 1;
            }
        } else if (currentObject instanceof ImageMessage) {
            if (((ImageMessage) currentObject).getHorizontalPosition() == 0) {
                return 2;
            } else {
                return 3;
            }
        } else
            return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        if (i == 0) {
            View view = layoutInflater.inflate(R.layout.list_item_chats_text_others, viewGroup, false);
            return new TextHolder(view, 0);
        } else if (i == 1) {
            View view = layoutInflater.inflate(R.layout.list_item_chats_text_self, viewGroup, false);
            return new TextHolder(view, 1);
        } else if (i == 2) {
            View view = layoutInflater.inflate(R.layout.list_item_chats_image_others, viewGroup, false);
            return new ImageHolder(view, 0);
        } else if (i == 3) {
            View view = layoutInflater.inflate(R.layout.list_item_chats_image_self, viewGroup, false);
            return new ImageHolder(view, 1);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Object currentObject = messagesList.get(i);
        if (getItemViewType(i) == 0 || getItemViewType(i) == 1) {
            TextHolder th = (TextHolder) viewHolder;
            TextMessage tm = (TextMessage) currentObject;
            th.userName.setText(tm.getMessage());
        } else if (getItemViewType(i) == 2 || getItemViewType(i) == 3) {
            ImageHolder ih = (ImageHolder) viewHolder;
            ImageMessage im = (ImageMessage) currentObject;
            Picasso.get()
                    .load(im.getImageUrl())
                    .noFade()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ih.userImage);
        }
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        ImageView userImage;

        public ImageHolder(@NonNull View itemView, int horizontalPosition) {
            super(itemView);
            if (horizontalPosition == 0) {
                userImage = itemView.findViewById(R.id.single_chat_image_view_others);
            } else {
                userImage = itemView.findViewById(R.id.single_chat_image_view_self);
            }
        }
    }

    class TextHolder extends RecyclerView.ViewHolder {
        TextView userName;

        public TextHolder(@NonNull View itemView, int horizontalPosition) {
            super(itemView);
            if (horizontalPosition == 0) {
                userName = itemView.findViewById(R.id.single_chat_text_view_others);
            } else {
                userName = itemView.findViewById(R.id.single_chat_text_view_self);
            }
        }
    }
}
