package com.example.projectilm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainWindow extends AppCompatActivity {

    RecyclerView recyclerBooks;
    DataBaseHelper dbHelper;
    ArrayList<Book> bookList;
    BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        recyclerBooks = findViewById(R.id.recyclerBooks);
        dbHelper = new DataBaseHelper(this);
        bookList = dbHelper.getAllBooks();

        adapter = new BookAdapter(this, bookList);
        recyclerBooks.setLayoutManager(new LinearLayoutManager(this));
        recyclerBooks.setAdapter(adapter);
    }
}
