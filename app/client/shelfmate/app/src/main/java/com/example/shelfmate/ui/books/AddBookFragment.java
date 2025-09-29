package com.example.shelfmate.ui.books;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.shelfmate.R;
import com.example.shelfmate.model.Author;
import com.example.shelfmate.model.Book;
import java.util.ArrayList;
import java.util.List;

public class AddBookFragment extends Fragment {
    private BooksListViewModel booksViewModel;
    private List<Author> allAuthors = new ArrayList<>();
    private Author selectedAuthor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        EditText titleText = view.findViewById(R.id.new_title_txt);
        EditText yearText = view.findViewById(R.id.new_publication_year);
        AutoCompleteTextView authorDropdown = view.findViewById(R.id.author_txt);
        Button saveBtn = view.findViewById(R.id.submit_book_btn);

        booksViewModel = new ViewModelProvider(requireActivity()).get(BooksListViewModel.class);

        ArrayAdapter<Author> authorAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                allAuthors
        );
        authorDropdown.setAdapter(authorAdapter);

        booksViewModel.getAuthors().observe(getViewLifecycleOwner(), authors -> {
            if (authors != null) {
                allAuthors.clear();
                allAuthors.addAll(authors);
                authorAdapter.notifyDataSetChanged();
            }
        });

        authorDropdown.setOnItemClickListener((parent, view1, position, id) -> {
            selectedAuthor = authorAdapter.getItem(position);
            Log.d("AddBookFragment", "Auteur sélectionné: " + selectedAuthor.getId());
        });

        saveBtn.setOnClickListener(v -> {
            String title = titleText.getText().toString().trim();
            String yearString = yearText.getText().toString().trim();

            if (!title.isEmpty() && !yearString.isEmpty() && selectedAuthor != null) {
                try {
                    int publicationYear = Integer.parseInt(yearString);

                    Book newBook = new Book();
                    newBook.setTitle(title);
                    newBook.setPublicationYear(publicationYear);
                    newBook.setAuthorId(selectedAuthor.getId());

                    booksViewModel.addBook(newBook);

                    Toast.makeText(requireContext(), "Livre ajouté avec succès", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(this).popBackStack();
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "Année de publication invalide", Toast.LENGTH_LONG).show();
                }
            } else {
                StringBuilder errorMsg = new StringBuilder("Veuillez ");
                if (title.isEmpty()) errorMsg.append("remplir le titre, ");
                if (yearString.isEmpty()) errorMsg.append("remplir l'année, ");
                if (selectedAuthor == null) errorMsg.append("sélectionner un auteur");

                String message = errorMsg.toString().replaceAll(", $", "");
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}