package com.example.tripadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashBoard extends AppCompatActivity {
    RecyclerView rvVideos;
    VideoAdapter videoAdapter;
    LinearLayoutManager llmanager;
    ImageView ivNav;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    TextView navEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_board);
        init();

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

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
                if (id == R.id.nav_home) {
                    // Current activity, no action needed
                } else if (id == R.id.nav_ContactUs) {
                    startActivity(new Intent(DashBoard.this, Contact_Us.class)); // Corrected activity
                } else if (id == R.id.nav_AboutUs) {
                    startActivity(new Intent(DashBoard.this, about_us.class));
                } else if (id == R.id.nav_LogOut) {
                    auth.signOut();
                    startActivity(new Intent(DashBoard.this, MainActivity.class));
                    finish();
                } else if (id == R.id.nav_signup) {
                    startActivity(new Intent(DashBoard.this, MainActivity.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        View headerView = navigationView.getHeaderView(0);
        navEmail = headerView.findViewById(R.id.navEmail);

        if (user != null) {
            navEmail.setText(user.getEmail());
        }
    }

    private void init() {
        rvVideos = findViewById(R.id.rvVideos);
        ivNav = findViewById(R.id.ivNavButton);
        drawerLayout = findViewById(R.id.drawer_layout);
        llmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvVideos.setLayoutManager(llmanager);

        videoAdapter = new VideoAdapter(DashBoard.this, MyApplication.videos);
        rvVideos.setAdapter(videoAdapter);

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
