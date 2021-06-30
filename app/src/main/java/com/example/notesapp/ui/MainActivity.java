package com.example.notesapp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.notesapp.R;
import com.example.notesapp.domain.Note;
import com.example.notesapp.ui.details.NoteDetailsFragment;
import com.example.notesapp.ui.list.NoteListFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NoteListFragment.OnNoteClicked, PublisherHolder {

    private final Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (!isLandscape) {
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
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

    }

    @Override
    public void onNoteClicked(Note note) {

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (isLandscape) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_details_fragment, NoteDetailsFragment.newInstance(note))
                    .commit();

        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_list_fragment, NoteDetailsFragment.newInstance(note))
                    .addToBackStack(null)
                    .commit();
        }
    }





    @Override
    public Publisher getPublisher() {
        return publisher;
    }




}