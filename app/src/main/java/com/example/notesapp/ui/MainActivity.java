package com.example.notesapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements RouterHolder {

    private MainRouter router;

    public MainRouter getRouter() {
        return router;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        router = new MainRouter(getSupportFragmentManager());
        if (savedInstanceState== null) {router.showNotes();}


        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.app_name,
                R.string.app_name
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.settings) {
                    Toast.makeText(getApplicationContext(), "Open settings", Toast.LENGTH_LONG).show();
                    return true;
                }
                if (item.getItemId() == R.id.app_info) {
                    Toast.makeText(getApplicationContext(), "In development", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public MainRouter getMainRouter() {
        return router;
    }
}
