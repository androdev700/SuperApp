package com.oldmen.superapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction mfragmentTransaction;
    private Menu mOptionsMenu;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (mOptionsMenu != null) {
                        mOptionsMenu.clear();
                        getMenuInflater().inflate(R.menu.menu_home, mOptionsMenu);
                    }
                    mfragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mfragmentTransaction.replace(R.id.main_frame, new HomeFragment());
                    mfragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    if (mOptionsMenu != null) {
                        mOptionsMenu.clear();
                        getMenuInflater().inflate(R.menu.menu_discovery, mOptionsMenu);
                    }
                    mfragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mfragmentTransaction.replace(R.id.main_frame, new HomeFragment());
                    mfragmentTransaction.commit();
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
                // Open Add Screen
                return true;
            case R.id.search_more:
                // Open Search Screen
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
