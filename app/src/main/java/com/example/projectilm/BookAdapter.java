package com.example.projectilm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private ArrayList<Book> books;

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.cost.setText(book.getCost() + " บาท");

        int resId = context.getResources().getIdentifier(
                book.getImageName(), "drawable", context.getPackageName());
        if (resId == 0) resId = R.drawable.ic_launcher_foreground; // กัน crash
        holder.image.setImageResource(resId);


    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void filterList(ArrayList<Book> filteredList) {
        books = filteredList;
        notifyDataSetChanged();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView title, author, cost;
        ImageView image;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            author = itemView.findViewById(R.id.tvAuthor);
            cost = itemView.findViewById(R.id.tvCost);
            image = itemView.findViewById(R.id.imgBook);
        }
    }
}
