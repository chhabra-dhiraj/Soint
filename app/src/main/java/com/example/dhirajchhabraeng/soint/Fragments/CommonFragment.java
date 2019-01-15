package com.example.dhirajchhabraeng.soint.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.engineer.dhirajchhabradevbestworld.soint.R;
import com.example.dhirajchhabraeng.soint.Adapters.CommonListAdapter;
import com.example.dhirajchhabraeng.soint.Pojos.Chat;
import java.util.ArrayList;

public class CommonFragment extends Fragment {

    ArrayList<Chat> chatsList;

    public static CommonFragment newInstance(ArrayList<Chat> chatsList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("chatsArrayList", chatsList);

        CommonFragment fragment = new CommonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_common, container, false);

        chatsList = getArguments().getParcelableArrayList("chatsArrayList");

        CommonListAdapter chatsAdapter = new CommonListAdapter(getContext(), chatsList);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        RecyclerView chatsRecyclerView = view.findViewById(R.id.recycler_view);

        chatsRecyclerView.addItemDecoration(new DividerItemDecoration(chatsRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        chatsRecyclerView.setLayoutManager(llm);
        chatsRecyclerView.setAdapter(chatsAdapter);

        return view;
    }

}
