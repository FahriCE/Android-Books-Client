package com.example.shelfmate.model;

import java.util.List;

public class Author {
    private int id;
    private String firstname;
    private String lastname;
    private List<Book> books;

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = -1;
    }
    public Author(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return lastname.toUpperCase() + " " + firstname;
    }
}
