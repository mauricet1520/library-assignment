package com.tiy;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by crci1 on 1/6/2017.
 */

@RestController
public class BookJSONController {
    @RequestMapping(path = "/test.json", method = RequestMethod.GET)
    public ArrayList<Book> getBooks() {

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Java for Dummies", "Unknown", "Technolgy", false, null));
        books.add(new Book(2, "Java Training", "Unknown", "Technolgy", false, null));
        books.add(new Book(3, "IOS Development", "Unknown", "Technolgy", false, null));
        books.add(new Book(4, "Cooking 101", "Unknown", "Food", false, null));

        return books;

    }

    @RequestMapping(path = "/testPush.json", method = RequestMethod.POST)
    public Book pushBook(@RequestBody BookHolder holder) throws Exception {

        Book book = new Book(1, "Java for Dummies", "Unknown", "Technolgy", false, null);
        return book;

    }


}
