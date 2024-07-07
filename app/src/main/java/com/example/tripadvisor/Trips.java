package com.example.tripadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Trips extends AppCompatActivity {
    RecyclerView rvTrips;
    TripAdvisor tripsAdapter;
    GridLayoutManager glManager;
    SearchView svSearch;
    ImageView ivNav;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trips);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        init();
        ivNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_Venues) {
                } else if (id == R.id.nav_home) {
                    startActivity(new Intent(Trips.this, DashBoard.class));
                }else if (id == R.id.nav_AboutUs) {
                    startActivity(new Intent(Trips.this, about_us.class));
                }else if (id == R.id.nav_ContactUs) {
                    startActivity(new Intent(Trips.this, about_us.class));
                }else if (id == R.id.nav_LogOut) {
                    auth.signOut();
                    startActivity(new Intent(Trips.this, MainActivity.class));
                    finish();
                }
                else if (id == R.id.nav_signup) {
                    startActivity(new Intent(Trips.this, MainActivity.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tripsAdapter.getFilter().filter(newText);
                return false;
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init()
    {
        rvTrips = findViewById(R.id.rvTrips);
        ivNav = findViewById(R.id.ivNavButton);
        glManager = new GridLayoutManager(this, 2);
        rvTrips.setLayoutManager(glManager);
        drawerLayout = findViewById(R.id.drawer_layout);
        svSearch = findViewById(R.id.svSearch);

        tripsAdapter = new TripAdvisor(Trips.this, MyApplication.Trips);
        rvTrips.setAdapter(tripsAdapter);


        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}