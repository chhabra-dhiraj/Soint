package com.example.dhirajchhabraeng.soint.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageButton;

import com.engineer.dhirajchhabradevbestworld.soint.R;
import com.example.dhirajchhabraeng.soint.Adapters.ContactsAdapter;
import com.example.dhirajchhabraeng.soint.Pojos.Contacts;

import com.example.dhirajchhabraeng.soint.Pojos.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    private RecyclerView contactsRecyclerView;
    private ImageButton addPeopleChatsButton;
    private FirebaseDatabase firebaseDatabase;
    private ContactsAdapter contactsAdapter;
    private ArrayList<Contacts> contactsList = new ArrayList<>();
    private ArrayList<User> usersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_contacts);
        setSupportActionBar(toolbar);

        //Enables the "up" button in a custom toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        contactsAdapter = new ContactsAdapter(this, contactsList);

        contactsRecyclerView = findViewById(R.id.contacts_recycler_view);

        contactsRecyclerView.addItemDecoration(new DividerItemDecoration(contactsRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsRecyclerView.setAdapter(contactsAdapter);

        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference rootReference = firebaseDatabase.getReference();

        rootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                usersList.add(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            int i = 0;

            while (i < usersList.size() - 1) {
                User currentUser = usersList.get(i);

                if (currentUser.getCurrentUserDisplayName().equals(query)) {
                    contactsList.add(new Contacts(currentUser.getCurrentUserPhotoUrl(),
                            currentUser.getCurrentUserDisplayName(),
                            currentUser.getCurrentUserEmail()));
                    contactsAdapter.notifyDataSetChanged();
                }
                i++;
            }
        }
    }
}

