package com.example.projectilm;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {


    DataBaseHelper db;
    EditText edtName,edtPas,edtMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        db = new DataBaseHelper(this);

        edtMail = findViewById(R.id.edtemail);
        edtName = findViewById(R.id.edtUSer);
        edtPas = findViewById(R.id.edtPass);
    }
    public void register(View view){
        String Email = edtMail.getText().toString();
        String Name =edtName.getText().toString();
        String Password = edtPas.getText().toString();

        if (Name.isEmpty()||Password.isEmpty()){
            Toast.makeText(Register.this ,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }else{
            boolean insertSucess = db.insertUser(Email,Name,Password);
            if(insertSucess){
                Toast.makeText(Register.this,"สมัครสมาชิกสำเร็จ!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(Register.this,"ไม่สามารถสมัครข้อมูลได้",Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void goHome(View view){
        Intent H0me = new Intent(this,MainActivity.class);
        startActivity(H0me);
    }

}