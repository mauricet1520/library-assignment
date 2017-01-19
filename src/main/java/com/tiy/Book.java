package com.tiy;

import java.time.LocalDateTime;

/**
 * Created by crci1 on 1/5/2017.
 * blueprint for a book object
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean checkedOut;
    private String checkedOutBy;
    private LocalDateTime dueDate;

    public Book(int id, String title, String author, String genre,
                boolean checkedOut, String checkedOutBy) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.checkedOut = checkedOut;
        this.checkedOutBy = checkedOutBy;
        this.dueDate = dueDate;
    }
    public Book() {}

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

    public String getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(String checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    private LocalDateTime getDueDate() {
        return dueDate;
    }

    private void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
