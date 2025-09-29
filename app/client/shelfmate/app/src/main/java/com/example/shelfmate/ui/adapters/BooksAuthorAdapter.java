package com.example.shelfmate.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shelfmate.R;
import com.example.shelfmate.model.Book;

import java.util.List;

public class BooksAuthorAdapter extends RecyclerView.Adapter<BooksAuthorAdapter.BookViewHolder> {

    private List<Book> books;
    private final OnBookClickListener listener;

    public BooksAuthorAdapter(List<Book> books, OnBookClickListener listener) {
        this.books = books;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_book_by_author, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookTitleTextView.setText(book.getTitle());
        holder.bookYearTextView.setText(String.valueOf(book.getPublicationYear()));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBookClick(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }

    public void updateItems(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private final TextView bookTitleTextView;
        private final TextView bookYearTextView;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookTitleTextView = itemView.findViewById(R.id.txtTitleBook);
            bookYearTextView = itemView.findViewById(R.id.txtYearBook);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBookClick(books.get(getAdapterPosition()));
                }
            });
        }
    }
}
