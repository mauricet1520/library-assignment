package com.tiy;

import org.h2.tools.Server;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by crci1 on 1/5/2017.
 *
 * A class that uses a h2 database table to store a list of books. Book.java
 */
public class BookDatabase {
    public final static String DB_URL = "jdbc:h2:./main";
    ArrayList<Book> books = new ArrayList<>();

    //drops the table
    public void dropTable() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);
        PreparedStatement statement = connection.prepareStatement("DROP TABLE books");
        statement.execute();

    }

    /**
     * starts the database
     * creates the tables
     * @throws SQLException
     */
    public void init() throws SQLException {
        Server.createWebServer().start();
        Connection connection = DriverManager.getConnection(DB_URL);
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS books (id IDENTITY, title VARCHAR, author VARCHAR, genre VARCHAR," +
                " checkedOut BOOLEAN, user VARCHAR)");
        statement.execute("CREATE TABLE IF NOT EXISTS customers (id IDENTITY, userName VARCHAR, firstName VARCHAR," +
                " lastName VARCHAR, password VARCHAR)");
    }

    // inserts books into table
    public void insertIntoTable(Connection connection, String title, String author, String genre,
                                String user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO books VALUES (NULL, ?, ?, ?, false, ?)");
        statement.setString(1, title);
        statement.setString(2, author);
        statement.setString(3, genre);
        statement.setString(4, user);
        statement.execute();
    }

    //delete a book from table
    public void deleteRecord(Connection connection, String title) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE title = ?");
        statement.setString(1, title);
        statement.execute();
    }
    //method that views all the books
    public ArrayList<Book> browseBooks(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM books");
        ArrayList<Book> myBooks = new ArrayList<>();

        while (results.next()) {
            int id = results.getInt("id");
            String title = results.getString("title");
            String author = results.getString("author");
            String genre = results.getString("genre");
            boolean checkedOut = results.getBoolean("checkedOut");
            String user = results.getString("user");
            myBooks.add(new Book(id, title, author, genre, checkedOut, user));

        }

        return myBooks;

    }

    // method that returns a one book
    public Book getBook(Connection connection, String title) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM books WHERE title = ?");
        stmt.setString(1, title);

        ResultSet results = stmt.executeQuery();
        results.next();
        int id2 = results.getInt("id");
        String title2 = results.getString("title");
        String author = results.getString("author");
        String genre = results.getString("genre");
        boolean checkedOut = results.getBoolean("checkedOut");
        String user1 = results.getString("user");

        Book book = new Book(id2, title2, author, genre, checkedOut, user1);

        return book;
    }

    //method allows a user the checkout
    public void checkOutBook(Connection conn, String title) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE books SET checkedOut = NOT checkedOut WHERE title = ?");

        stmt.setString(1, title);
        stmt.execute();
    }

    //sets a user to the book when the book is checkedout
    public void checkedByUser(Connection connection, String user, String title) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE books SET user = ? WHERE title = ?");
        stmt.setString(1, user);
        stmt.setString(2, title);
        stmt.execute();

    }
    // insert a customer to a table
    public int insertCustomer(Connection connection, String userName, String firstName, String lastName, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO customers VALUES (NULL, ?, ?, ?, ?)");
        statement.setString(1, userName);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.setString(4, password);
        statement.execute();

        statement = connection.prepareStatement("SELECT * FROM customers WHERE userName = ?");
        statement.setString(1, userName);
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getInt("id");
    }

    // delete a customer
    public void deleteUser(Connection conn, String username) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM customers WHERE userName = ?");
        stmt.setString(1, username);
        stmt.execute();
    }

    public ArrayList<Book> selectBookForUser(Connection conn, int userID) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books " +
                "INNER JOIN customers ON books.customer_id = customer.id " +
                "WHERE customer.id = ?");
        stmt.setInt(1, userID);
        ResultSet results = stmt.executeQuery();

        while (results.next()) {
            int id = results.getInt("id");
            String title = results.getString("title");
            String author = results.getString("author");
            String genre = results.getString("genre");
            boolean checkedOut = results.getBoolean("checkedOut");
            books.add(new Book(id, title, author, genre, checkedOut, null));
        }
        return books;
    }


}
