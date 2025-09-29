package com.example.shelfmate.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private int publication_year;
    private List<Tag> tags;
    private int authorId;

    public Book(String title, int publicationYear, List<Tag> tags, int authorId) {
        this.title = title;
        this.publication_year = publicationYear;
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
        this.authorId = authorId;
    }
    public Book(){

    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publication_year;
    }

    public void setPublicationYear(int publicationYear) {
        this.publication_year = publicationYear;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}