package com.tiy;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by crci1 on 1/6/2017.
 */

@RestController
public class BookJSONController {
    static BookDatabase database;
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:h2:./main");
            database = new BookDatabase();
            database.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Book book;

    //Get request that returns a database of books
    @RequestMapping(path = "/get_books.json", method = RequestMethod.GET)
    public ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> databaseBooks = new ArrayList<>();

        databaseBooks = database.browseBooks(connection);

//        books.add(new Book(1, "Java for Dummies", "Will Thomas", "Computer", false, null));
//        books.add(new Book(2, "Java Training", "Rick Santos", "Computer", false, null));
//        books.add(new Book(3, "IOS Development", "Susan Summers", "Computer", false, null));
//        books.add(new Book(4, "Cooking 101", "Rick Fox", "CookBooks", false, null));
//
//        database.insertIntoTable(connection, books.get(0).getTitle(), books.get(0).getAuthor(), books.get(0).getGenre()
//        ,books.get(0).getCheckedOutBy());
//
//        database.insertIntoTable(connection, books.get(1).getTitle(), books.get(1).getAuthor(), books.get(1).getGenre()
//                ,books.get(1).getCheckedOutBy());
//
//        database.insertIntoTable(connection, books.get(2).getTitle(), books.get(2).getAuthor(), books.get(2).getGenre()
//                ,books.get(2).getCheckedOutBy());
//
//        database.insertIntoTable(connection, books.get(3).getTitle(), books.get(3).getAuthor(), books.get(3).getGenre()
//                ,books.get(3).getCheckedOutBy());

        return databaseBooks;

    }

    //Post request that checkout a book
    @RequestMapping(path = "/checkout_book.json", method = RequestMethod.POST)
    public Book checkOutBook(@RequestBody BookHolder holder) throws Exception {
        book = new Book();
         book = database.getBook(connection, holder.getTitle());
        database.checkOutBook(connection, holder.getTitle());
        book.setCheckedOutBy(holder.getCheckedOutBy());

        database.checkedByUser(connection, holder.getCheckedOutBy(), book.getTitle());

        return book;

    }
    //Post request that check in a book
    @RequestMapping(path = "/checkin_book.json", method = RequestMethod.POST)
    public Book checkInBook(@RequestBody BookHolder holder) throws Exception {
        book = new Book();

         book = database.getBook(connection, holder.getTitle());
        database.checkOutBook(connection, holder.getTitle());
        book.setCheckedOutBy(null);

        database.checkedByUser(connection, null, book.getTitle());
        return book;

    }

    //Post request that adds a book to the database table
    @RequestMapping(path = "/add_book.json", method = RequestMethod.POST)
    public ArrayList<Book> addBook(@RequestBody Book book) throws Exception {

        database.insertIntoTable(connection, book.getTitle(), book.getAuthor(), book.getGenre(), book.getCheckedOutBy());

        return getAllBooks();
    }

    //Post request that deletes a book from the database table
    @RequestMapping(path = "/delete_book.json", method = RequestMethod.POST)
    public void deleteBook(@RequestBody Book book) throws Exception {

        database.deleteRecord(connection, book.getTitle());
    }

    ArrayList<Book> getAllBooks() throws SQLException{
        ArrayList<Book> books = database.browseBooks(connection);
        return books;
    }




}
