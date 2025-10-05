package com.example.projectilm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


    }
    public void regiSter(View view){
        Intent register = new Intent(this, Register.class);
        startActivity(register);
    }

    public void logIn(View view){
        Intent Login = new Intent(this, MainActivity.class);
        startActivity(Login);
    }
    public void clickview(View view){
        Intent Login = new Intent(this, ViewData.class);
        startActivity(Login);
    }


}