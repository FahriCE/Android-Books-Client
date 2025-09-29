package com.example.shelfmate.network;

import com.example.shelfmate.model.Book;
import com.example.shelfmate.model.Author;
import com.example.shelfmate.model.Tag;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Body;
import retrofit2.http.Path;

public interface ApiService {
    // Consultation
    @GET("/books")
    Call<List<Book>> getAllBooks();
    @GET("/authors")
    Call<List<Author>> getAllAuthors();
    @GET("/authors/{id}")
    Call<Author> getAuthorById(@Path("id") int id);
    @GET("/books/{id}")
    Call<Book> getBookById(@Path("id") int id);
    @GET("/tags/{id}/books")
    Call<List<Book>> getBooksByTagId(@Path("id") int id);
    @GET("/tags")
    Call<List<Tag>> getAllTags();

    // Modification
    @POST("/authors/{author_id}/books")
    Call<Book> addBookForAuthor(@Path("author_id") int authorId, @Body JsonObject book);

    @POST("/authors")
    Call<Author> addAuthor(@Body JsonObject author);

    @DELETE("/books/{book_id}")
    Call<Void> deleteBook(@Path("book_id") int bookId);

    @DELETE("/authors/{author_id}")
    Call<Void> deleteAuthor(@Path("author_id") int authorId);

    @GET("/authors/{author_id}/books")
    Call<List<Book>> getBooksByAuthor(@Path("author_id") int author_id);
}