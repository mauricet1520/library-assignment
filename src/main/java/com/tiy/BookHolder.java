package com.tiy;

/**
 * Created by crci1 on 1/6/2017.
 */
public class BookHolder {
    private String checkedOutBy;
    private int id;
    private String title;


    public BookHolder() {

    }

    public String getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(String checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookHolder(String checkOutBy, int id, String title) {
        this.checkedOutBy = checkOutBy;
        this.id = id;
        this.title = title;
    }

    public String getCheckOutBy() {
        return checkedOutBy;
    }

    public void setCheckOutBy(String checkOutBy) {
        this.checkedOutBy = checkOutBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
