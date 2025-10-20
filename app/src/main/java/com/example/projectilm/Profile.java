package com.example.projectilm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    DataBaseHelper db;
    TextView tvEmail, tvUsername, tvPassword;
    EditText etUsername, etPassword, etPasswordConfirm;
    BottomNavigationView bottomNavigationView;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new DataBaseHelper(this);

        tvEmail = findViewById(R.id.tvemail);
        tvUsername = findViewById(R.id.tvusername);
        tvPassword = findViewById(R.id.tvpassword);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordconfirm);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        email = getIntent().getStringExtra("email");
        if (email != null) {
            loadUserData(email);
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent homeIntent = new Intent(this, MainWindow.class);
                    homeIntent.putExtra("email", email);
                    startActivity(homeIntent);
                case R.id.profile:
                    return true;
            }
            return false;
        });
    }

    private void loadUserData(String email) {
        Cursor cursor = db.getUserByEmail(email);
        if (cursor != null && cursor.moveToFirst()) {
            String username = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL_Name));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL_Password));

            tvEmail.setText("📧 " + email);
            tvUsername.setText("👤 " + username);
            tvPassword.setText("🔒 " + password);
        }
        if (cursor != null) cursor.close();
    }

    public void clickchangeusername(View view) {
        String newUsername = etUsername.getText().toString().trim();

        if (newUsername.isEmpty()) {
            Toast.makeText(this, "กรุณากรอกชื่อผู้ใช้ใหม่", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean updated = db.updateUser(email, newUsername, null);
        if (updated) {
            Toast.makeText(this, "อัปเดตชื่อผู้ใช้สำเร็จ ✅", Toast.LENGTH_SHORT).show();
            etUsername.setText("");
            loadUserData(email);
        } else {
            Toast.makeText(this, "เกิดข้อผิดพลาดในการอัปเดตชื่อ ❌", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickchangepassword(View view) {
        String newPassword = etPassword.getText().toString().trim();
        String confirmPassword = etPasswordConfirm.getText().toString().trim();

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "กรุณากรอกรหัสผ่านให้ครบ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "รหัสผ่านไม่ตรงกัน ❌", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean updated = db.updateUser(email, null, newPassword);
        if (updated) {
            Toast.makeText(this, "เปลี่ยนรหัสผ่านสำเร็จ 🎉", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            etPasswordConfirm.setText("");
            loadUserData(email);
        } else {
            Toast.makeText(this, "เกิดข้อผิดพลาดในการเปลี่ยนรหัส ❌", Toast.LENGTH_SHORT).show();
        }
    }

    public void clicklogout(View view) {
        Toast.makeText(this, "ออกจากระบบเรียบร้อย ✅", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
