package com.example.projectilm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BookDetail extends AppCompatActivity {
    TextView tvDetailTitle,tvDetail;
    DataBaseHelper dbHelper;
    BottomNavigationView bottomNavigationView;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);
        email = getIntent().getStringExtra("email");
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    Intent homeIntent = new Intent(this, MainWindow.class);
                    homeIntent.putExtra("email", email);
                    startActivity(homeIntent);
                    return true;
                case R.id.profile:
                    Intent profileIntent = new Intent(this, Profile.class);
                    profileIntent.putExtra("email", email);
                    startActivity(profileIntent);
                    return true;
            }
            return false;
        });
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetail = findViewById(R.id.tvDetail);

        dbHelper = new DataBaseHelper(this);


        String bookTitle = getIntent().getStringExtra("BOOK_TITLE");

        if (bookTitle != null) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] projection = {
                    "Title",
                    "Description"
            };
            String selection = "Title = ?";
            String[] selectionArgs = {bookTitle};

            Cursor cursor = db.query(
                    "Books",
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {

                String title = cursor.getString(cursor.getColumnIndexOrThrow("Title"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("Description"));
                tvDetailTitle.setText(title);
                tvDetail.setText(description);
            }
            cursor.close();

        }
    }
    public void clickback(View view){
        Intent back = new Intent(this,MainWindow.class);
        back.putExtra("email", email);
        startActivity(back);
    }
}