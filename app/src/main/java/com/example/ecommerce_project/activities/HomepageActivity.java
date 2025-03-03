package com.example.ecommerce_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ecommerce_project.R;
import com.example.ecommerce_project.fragments.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class HomepageActivity extends AppCompatActivity {

    Toolbar toolbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        auth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.Home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24);


        if (savedInstanceState == null) { // Prevents fragment reloading on screen rotation
            loadFragment(new HomeFragment());
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, fragment);
        transaction.addToBackStack(null); // Allows back navigation
        transaction.commit();
        Log.d("HomepageActivity", "HomeFragment loaded successfully");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_logout){
            auth.signOut();
            startActivity(new Intent(HomepageActivity.this, SignupActivity.class));
            finish();

        } else if (id == R.id.menu_my_cart) {
            startActivity(new Intent(HomepageActivity.this, CartActivity.class));
        }
        return true;
    }
}
