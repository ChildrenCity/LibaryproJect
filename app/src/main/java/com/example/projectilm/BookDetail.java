package com.example.projectilm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BookDetail extends AppCompatActivity {
    ImageView imageViewBookDetail;
    TextView tvDetailTitle,tvDetail;
    DataBaseHelper dbHelper;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);
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




        imageViewBookDetail = findViewById(R.id.imageViewBookDetail);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetail = findViewById(R.id.tvDetail);

        dbHelper = new DataBaseHelper(this);


        String bookTitle = getIntent().getStringExtra("BOOK_TITLE");

        if (bookTitle != null) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] projection = {
                    "BookImage",
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

                byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow("BookImage"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("Title"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("Description"));


                if (imageBytes != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    imageViewBookDetail.setImageBitmap(bitmap);
                }


                tvDetailTitle.setText(title);
                tvDetail.setText(description);
            }
            cursor.close();
        }
    }
}