package com.example.tripadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Detailsoftrips extends AppCompatActivity {
    TextView tvLocation, tvFrom, tvPointA, tvPointB, tvDescription, tvPrice, tvDays;
    ImageView ivTripImage;
    Button btnBookTrip;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    ImageView ivNav;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailsoftrips);
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
                    // Handle home click
                } else if (id == R.id.rvTrips) {
                    startActivity(new Intent(Detailsoftrips.this, Trips.class));
                } else if (id == R.id.nav_ContactUs) {
                    startActivity(new Intent(Detailsoftrips.this, Contact_Us.class));
                }  else if (id == R.id.nav_AboutUs) {
                    startActivity(new Intent(Detailsoftrips.this, about_us.class));
                } else if (id == R.id.nav_LogOut) {
                    auth.signOut();
                    startActivity(new Intent(Detailsoftrips.this, MainActivity.class));
                    finish();
                } else if (id == R.id.nav_signup) {
                    startActivity(new Intent(Detailsoftrips.this, MainActivity.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



        String location = getIntent().getStringExtra("location");
        String from = getIntent().getStringExtra("from");
        String pointA = getIntent().getStringExtra("point_a");
        String pointB = getIntent().getStringExtra("point_b");
        String description = getIntent().getStringExtra("description");
        int price = getIntent().getIntExtra("price", -1);
        int days = getIntent().getIntExtra("days", -1);
        int imageResId = getIntent().getIntExtra("image_res_id", -1);

        tvLocation.setText(location);
        tvFrom.setText(from);
        tvPointA.setText(pointA);
        tvPointB.setText(pointB);
        tvDescription.setText(description);
        tvPrice.setText(String.valueOf(price));
        tvDays.setText(String.valueOf(days));

        if (imageResId != -1) {
            ivTripImage.setImageResource(imageResId);
        }





    }

    private void init() {
        tvLocation = findViewById(R.id.tvLocation);
        tvFrom = findViewById(R.id.tvFrom);
        tvPointA = findViewById(R.id.tvPointA);
        tvPointB = findViewById(R.id.tvPointB);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        tvDays = findViewById(R.id.tvDays);
        ivTripImage = findViewById(R.id.ivTripImage);
        btnBookTrip = findViewById(R.id.btnBookTrip);
        ivNav = findViewById(R.id.ivNavButton);
        drawerLayout = findViewById(R.id.drawer_layout);

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