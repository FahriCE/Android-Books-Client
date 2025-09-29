package com.example.shelfmate.ui.authors;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shelfmate.R;
import com.example.shelfmate.model.Author;

public class NewAuthorFragment extends Fragment {
    private AuthorsViewModel authorsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_author, container, false);

        EditText firstnameText = view.findViewById(R.id.author_firstname);
        EditText lastnameText = view.findViewById(R.id.author_lastname);
        Button saveBtn = view.findViewById(R.id.save_author_btn);

        authorsViewModel = new ViewModelProvider(requireActivity()).get(AuthorsViewModel.class);

        saveBtn.setOnClickListener(v -> {
            String name = firstnameText.getText().toString().trim();
            String lastname = lastnameText.getText().toString().trim();

            if(!name.isEmpty() && !lastname.isEmpty()) {
                Author newAuthor = new Author(name, lastname);
                authorsViewModel.addAuthor(newAuthor);

                Toast.makeText(requireContext(), "Auteur ajouté", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(this).popBackStack();
            } else {
                Toast.makeText(requireContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}