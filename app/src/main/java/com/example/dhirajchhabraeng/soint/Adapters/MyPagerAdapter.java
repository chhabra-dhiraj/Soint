package com.example.dhirajchhabraeng.soint.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dhirajchhabraeng.soint.Fragments.CommonFragment;
import com.example.dhirajchhabraeng.soint.Pojos.Chat;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Chat> chatsList;

    public MyPagerAdapter(FragmentManager fm, ArrayList<Chat> chatsList) {
        super(fm);

        this.chatsList = chatsList;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new Fragment();
        }

        return CommonFragment.newInstance(chatsList);
    }

    @Override
    public int getCount() {
        return 4;
    }

}
