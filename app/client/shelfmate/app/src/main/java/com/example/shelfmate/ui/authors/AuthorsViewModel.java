package com.example.shelfmate.ui.authors;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.shelfmate.data.BooksRepository;
import com.example.shelfmate.model.Author;
import com.example.shelfmate.model.Book;
import com.example.shelfmate.network.ApiClient;
import com.example.shelfmate.ui.books.BooksListViewModel;

import java.util.List;

public class AuthorsViewModel extends ViewModel {
    private final BooksRepository booksRepository;
    private final MutableLiveData<List<Author>> authorsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Book>> booksByAuthorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Author> selectedAuthor = new MutableLiveData<>();


    public AuthorsViewModel() {
        this.booksRepository = new BooksRepository(ApiClient.getApiService());
        loadAuthors();
    }

    public LiveData<List<Author>> getAuthors() {
        return authorsLiveData;
    }

    public void loadAuthors() {
        booksRepository.getAllAuthors(authorsLiveData);

        authorsLiveData.observeForever(authors -> {
            Log.d("AuthorsViewModel", "Liste des auteurs mise à jour : " + authors.size());
        });
    }

    public void addAuthor(Author author) {
        booksRepository.addAuthor(author, authorsLiveData);

        authorsLiveData.observeForever(authors -> {
            Log.d("AuthorsViewModel", "Liste des auteurs mise à jour : " + authors.size());
        });
    }

    public void deleteAuthor(int authorId, MutableLiveData<Boolean> resultLiveData) {
        booksRepository.deleteAuthor(authorId, resultLiveData);
    }

    public LiveData<List<Book>> getBooksByAuthor() {
        return booksByAuthorLiveData;
    }

    public void loadBooksByAuthor(int authorId) {
        Log.d("AuthorsViewModel", "Chargement des livres pour l'auteur ID: " + authorId);
        booksRepository.getBooksByAuthor(authorId, booksByAuthorLiveData);

        booksByAuthorLiveData.observeForever(books -> {
            if (books != null) {
                Log.d("AuthorsViewModel", "Nombre de livres récupérés: " + books.size());
            } else {
                Log.e("AuthorsViewModel", "Erreur : aucun livre récupéré !");
            }
        });
    }

    public void selectAuthor(Author author) {
        Log.d("AuthorsViewModel", "Auteur sélectionné : " + author.getFirstname() + " " + author.getLastname());
        selectedAuthor.setValue(author);
    }

    public LiveData<Author> getSelectedAuthor() {
        return selectedAuthor;
    }

    public void loadAuthorById(int authorId) {
        booksRepository.getAuthorById(authorId, selectedAuthor);
    }
}