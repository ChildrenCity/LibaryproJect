package com.example.projectilm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiptActivity extends AppCompatActivity {

    TextView tvTitle, tvAuthor, tvCost;
    ImageView imgBook;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvCost = findViewById(R.id.tvCost);
        imgBook = findViewById(R.id.imgBook);
        btnConfirm = findViewById(R.id.btnConfirm);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        int cost = intent.getIntExtra("cost", 0);
        String image = intent.getStringExtra("image");

        tvTitle.setText(title);
        tvAuthor.setText("ผู้แต่ง: " + author);
        tvCost.setText("ราคา: " + cost + " บาท");

        int resId = getResources().getIdentifier(image, "drawable", getPackageName());
        imgBook.setImageResource(resId);

        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(this, "สั่งซื้อเรียบร้อย!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
