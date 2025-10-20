package com.example.projectilm;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainWindow extends AppCompatActivity {

    RecyclerView recyclerBooks;
    DataBaseHelper dbHelper;
    ArrayList<Book> bookList;
    BookAdapter adapter;
    androidx.appcompat.widget.SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

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

        adapter = new BookAdapter(this, bookList);
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
