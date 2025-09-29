package com.example.shelfmate.ui.books;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shelfmate.R;
import com.example.shelfmate.data.BooksRepository;
import com.example.shelfmate.model.Author;
import com.example.shelfmate.model.Book;
import com.example.shelfmate.model.Tag;
import com.example.shelfmate.network.ApiClient;

import java.util.List;
import java.util.stream.Collectors;

public class BookDetailsFragment extends Fragment {
    private TextView bookTitle, bookAuthor, bookYear, bookTags;
    private Button deleteBtn;
    private BooksListViewModel sharedViewModel;
    private Book currentBook;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupViewModel();
        handleBookSelection();
    }

    private void initViews(View view) {
        bookTitle = view.findViewById(R.id.book_title);
        bookAuthor = view.findViewById(R.id.book_author);
        bookYear = view.findViewById(R.id.book_publication_year);
        bookTags = view.findViewById(R.id.book_tag);
        deleteBtn = view.findViewById(R.id.delete_btn);
    }

    private void setupViewModel() {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(BooksListViewModel.class);
        sharedViewModel.getAuthors().observe(getViewLifecycleOwner(), authors -> {
            if (currentBook != null) {
                updateAuthorInfo(currentBook.getAuthorId());
            }
        });
    }

    private void handleBookSelection() {
        if (getArguments() != null && getArguments().containsKey("bookId")) {
            int bookId = getArguments().getInt("bookId");
            if (bookId != -1) {
                loadAndDisplayBook(bookId);
            } else {
                showError("ID de livre invalide");
            }
        } else {
            showError("Aucun livre sélectionné");
        }
    }

    private void loadAndDisplayBook(int bookId) {
        sharedViewModel.getSelectedBook().observe(getViewLifecycleOwner(), book -> {
            if (book != null) {
                currentBook = book;
                displayBookDetails(book);
                setupDeleteButton();
            } else {
                showError("Livre introuvable");
            }
        });
        sharedViewModel.loadBookDetails(bookId);
    }

    private void displayBookDetails(Book book) {
        bookTitle.setText(book.getTitle() != null ? book.getTitle() : "Titre inconnu");

        bookYear.setText(book.getPublicationYear() != 0 ?
                String.valueOf(book.getPublicationYear()) : "Année inconnue");

        if (book.getTags() != null && !book.getTags().isEmpty()) {
            String tagsText = book.getTags().stream()
                    .filter(tag -> tag.getName() != null)
                    .map(Tag::getName)
                    .collect(Collectors.joining(", "));
            bookTags.setText(tagsText.isEmpty() ? "Aucun tag valide" : tagsText);
        } else {
            bookTags.setText("Aucun tag");
        }

        updateAuthorInfo(book.getAuthorId());
    }

    private void updateAuthorInfo(int authorId) {
        List<Author> authors = sharedViewModel.getAuthors().getValue();
        if (authors != null) {
            for (Author author : authors) {
                if (author.getId() == authorId) {
                    bookAuthor.setText(author.getFirstname() + " " + author.getLastname());
                    return;
                }
            }
        }
        bookAuthor.setText("Auteur inconnu");
    }

    private void setupDeleteButton() {
        deleteBtn.setOnClickListener(v -> {
            BooksListViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(BooksListViewModel.class);

            sharedViewModel.deleteBook(currentBook.getId());
            Toast.makeText(requireContext(), "Livre supprimé", Toast.LENGTH_SHORT).show();

            v.postDelayed(() -> Navigation.findNavController(v).popBackStack(), 200);
        });
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
        Navigation.findNavController(requireView()).popBackStack();
    }
}