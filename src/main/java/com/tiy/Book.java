package com.tiy;

import java.time.LocalDateTime;

/**
 * Created by crci1 on 1/5/2017.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean checkedOut;
    private Customer checkedOutBy;
    private LocalDateTime dueDate;

    public Book(int id, String title, String author, String genre,
                boolean checkedOut, Customer checkedOutBy, LocalDateTime dueDate) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.checkedOut = checkedOut;
        this.checkedOutBy = checkedOutBy;
        this.dueDate = dueDate;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Customer getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(Customer checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
