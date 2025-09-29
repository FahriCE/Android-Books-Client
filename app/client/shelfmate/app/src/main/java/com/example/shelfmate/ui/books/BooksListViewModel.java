package com.example.shelfmate.ui.books;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.shelfmate.data.BooksRepository;
import com.example.shelfmate.model.Author;
import com.example.shelfmate.model.Book;
import com.example.shelfmate.model.Tag;
import com.example.shelfmate.network.ApiClient;

import java.util.List;

public class BooksListViewModel extends ViewModel {
    private final BooksRepository booksRepository;
    private final MutableLiveData<List<Book>> booksLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Tag>> tagsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Author>> authorsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Book> selectedBook = new MutableLiveData<>();
    private final MutableLiveData<Author> selectedAuthor = new MutableLiveData<>();
    private final MutableLiveData<List<Tag>> selectedTags = new MutableLiveData<>();


    public BooksListViewModel() {
        this.booksRepository = new BooksRepository(ApiClient.getApiService());
        loadBooks();
        loadAuthors();
        loadTags();
    }

    public LiveData<List<Book>> getBooks() {
        return booksLiveData;
    }

    public LiveData<List<Author>> getAuthors() {
        return authorsLiveData;
    }

    public LiveData<List<Tag>> getTags() {
        return tagsLiveData;
    }

    private void loadBooks() {
        booksRepository.getAllBooks(booksLiveData);
    }

    private void loadAuthors() {
        booksRepository.getAllAuthors(authorsLiveData);
    }

    private void loadTags() {
        booksRepository.getAllTags(tagsLiveData);
    }

    public void addBook(Book book) {
        booksRepository.addBookForAuthor(book, booksLiveData);

        booksLiveData.observeForever(books -> {
            Log.d("AuthorsViewModel", "Liste des auteurs mise à jour : " + books.size());
        });
    }

    public LiveData<Book> getSelectedBook() {
        return selectedBook;
    }

    public void loadBookDetails(int bookId) {
        Book localBook = findBookById(bookId);
        if (localBook != null) {
            selectedBook.setValue(localBook);
        } else {
            booksRepository.getBookById(bookId, new MutableLiveData<Book>() {
                @Override
                protected void onActive() {
                    Book book = getValue();
                    if (book != null) {
                        selectedBook.setValue(book);
                    }
                }
            });
        }
    }

    private Book findBookById(int bookId) {
        List<Book> books = booksLiveData.getValue();
        if (books != null) {
            for (Book book : books) {
                if (book.getId() == bookId) {
                    return book;
                }
            }
        }
        return null;
    }

    public void deleteBook(int bookId) {
        MutableLiveData<Boolean> deleteResult = new MutableLiveData<>();
        booksRepository.deleteBook(bookId, deleteResult);

        deleteResult.observeForever(success -> {
            if (Boolean.TRUE.equals(success)) {
                loadBooks();
                loadAuthors();
            }
        });
    }
}