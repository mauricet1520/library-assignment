package com.tiy;

import org.h2.tools.Server;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by crci1 on 1/5/2017.
 */
public class BookDatabase {
    public final static String DB_URL = "jdbc:h2:./main";

    public void init() throws SQLException {
        Server.createWebServer().start();
        Connection connection = DriverManager.getConnection(DB_URL);
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS books (id IDENTITY, title VARCHAR, author VARCHAR, genre VARCHAR," +
                " checkedOut BOOLEAN, customer_id INT)");
        statement.execute("CREATE TABLE IF NOT EXISTS customers (id IDENTITY, userName VARCHAR, firstName VARCHAR," +
                " lastName VARCHAR, password VARCHAR)");
    }

    public void insertIntoTable(Connection connection, String title, int customer_id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO books VALUES (NULL, ?, NULL, NULL, false, ?)");
        statement.setString(1, title);
        statement.setInt(2, customer_id);
        statement.execute();
    }

    public void deleteRecord(Connection connection, String title) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE title = ?");
        statement.setString(1, title);
        statement.execute();
    }

    public ArrayList<Book> browseBooks(Connection connection) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM books");

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

    public void checkOutBook(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE books SET checkedOut = NOT checkedOut WHERE id = ?");
        statement.setInt(1, id);
        statement.execute();

    }

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
