package com.example.projectilm;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;

public class Profile extends AppCompatActivity {
    DataBaseHelper db;
    TextView tvEmail, tvUsername, tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new DataBaseHelper(this);

        tvEmail = findViewById(R.id.tvEmail);
        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);


        String email = getIntent().getStringExtra("email");
        if (email != null) {
            showUserData(email);
        }
    }

    private void showUserData(String email) {
        Cursor cursor = db.getUserByEmail(email);
        if (cursor.moveToFirst()) {
            String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL_Email));
            String userName = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL_Name));
            String userPassword = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL_Password));

            tvEmail.setText("📧 อีเมล: " + userEmail);
            tvUsername.setText("👤 ชื่อผู้ใช้: " + userName);
            tvPassword.setText("🔒 รหัสผ่าน: " + userPassword);
        }
        cursor.close();
    }
}