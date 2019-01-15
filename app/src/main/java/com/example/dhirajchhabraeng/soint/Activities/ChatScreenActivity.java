package com.example.dhirajchhabraeng.soint.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.engineer.dhirajchhabradevbestworld.soint.R;
import com.example.dhirajchhabraeng.soint.Adapters.ChatsScreenAdapter;
import com.example.dhirajchhabraeng.soint.Pojos.ImageMessage;
import com.example.dhirajchhabraeng.soint.Pojos.TextMessage;


import java.util.ArrayList;

public class ChatScreenActivity extends AppCompatActivity {

    private RecyclerView chatScreenRecyclerView;
    private EditText inputMessageEditText;
    private FloatingActionButton sendingFab;

    private ArrayList<Object> messagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_chat_screen);
        setSupportActionBar(toolbar);

        final ChatsScreenAdapter chatsScreenAdapter = new ChatsScreenAdapter(messagesList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        chatScreenRecyclerView = findViewById(R.id.chat_screen_recycler_view);
        chatScreenRecyclerView.setLayoutManager(linearLayoutManager);
        chatScreenRecyclerView.setAdapter(chatsScreenAdapter);

        messagesList.add(new TextMessage("hello dhiraj", 0));
        messagesList.add(new ImageMessage("https://randomuser.me/api/portraits/women/14.jpg", 0));

        inputMessageEditText = findViewById(R.id.input_message_edit_text);
        sendingFab = findViewById(R.id.sending_fab);
        sendingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagesList.add(new TextMessage(inputMessageEditText.getText().toString(), 1));
                messagesList.add(new ImageMessage("https://randomuser.me/api/portraits/women/13.jpg", 1));
                chatsScreenAdapter.notifyDataSetChanged();
                inputMessageEditText.setText("");
                chatScreenRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        // Call smooth scroll
                        chatScreenRecyclerView.smoothScrollToPosition(chatsScreenAdapter.getItemCount());
                    }
                });
            }
        });



        //Enables the "up" button in a custom toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}