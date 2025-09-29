package com.example.shelfmate.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Tag {
    private int id;
    private String name;
    private List<Book> books;

    public Tag(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getBooksCount() {
        return books != null ? books.size() : 0;
    }

    // pour le fonctionnement avec Set
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
