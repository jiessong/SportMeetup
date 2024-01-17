package com.example.sportsdating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.sportsdating.datagenerator.DataGenerator;
import com.example.sportsdating.ui.GroupUpFragment;
import com.example.sportsdating.ui.MapsFragment;
import com.example.sportsdating.ui.MessagesFragment;
import com.example.sportsdating.ui.MineFragment;
import com.example.sportsdating.ui.MomentsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout mNavContainer;

//    private MapsFragment mapsFragment;
    private MomentsFragment momentsFragment;
    private MessagesFragment messagesFragment;
    private MineFragment mineFragment;
    private GroupUpFragment groupUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mapsFragment = new MapsFragment();
        momentsFragment = new MomentsFragment();
        messagesFragment = new MessagesFragment();
        mineFragment = new MineFragment();
        groupUpFragment = new GroupUpFragment();

        mNavContainer = findViewById(R.id.nav_container);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        switchFragment(groupUpFragment);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_item_groupup:
                    switchFragment(groupUpFragment);
                    break;
                case R.id.nav_item_Moments:
                    switchFragment(momentsFragment);
                    break;
                case R.id.nav_item_Message:
                    switchFragment(messagesFragment);
                    break;
                case R.id.nav_item_mine:
                    switchFragment(mineFragment);
                    break;
            }
            return true;
        }
    };

    private void switchFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_container,fragment).commitNow();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show();
        DataGenerator.getInstance().scheduledStream();
        int id = getIntent().getIntExtra("id", 0);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show();
        DataGenerator.getInstance().stopStream();
    }
}
