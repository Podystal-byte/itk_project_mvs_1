package org.example.controller;

import org.example.exception.NotFoundException;
import org.example.exception.ValidationException;
import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public Book addBook(@RequestBody Book book) throws ValidationException, NotFoundException {
        return bookService.addBook(book);
    }

    @GetMapping("{book_id}")
    public Book getByid(@PathVariable ("book_id") Long book_id) throws NotFoundException {
        return bookService.getById(book_id);
    }

}
