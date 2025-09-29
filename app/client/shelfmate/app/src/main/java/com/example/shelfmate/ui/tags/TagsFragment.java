package com.example.shelfmate.ui.tags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shelfmate.R;
import com.example.shelfmate.model.Tag;
import com.example.shelfmate.ui.adapters.TagAdapter;

import java.util.ArrayList;
import java.util.Comparator;

public class TagsFragment extends Fragment {
    private TagAdapter adapter;
    private TagsViewModel tagsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tags, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tagsViewModel = new ViewModelProvider(this).get(TagsViewModel.class);

        adapter = new TagAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        setupObservers();

        return view;
    }

    private void setupObservers() {
        tagsViewModel.getTagsWithBooks().observe(getViewLifecycleOwner(), tags -> {
            if (tags != null) {
                tags.sort(Comparator.comparing(Tag::getName));
                adapter.updateItems(tags);
            } else {
                adapter.updateItems(new ArrayList<>());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}