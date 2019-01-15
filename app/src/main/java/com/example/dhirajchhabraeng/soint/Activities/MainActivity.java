package com.example.dhirajchhabraeng.soint.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.engineer.dhirajchhabradevbestworld.soint.R;
import com.example.dhirajchhabraeng.soint.Adapters.MyPagerAdapter;
import com.example.dhirajchhabraeng.soint.Pojos.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.PorterDuff.Mode.SRC_IN;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private android.support.design.widget.TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton contactsFab;
    private Button logoutButton;
    private TextView personDisplayName;
    private FirebaseAuth firebaseAuth;
    private CircleImageView personImageToolBar;
    private ArrayList<Chat> chatsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_main);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        firebaseAuth = FirebaseAuth.getInstance();

        personImageToolBar = findViewById(R.id.person_image_tool_bar);

        Picasso.get()
                .load(firebaseAuth.getCurrentUser().getPhotoUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .into(personImageToolBar);

        personDisplayName = findViewById(R.id.current_user_display_name);

        personDisplayName.setText(firebaseAuth.getCurrentUser().getDisplayName());

        String urlString = "https://randomuser.me/api/portraits/";
        Drawable checkDrawable = this.getResources().getDrawable(R.drawable.ic_sentiment_dissatisfied_black_16dp);

        chatsList.add(new Chat("" + urlString, "Vinayak", "21:14", checkDrawable, 1));
        chatsList.add(new Chat("" + urlString, "Lavanya", "21:15", checkDrawable, 1));
        chatsList.add(new Chat("" + urlString, "Shabnam", "21:16", checkDrawable, 0));
        chatsList.add(new Chat("" + urlString, "Ayush Garg", "21:17", checkDrawable, 0));
        chatsList.add(new Chat("" + urlString, "Harshit Dwivedi Sir", "21:18", checkDrawable, 0));
        chatsList.add(new Chat("" + urlString, "Anmol", "21:19", checkDrawable, 0));
        chatsList.add(new Chat("" + urlString, "Prateek Bhatia", "21:10", checkDrawable, 0));
        chatsList.add(new Chat("" + urlString, "Raghav", "21:11", checkDrawable, 0));
        chatsList.add(new Chat("" + urlString, "Damanpreet Singh", "21:13", checkDrawable, 0));
        chatsList.add(new Chat("" + urlString, "Veerain", "21:15", checkDrawable, 0));

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), chatsList));

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_chat_bubble_outline_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_insert_emoticon_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_call_black_24dp);

        tabLayout.addOnTabSelectedListener(this);

        TabLayout.Tab tabChats = tabLayout.getTabAt(1);
        tabChats.select();

        LinearLayout layout = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = (float) 0.5;
        layout.setLayoutParams(layoutParams);

        contactsFab = findViewById(R.id.contacts_fab);
        contactsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        handleIntent(getIntent());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0: {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }

            default:
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorAccent), SRC_IN);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.getIcon().setColorFilter(getResources().getColor(R.color.colorUnselectedTab), SRC_IN);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onResume() {
        TabLayout.Tab tabChats = tabLayout.getTabAt(1);
        tabChats.select();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen_menu, menu);

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
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout_button) {
            firebaseAuth.signOut();

            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
