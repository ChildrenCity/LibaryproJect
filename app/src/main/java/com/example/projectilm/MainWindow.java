package com.example.projectilm;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;


public class MainWindow extends AppCompatActivity {

    RecyclerView recyclerBooks;
    DataBaseHelper dbHelper;
    ArrayList<Book> bookList;
    BookAdapter adapter;
    androidx.appcompat.widget.SearchView searchView;

    BottomNavigationView bottomNavigationView;
    String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
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


        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterBooks(newText);
                return true;
            }
        });

        recyclerBooks = findViewById(R.id.recyclerBooks);
        dbHelper = new DataBaseHelper(this);
        bookList = dbHelper.getAllBooks();

        adapter = new BookAdapter(this, bookList,email);
        recyclerBooks.setLayoutManager(new LinearLayoutManager(this));
        recyclerBooks.setAdapter(adapter);
    }


    private void filterBooks(String text) {
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(book);
            }
        }

        adapter.filterList(filteredList);
    }


}
