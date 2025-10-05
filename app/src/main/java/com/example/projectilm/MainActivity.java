package com.example.projectilm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtUserLogin,edtPassLogin;
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
    public void regiSter(View view){
        Intent register = new Intent(this, Register.class);
        startActivity(register);
    }

    public void logIn(View view){
        String username = edtUserLogin.getText().toString();
        String password = edtPassLogin.getText().toString();

        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(MainActivity.this,"กรุณากรอกข้อมูล",Toast.LENGTH_LONG).show();
        }else {
            boolean isCheck = db.checkUsernamePassword(username,password);
            if(isCheck){
                Toast.makeText(MainActivity.this,"ล็อกอินสำเร็จ",Toast.LENGTH_LONG).show();
                Intent Login = new Intent(this, MainWindow.class);
                startActivity(Login);
                finish();
            }else {
                Toast.makeText(MainActivity.this,"ไม่สามารถสมัครข้อมูลได้",Toast.LENGTH_LONG).show();
            }
        }
    }


}