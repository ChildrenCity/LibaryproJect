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

    Cursor Customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_data);

        tvshowdb=findViewById(R.id.textView4);
        dbh = new DataBaseHelper(this);
        Customer = dbh.getAllUsers();

        if
        (Customer.getCount()==0){
            ShowInfo("Message","No Data");
        }else{
            StringBuffer datBuff = new StringBuffer();

            while (Customer.moveToNext()) {
                datBuff.append("รหัส : " + Customer.getString(0) + "\n");
                datBuff.append("ชื่อผู้ใช้ : " + Customer.getString(1) + "\n");
                datBuff.append("รหัสผ่าน : " + Customer.getString(2) + "\n");
                datBuff.append("__________________________________________________\n\n");
            }

            tvshowdb.setMovementMethod(new ScrollingMovementMethod());
            tvshowdb.setText("ชื้อผู้ใช้\n\n" + datBuff);
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
