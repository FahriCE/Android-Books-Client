package com.example.shelfmate.ui.authors;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shelfmate.R;
import com.example.shelfmate.model.Author;
import com.example.shelfmate.ui.adapters.AuthorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AuthorsFragment extends Fragment implements AuthorAdapter.OnAuthorClickListener {
    private AuthorAdapter adapter;
    private AuthorsViewModel authorsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authors, container, false);

        authorsViewModel = new ViewModelProvider(this).get(AuthorsViewModel.class);

        FloatingActionButton addButton = view.findViewById(R.id.fab_add_author);

        adapter = new AuthorAdapter(new ArrayList<>(), author -> {
            Bundle args = new Bundle();
            args.putInt("authorId", author.getId());
            Navigation.findNavController(view).navigate(R.id.action_authorsDetailsFragment_to_bookDetailsFragment, args);
        });

        RecyclerView authorsRecyclerView = view.findViewById(R.id.authors_recycler_view);
        authorsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        authorsRecyclerView.setAdapter(adapter);

        addButton.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_navigation_authors_to_newAuthorFragment)
        );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.authors_recycler_view);
        FloatingActionButton fabAddAuthor = view.findViewById(R.id.fab_add_author);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new AuthorAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        authorsViewModel = new ViewModelProvider(requireActivity()).get(AuthorsViewModel.class);

        fabAddAuthor.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_navigation_authors_to_newAuthorFragment)
        );

        observeAuthors();
    }

    private void observeAuthors() {
        if (authorsViewModel != null) {
            authorsViewModel.getAuthors().observe(getViewLifecycleOwner(), authors -> {
                if (authors != null) {
                    adapter.updateItems(new ArrayList<>(authors));
                } else {
                    Log.e("AuthorsFragment", "La liste d'auteurs est null");
                }
            });
        } else {
            Log.e("AuthorsFragment", "AuthorsViewModel est null");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAuthorClick(Author author) {
        Bundle args = new Bundle();
        args.putInt("authorId", author.getId());

        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_authors_to_authorsDetailsFragment, args);
    }
}