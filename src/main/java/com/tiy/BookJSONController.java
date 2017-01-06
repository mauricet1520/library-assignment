package com.tiy;

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
    public ArrayList<Book> getBooks(){

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Java for Dummies", "Unknown", "Technolgy", false, null));
        books.add(new Book(1, "Java Training", "Unknown", "Technolgy", false, null));
        books.add(new Book(1, "IOS Development", "Unknown", "Technolgy", false, null));
        books.add(new Book(1, "Cooking 101", "Unknown", "Food", false, null));

        return books;

    }




}
