package org.example.controller;

import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.save(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}