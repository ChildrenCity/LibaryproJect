package com.example.projectilm;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;

public class Favorite extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorite);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    startActivity(new Intent(this, MainWindow.class));
                    return true;
                case R.id.favorite:
                    startActivity(new Intent(this, Favorite.class));
                    return true;
                case R.id.profile:
                    startActivity(new Intent(this, Profile.class));
                    return true;
            }
            return false;
        });

    }
}