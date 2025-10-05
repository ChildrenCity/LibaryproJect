package com.example.projectilm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class ViewData extends AppCompatActivity {

    TextView tvshowdb;
    DataBaseHelper dbh;

    Cursor students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_data);

        tvshowdb=findViewById(R.id.button4);
        dbh=new DataBaseHelper(this);
        students = dbh.student();

        if
        (students.getCount()==0){
            ShowInfo("Message","No Data");
        }else{
            StringBuffer datBuff=new StringBuffer();
            while (students.moveToNext()){
                datBuff.append("รหัส :" students.getString(0)+"\n");
                datBuff.append("username :" students.getString(1)+"\n");
                datBuff.append("password :" students.getString(2)+"\n");
                datBuff.append("__________________________________________________" students.getString(3)+"\n");
            }
            tvshowdb.setMovementMethod(new ScrollingMovementMethod());
            tvshowdb.setText("รายชื่อผู้ใช้");
        }
    }
    public void ShowInfo(String title,String msg){
        AlertDialog.Builder show = new AlertDialog.Builder(this);
        show.setCancelable(true);
        show.setTitle(title);
        show.setMessage(msg);
        show.show();
    }
    public void clickhome(View view){
        Intent Login = new Intent(this, MainActivity.class);
        startActivity(Login);
    }
}
