package com.oldmen.superapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.oldmen.superapp.R;
import com.oldmen.superapp.ui.activity.addChannel.AddActivity;
import com.oldmen.superapp.ui.fragment.HomeFragment;
import com.oldmen.superapp.ui.fragment.ProfileFragment;
import com.oldmen.superapp.ui.fragment.discovery.DiscoveryFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction mFragmentTransaction;
    private Menu mOptionsMenu;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportActionBar().setTitle("Home");
                    if (mOptionsMenu != null) {
                        mOptionsMenu.clear();
                        getMenuInflater().inflate(R.menu.menu_home, mOptionsMenu);
                    }
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.main_frame, new HomeFragment());
                    mFragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportActionBar().setTitle("Discovery");
                    if (mOptionsMenu != null) {
                        mOptionsMenu.clear();
                        getMenuInflater().inflate(R.menu.menu_discovery, mOptionsMenu);
                    }
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.main_frame, new DiscoveryFragment());
                    mFragmentTransaction.commit();
                    return true;
                case R.id.navigation_profile:
                    getSupportActionBar().setTitle("Profile");
                    if (mOptionsMenu != null) {
                        mOptionsMenu.clear();
                    }
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.main_frame, new ProfileFragment());
                    mFragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        mOptionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_more:
                startActivity(new Intent(this, AddActivity.class));
                return true;
            case R.id.search_more:
                // Open Search Screen
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
