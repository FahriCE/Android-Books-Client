package com.example.shelfmate.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shelfmate.R;
import com.example.shelfmate.model.Tag;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {
    private List<Tag> tags;

    public TagAdapter(List<Tag> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_tag, parent, false);
        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        holder.bind(tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags != null ? tags.size() : 0;
    }

    public class TagViewHolder extends RecyclerView.ViewHolder {
        private final TextView tagNameTextView;
        private final TextView tagBooksCountTextView;

        public TagViewHolder(View itemView) {
            super(itemView);
            tagNameTextView = itemView.findViewById(R.id.tag_name);
            tagBooksCountTextView = itemView.findViewById(R.id.tag_books_count);
        }

        public void bind(Tag tag) {
            tagNameTextView.setText(tag.getName());
            if (tag.getBooksCount() > 0) {
                tagBooksCountTextView.setText(tag.getBooksCount() + " livres associés");
            } else {
                tagBooksCountTextView.setText("Aucun livre associé");
            }
        }
    }

    public void updateItems(List<Tag> newTags) {
        this.tags = newTags;
        notifyDataSetChanged();
    }
}
