package org.example.controller;

import org.example.exception.NotFoundException;
import org.example.exception.ValidationException;
import org.example.model.Author;
import org.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) throws ValidationException {
        return authorService.addAuthor(author);
    }

    @GetMapping
    public Page<Author> getAuthors(Pageable pageable) throws NotFoundException {
        return authorService.getAuthors(pageable);
    }
}
