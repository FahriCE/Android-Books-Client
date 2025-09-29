package com.example.shelfmate.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shelfmate.R;
import com.example.shelfmate.model.Author;

import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {
    private List<Author> authors;
    private final OnAuthorClickListener listener;


    public interface OnAuthorClickListener {
        void onAuthorClick(Author author);
    }

    public AuthorAdapter(List<Author> authors, OnAuthorClickListener listener) {
        this.authors = authors;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_author, parent, false);
        return new AuthorViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        holder.bind(authors.get(position));
    }

    @Override
    public int getItemCount() {
        return authors != null ? authors.size() : 0;
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        private final TextView authorNameTextView;
        private Author author;

        public AuthorViewHolder(View itemView, OnAuthorClickListener listener) {
            super(itemView);
            authorNameTextView = itemView.findViewById(R.id.author_full_name);

            itemView.setOnClickListener(v -> {
                if(author != null) {
                    listener.onAuthorClick(author);
                }
            });
        }

        public void bind(Author author) {
            this.author = author;
            authorNameTextView.setText(author.getLastname().toUpperCase() + " " + author.getFirstname());
        }
    }

    public void updateItems(List<Author> newItems) {
        authors.clear();
        authors.addAll(newItems);
        notifyDataSetChanged();
    }
}