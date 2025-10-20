package com.example.projectilm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtUserLogin, edtPassLogin;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        db = new DataBaseHelper(this);
        edtUserLogin = findViewById(R.id.edtUserLogin);
        edtPassLogin = findViewById(R.id.edtPasswordLogin);
    }

    public void regiSter(View view) {
        Intent register = new Intent(this, Register.class);
        startActivity(register);
    }

    public void logIn(View view) {
        String username = edtUserLogin.getText().toString();
        String password = edtPassLogin.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "กรุณากรอกข้อมูล", Toast.LENGTH_SHORT).show();
        } else {
            boolean isCheck = db.checkUsernamePassword(username, password);
            if (isCheck) {
                Toast.makeText(this, "ล็อกอินสำเร็จ", Toast.LENGTH_SHORT).show();
                Intent Login = new Intent(this, MainWindow.class);
                startActivity(Login);
                finish();
            } else {
                Toast.makeText(this, "เข้าสู่ระบบไม่สำเร็จ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clickview(View view) {
        Intent viewdata = new Intent(this, ViewData.class);
        startActivity(viewdata);
    }

}
