package com.example.tripadvisor;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Contact_Us<ContactUsData> extends AppCompatActivity {
    ImageView ivNav;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;

    EditText etFullName, etEmail, etPhone, etHearAboutUs;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_us);
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
                if (id == R.id.nav_ContactUs) {
                    // Current activity, no action needed
                } else if (id == R.id.nav_Venues) {
                    startActivity(new Intent(Contact_Us.this, Trips.class));
                } else if (id == R.id.nav_home) {
                    startActivity(new Intent(Contact_Us.this, DashBoard.class));
                } else if (id == R.id.nav_AboutUs) {
                    startActivity(new Intent(Contact_Us.this, about_us.class));
                }  else if (id == R.id.nav_LogOut) {
                    auth.signOut();
                    startActivity(new Intent(Contact_Us.this, MainActivity.class));
                    finish();
                } else if (id == R.id.nav_signup) {
                    startActivity(new Intent(Contact_Us.this, Sign_Up.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String phone = etPhone.getText().toString();
                String hearAboutUs = etHearAboutUs.getText().toString().trim();

                if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || hearAboutUs.isEmpty()) {
                    Toast.makeText(Contact_Us.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                ProgressDialog contact = new ProgressDialog(Contact_Us.this);
                contact.setMessage("Sending Response.....");
                contact.show();

                String key = reference.push().getKey();



            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("WrongViewCast")
    private void init() {
        ivNav = findViewById(R.id.ivNavButton);
        drawerLayout = findViewById(R.id.drawer_layout);
        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etHearAboutUs = findViewById(R.id.etHearAboutUs);

        btnSubmit = findViewById(R.id.submitButton);
    }
}
