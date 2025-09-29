package com.example.shelfmate.ui.books;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shelfmate.R;
import com.example.shelfmate.ui.adapters.BookAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BooksListFragment extends Fragment {

    private BookAdapter adapter;
    private BooksListViewModel booksViewModel;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_books_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fabAddBook = view.findViewById(R.id.fab_add_book);
        recyclerView = view.findViewById(R.id.books_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new BookAdapter(new ArrayList<>(), new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setOnBookClickListener(book -> {
            Bundle args = new Bundle();
            args.putInt("bookId", book.getId());
            Navigation.findNavController(view).navigate(
                    R.id.action_booksListFragment_to_bookDetailsFragment,
                    args
            );
        });

        booksViewModel = new ViewModelProvider(requireActivity()).get(BooksListViewModel.class);

        fabAddBook.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_booksListFragment_to_addBookFragment)
        );

        observeBooksData();
    }

    private void observeBooksData() {
        booksViewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            if (books != null) {
                booksViewModel.getAuthors().observe(getViewLifecycleOwner(), authors -> {
                    if (authors != null) {
                        adapter.updateItems(books, authors);
                        recyclerView.smoothScrollToPosition(0);
                    } else {
                        Log.e("BooksListFragment", "Authors list is null");
                        adapter.updateItems(books, new ArrayList<>());
                    }
                });
            } else {
                Log.e("BooksListFragment", "Books list is null");
                adapter.updateItems(new ArrayList<>(), new ArrayList<>());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }
}