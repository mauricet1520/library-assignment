package com.tiy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by crci1 on 1/5/2017.
 */
public class BookDatabaseTest {

    static private BookDatabase database;

    @Before
    public void setUp() throws Exception {
        if (database == null) {
            database = new BookDatabase();
            database.init();
        }

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInit() throws SQLException {
        // test to make sure we can access the new database
        Connection connection = DriverManager.getConnection("jdbc:h2:./main");
        PreparedStatement todoQuery = connection.prepareStatement("SELECT * FROM books");
        ResultSet results = todoQuery.executeQuery();
        assertNotNull(results);

    }

    @Test
    public void testInsertIntoTable() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:./main");
        String title = "Java for Dummies";
        String userName = "cr";
        String firstName = "maurice";
        String lastName = "thomas";
        String password = "1234";


//        int custId = database.insertCustomer(connection, userName, firstName, lastName, password);
        database.insertIntoTable(connection, title, userName, null, null);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE title = ?");
        statement.setString(1, title);
        ResultSet results = statement.executeQuery();
        assertNotNull(results);

        int numResults = 0;

        while (results.next()) {
            numResults++;
        }
        assertEquals(1, numResults);
        database.deleteRecord(connection, title);
        database.deleteUser(connection, userName);

        results = statement.executeQuery();
        numResults = 0;
        while (results.next()) {
            numResults++;
        }
        assertEquals(0, numResults);

    }

    @Test
    public void testBrowseBook() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:./main");
        String title = "Java for Dummies";
        String titleTwo = "Java Training 101";
        String userName = "reece";
        String firstName = "Maurice";
        String lastname = "Thomas";
        String password = "1234";

        int custId1 = database.insertCustomer(connection, userName, firstName, lastname, password);
        int custId2 = database.insertCustomer(connection, userName, firstName, lastname, password);


        database.insertIntoTable(connection, title, userName, null, null);
        database.insertIntoTable(connection, titleTwo, userName, null, null);

        ArrayList<Book> books = database.browseBooks(connection);

        System.out.println("Found " + books.size() + " books in the database");

        assertTrue("There should be at least 2 books in the database (there are " +
                books.size() + ")", books.size() > 1);

        database.deleteRecord(connection, title);
        database.deleteRecord(connection, titleTwo);

        database.deleteUser(connection, userName);
        database.deleteUser(connection, userName);

    }

    @Test
    public void testGetAbook() throws Exception {

        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Book book = new Book(22, "funk", "cr", "horror", false, "someone");

        database.insertIntoTable(conn, "funk", "cr", "horror", "someone");


        Book newBook = database.getBook(conn, book.getTitle());

        assertEquals(newBook.getTitle(), book.getTitle());

        database.deleteRecord(conn, newBook.getCheckedOutBy());


    }


    @Test
    public void testInsertCustomer() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        String userName = "reece";
        String firstName = "Maurice";
        String lastname = "Thomas";
        String password = "1234";

        int custId = database.insertCustomer(conn, userName, firstName, lastname, password);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM customers WHERE username = ?");


        statement.setString(1, userName);
        ResultSet results = statement.executeQuery();
        assertNotNull(results);

//         count the records in results to make sure we get what we expected
        int userIdRetrieve = -1;
        int numResults = 0;
        while (results.next()) {
            numResults++;
            userIdRetrieve = results.getInt("id");
        }
        assertEquals(1, numResults);
        assertEquals(custId, userIdRetrieve);

//        ToDoItem retrieveItem = myDatabase.retrieveToDo(conn, todoText);
//        assertNotNull(retrieveItem);
//        assertEquals(todoText, retrieveItem.getText());

        database.deleteUser(conn, userName);

        results = statement.executeQuery();
        numResults = 0;
        while (results.next()) {
            numResults++;
        }
        assertEquals(0, numResults);


    }

//    @Test
//    public void testSelectBooksForUser() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:h2:./main");
//
//        String title = "Java for Dummies";
//        String title2 = "Java 101";
//
//        String userName = "reece";
//        String firstName = "Maurice";
//        String lastName = "Thomas";
//        String password = "1234";
//
//        String userName2 = "cr";
//        String firstName2 = "Tom";
//        String lastName2 = "Walker";
//        String password2 = "1234";
//
//
//
//        int custId = database.insertCustomer(connection, userName, firstName, lastName, password);
//        int custId2 = database.insertCustomer(connection, userName2, firstName2, lastName2, password2);
//
//        database.insertIntoTable(connection, title, custId);
//        database.insertIntoTable(connection, title2, custId2);
//
//        ArrayList<Book> customersBook1 = database.selectBookForUser(connection, custId);
//        ArrayList<Book> customersBook2 = database.selectBookForUser(connection, custId);
//
//        assertEquals(1, customersBook1.size());
//        assertEquals(1, customersBook2.size());
//
//        Book bookUser1 = customersBook1.get(0);
//        assertEquals(title, customersBook1.t);
//
//
//
//    }


}