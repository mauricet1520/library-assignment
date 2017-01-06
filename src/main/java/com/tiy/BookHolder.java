package com.tiy;

/**
 * Created by crci1 on 1/6/2017.
 */
public class BookHolder {
    private String checkOutBy;
    private int id;

    public BookHolder(String checkOutBy, int id) {
        this.checkOutBy = checkOutBy;
        this.id = id;
    }

    public String getCheckOutBy() {
        return checkOutBy;
    }

    public void setCheckOutBy(String checkOutBy) {
        this.checkOutBy = checkOutBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
