package com.tiy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by crci1 on 1/8/2017.
 */

@Controller
public class BookController {
    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public String todos(Model model, HttpSession session) {
        return "books";
    }
}
