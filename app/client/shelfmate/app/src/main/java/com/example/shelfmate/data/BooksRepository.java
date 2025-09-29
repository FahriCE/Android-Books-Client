package com.example.shelfmate.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.shelfmate.model.Book;
import com.example.shelfmate.model.Author;
import com.example.shelfmate.model.Tag;
import com.example.shelfmate.network.ApiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class BooksRepository {
    private final ApiService apiService;

    public BooksRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getAllBooks(MutableLiveData<List<Book>> booksLiveData) {
        apiService.getAllBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(@NonNull Call<List<Book>> call, @NonNull Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    booksLiveData.setValue(response.body());
                } else {
                    booksLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Book>> call, @NonNull Throwable t) {
                booksLiveData.setValue(null);
            }
        });
    }

    public void getBooksByTag(int tagId, MutableLiveData<List<Book>> booksLiveData) {
        apiService.getBooksByTagId(tagId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(@NonNull Call<List<Book>> call, @NonNull Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    booksLiveData.setValue(response.body());
                } else {
                    booksLiveData.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Book>> call, @NonNull Throwable t) {
                booksLiveData.setValue(new ArrayList<>());
            }
        });
    }

    public void getAuthorById(int authorId, MutableLiveData<Author> authorLiveData) {
        apiService.getAuthorById(authorId).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Author> call, @NonNull Response<Author> response) {
                if (response.isSuccessful() && response.body() != null) {
                    authorLiveData.setValue(response.body());
                } else {
                    authorLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Author> call, @NonNull Throwable t) {
                authorLiveData.setValue(null);
            }
        });
    }

    public void getBookById(int bookId, MutableLiveData<Book> bookLiveData) {
        apiService.getBookById(bookId).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Book> call, @NonNull Response<Book> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bookLiveData.setValue(response.body());
                } else {
                    bookLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Book> call, @NonNull Throwable t) {
                bookLiveData.setValue(null);
            }
        });
    }

    public void getBooksByTagId(int tagId, MutableLiveData<List<Book>> booksLiveData) {
        apiService.getBooksByTagId(tagId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(@NonNull Call<List<Book>> call, @NonNull Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    booksLiveData.setValue(response.body());
                } else {
                    booksLiveData.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Book>> call, @NonNull Throwable t) {
                booksLiveData.setValue(new ArrayList<>());
            }
        });
    }

    public void getAllAuthors(MutableLiveData<List<Author>> authorsLiveData) {
        apiService.getAllAuthors().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Author>> call, @NonNull Response<List<Author>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    authorsLiveData.setValue(response.body());
                } else {
                    authorsLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Author>> call, @NonNull Throwable t) {
                authorsLiveData.setValue(null);
            }
        });
    }

    public void getAllTags(MutableLiveData<List<Tag>> tagsLiveData) {
        apiService.getAllTags().enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(@NonNull Call<List<Tag>> call, @NonNull Response<List<Tag>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tagsLiveData.setValue(response.body());
                } else {
                    tagsLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Tag>> call, @NonNull Throwable t) {
                tagsLiveData.setValue(null);
            }
        });
    }

    public void addAuthor(Author author, MutableLiveData<List<Author>> authorsLiveData) {
        JsonObject authorJson = new JsonObject();
        authorJson.addProperty("firstname", author.getFirstname());
        authorJson.addProperty("lastname", author.getLastname());

        apiService.addAuthor(authorJson).enqueue(new Callback<Author>() {
            @Override
            public void onResponse(Call<Author> call, Response<Author> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Log.d("API_SUCCESS", "Auteur ajouté: " + response.body());
                    getAllAuthors(authorsLiveData);
                } else {
                    try {
                        Log.e("API_ERROR", "Erreur: " + response.code() + " - " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Author> call, Throwable t) {
                Log.e("API_FAILURE", "Échec: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void addBookForAuthor(Book book, MutableLiveData<List<Book>> booksLiveData) {
        JsonObject bookJson = new JsonObject();
        bookJson.addProperty("title", book.getTitle());
        bookJson.addProperty("publication_year", book.getPublicationYear());

        int authorId = book.getAuthorId();

        apiService.addBookForAuthor(Integer.parseInt(String.valueOf(authorId)), bookJson).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    getAllBooks(booksLiveData);
                } else {
                    handleResponseError(response);
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.e("API_FAILURE", "Failed to add book", t);
            }
        });
    }

    private void handleResponseError(Response<Book> response) {
        try {
            String errorBody = response.errorBody() != null ?
                    response.errorBody().string() : "unknown error";
            Log.e("API_ERROR", "Failed to add book: " + errorBody);
        } catch (IOException e) {
            Log.e("API_ERROR", "Error reading error body", e);
        }
    }


    public void deleteBook(int bookId, MutableLiveData<Boolean> resultLiveData) {
        apiService.deleteBook(bookId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                resultLiveData.setValue(response.isSuccessful());
                if (!response.isSuccessful()) {
                    System.out.println("Erreur lors de la suppression: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                resultLiveData.setValue(false);
                System.out.println("Échec de la suppression: " + t.getMessage());
            }
        });
    }

    public void deleteAuthor(int authorId, MutableLiveData<Boolean> resultLiveData) {
        apiService.deleteAuthor(authorId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                resultLiveData.setValue(response.isSuccessful());
                if (!response.isSuccessful()) {
                    System.out.println("Erreur lors de la suppression: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                resultLiveData.setValue(false);
                System.out.println("Échec de la suppression: " + t.getMessage());
            }
        });
    }

    public void getBooksByAuthor(int authorId, MutableLiveData<List<Book>> booksLiveData) {
        apiService.getBooksByAuthor(authorId).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(@NonNull Call<List<Book>> call, @NonNull Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    booksLiveData.setValue(response.body());
                } else {
                    booksLiveData.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Book>> call, @NonNull Throwable t) {
                booksLiveData.setValue(new ArrayList<>());
            }
        });
    }
}