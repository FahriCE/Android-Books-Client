package com.example.shelfmate.ui.tags;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shelfmate.data.BooksRepository;
import com.example.shelfmate.model.Book;
import com.example.shelfmate.model.Tag;
import com.example.shelfmate.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class TagsViewModel extends ViewModel {
    private final BooksRepository booksRepository;
    private final MutableLiveData<List<Tag>> tags = new MutableLiveData<>();
    private final MutableLiveData<List<Tag>> tagsWithBooks = new MutableLiveData<>();

    public TagsViewModel() {
        this.booksRepository = new BooksRepository(ApiClient.getApiService());
        loadTagsWithBooks();
    }

    public LiveData<List<Tag>> getTagsWithBooks() {
        return tagsWithBooks;
    }

    private void loadTagsWithBooks() {
        MutableLiveData<List<Tag>> tagsLiveData = new MutableLiveData<>();
        booksRepository.getAllTags(tagsLiveData);

        tagsLiveData.observeForever(tagList -> {
            if (tagList != null && !tagList.isEmpty()) {
                List<Tag> updatedTags = new ArrayList<>();
                int[] counter = {0};

                for (Tag tag : tagList) {
                    MutableLiveData<List<Book>> booksLiveData = new MutableLiveData<>();
                    booksRepository.getBooksByTagId(tag.getId(), booksLiveData);

                    booksLiveData.observeForever(books -> {
                        if (books != null) {
                            tag.setBooks(books);
                        } else {
                            tag.setBooks(new ArrayList<>());
                        }

                        updatedTags.add(tag);
                        counter[0]++;

                        if (counter[0] == tagList.size()) {
                            tagsWithBooks.setValue(updatedTags);
                        }
                    });
                }
            } else {
                tagsWithBooks.setValue(new ArrayList<>());
            }
        });
    }

    public void clearHistory() {
        tagsWithBooks.setValue(new ArrayList<>());
    }
}