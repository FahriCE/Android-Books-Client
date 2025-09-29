package com.example.shelfmate.ui.authors;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shelfmate.R;
import com.example.shelfmate.model.Author;
import com.example.shelfmate.ui.adapters.BookAdapter;
import com.example.shelfmate.ui.adapters.BooksAuthorAdapter;

import java.util.ArrayList;
import java.util.NavigableMap;

public class AuthorsDetailsFragment extends Fragment {

    private AuthorsViewModel authorsViewModel;
    private BooksAuthorAdapter bookAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authors_details, container, false);

        authorsViewModel = new ViewModelProvider(requireActivity()).get(AuthorsViewModel.class);

        bookAdapter = new BooksAuthorAdapter(new ArrayList<>(), book -> {
            Bundle args = new Bundle();
            args.putInt("bookId", book.getId());
            Navigation.findNavController(view).navigate(R.id.action_authorsDetailsFragment_to_bookDetailsFragment, args);
        });


        RecyclerView booksRecyclerView = view.findViewById(R.id.rvwBooksIntoAuthors);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        booksRecyclerView.setAdapter(bookAdapter);

        authorsViewModel.getSelectedAuthor().observe(getViewLifecycleOwner(), author -> {
            if (author != null) {
                authorsViewModel.loadBooksByAuthor(author.getId());
            }
        });

        authorsViewModel.getBooksByAuthor().observe(getViewLifecycleOwner(), books -> {
            if (books != null) {
                bookAdapter.updateItems(books);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView authorFirstName = view.findViewById(R.id.firstname_author);
        TextView authorLastname = view.findViewById(R.id.lastname_author);
        Button btnDelete = view.findViewById(R.id.btnDeleteAuthor);

        authorsViewModel = new ViewModelProvider(requireActivity()).get(AuthorsViewModel.class);

        Bundle args = getArguments();
        if (args != null) {
            int authorId = args.getInt("authorId", -1);
            if (authorId != -1) {
                authorsViewModel.loadAuthorById(authorId);
            }
        }

        authorsViewModel.getSelectedAuthor().observe(getViewLifecycleOwner(), author -> {
            if (author != null) {
                authorFirstName.setText(author.getFirstname());
                authorLastname.setText(author.getLastname());
                btnDelete.setOnClickListener(v-> deleteAuthor(author.getId()));
            }
        });
    }

    private void deleteAuthor(int authorId) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirmation")
                .setMessage("Es-tu sûr(e) de vouloir supprimer cet auteur ? Cette action est irréversible.")
                .setPositiveButton("Supprimer", (dialog, which) -> {
                    MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
                    authorsViewModel.deleteAuthor(authorId, resultLiveData);

                    resultLiveData.observe(getViewLifecycleOwner(), deleted -> {
                        if (deleted != null && deleted) {
                            Toast.makeText(requireContext(), "Auteur supprimé avec succès", Toast.LENGTH_SHORT).show();
                            authorsViewModel.loadAuthors();
                            Navigation.findNavController(requireView()).popBackStack();
                        } else {
                            Toast.makeText(requireContext(), "Échec de la suppression", Toast.LENGTH_SHORT).show();
                        }
                    });
                })
                .setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss())
                .show();
    }

}