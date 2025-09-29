package com.example.shelfmate.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shelfmate.R;
import com.example.shelfmate.model.Author;
import com.example.shelfmate.model.Book;
import com.example.shelfmate.ui.books.BooksListViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> books;
    private List<Author> authors;
    private OnBookClickListener listener;
    private BooksListViewModel viewModel;
    private LifecycleOwner lifecycleOwner;
    private boolean isForAuthorDetails;

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    // Constructeur pour gérer les livres et l'affichage détaillé de l'auteur
    public BookAdapter(List<Book> books, OnBookClickListener listener,
                       BooksListViewModel viewModel, LifecycleOwner lifecycleOwner,
                       boolean isForAuthorDetails) {
        this.books = books;
        this.listener = listener;
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        this.isForAuthorDetails = isForAuthorDetails;
    }

    // Constructeur pour gérer les livres avec une liste d'auteurs
    public BookAdapter(List<Book> books, List<Author> authors) {
        this.books = books;
        this.authors = authors;
        this.isForAuthorDetails = false;
    }

    public void setOnBookClickListener(OnBookClickListener listener) {
        this.listener = listener;
    }

    public void updateItems(List<Book> newBooks, List<Author> newAuthors) {
        this.books = newBooks;
        this.authors = newAuthors;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view, listener, viewModel, lifecycleOwner);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        Author author = findAuthorById(book.getAuthorId());

        if (holder.bookTitleTextView != null) {
            holder.bookTitleTextView.setText(book.getTitle());
        } else {
            Log.e("BookAdapter", "bookTitleTextView est null !");
        }

        if (holder.bookAuthorView != null) {
            if (author != null) {
                holder.bookAuthorView.setText(author.getFirstname() + " " + author.getLastname());
            } else {
                holder.bookAuthorView.setText("Auteur inconnu");
            }
        }

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

    public void updateItems(List<Book> newBooks) {
        books.clear();
        books.addAll(newBooks != null ? newBooks : new ArrayList<>());
        notifyDataSetChanged();
    }

    private Author findAuthorById(int authorId) {
        if (authors != null) {
            for (Author author : authors) {
                if (author.getId() == authorId) {
                    return author;
                }
            }
        }
        return null;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        private final TextView bookTitleTextView;
        private final TextView bookAuthorView;

        public BookViewHolder(View itemView, OnBookClickListener listener,
                              BooksListViewModel viewModel, LifecycleOwner lifecycleOwner) {
            super(itemView);
            bookTitleTextView = itemView.findViewById(R.id.txtTitleBook);
            bookAuthorView = itemView.findViewById(R.id.txtAuthorByBook);

            if (bookTitleTextView == null) {
                Log.e("BookViewHolder", "Erreur : book_title est introuvable dans le layout !");
            }
            if (bookAuthorView == null) {
                Log.e("BookViewHolder", "Erreur : book_author est introuvable dans le layout !");
            }

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onBookClick((Book) itemView.getTag());
                }
            });
        }
    }
}