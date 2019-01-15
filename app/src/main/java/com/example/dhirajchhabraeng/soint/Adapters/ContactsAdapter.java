package com.example.dhirajchhabraeng.soint.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.engineer.dhirajchhabradevbestworld.soint.R;
import com.example.dhirajchhabraeng.soint.Activities.ContactsActivity;
import com.example.dhirajchhabraeng.soint.Pojos.Contacts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Contacts> contactsList;

    public ContactsAdapter(Context ctx, ArrayList<Contacts> contactsList) {
        this.ctx = ctx;
        this.contactsList = contactsList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        de.hdodenhof.circleimageview.CircleImageView personImageView;
        TextView personNameView, statusTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            personImageView = itemView.findViewById(R.id.person_image);
            personNameView = itemView.findViewById(R.id.person_name);
            statusTextView = itemView.findViewById(R.id.status_view);
        }
    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(ctx).inflate(R.layout.list_item_contacts, parent, false);

        ContactsAdapter.ViewHolder viewHolder = new ContactsAdapter.ViewHolder(inflatedView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        Picasso.get().load(contactsList.get(position).getPersonImageURL()).placeholder(R.mipmap.ic_launcher_round).into(holder.personImageView);
        holder.personNameView.setText(contactsList.get(position).getPersonName());
        holder.statusTextView.setText(contactsList.get(position).getPersonEmail());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

}
