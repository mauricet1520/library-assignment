package com.tiy;

/**
 * Created by crci1 on 1/5/2017.
 */
public class Customer {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

    public Customer(int id, String userName, String firstName, String lastName, String password) {

        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstNsme() {
        return firstName;
    }

    public void setFirstNsme(String firstNsme) {
        this.firstName = firstNsme;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
