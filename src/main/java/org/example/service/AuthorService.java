package org.example.service;

import org.example.exception.NotFoundException;
import org.example.exception.ValidationException;
import org.example.model.Author;
import org.example.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addAuthor(Author author) throws ValidationException {
        if (author.getName() == null || author.getLastName() == null){
            throw new ValidationException("Данные пользователя указаны не верно");
        }

        authorRepository.save(author);


        return author;
    }

    public Page<Author> getAuthors(Pageable pageable) throws NotFoundException {
        Page<Author> author = authorRepository.findAll(pageable);

        if (author.isEmpty()){
            throw new NotFoundException("Авторы не найдены");
        }

        return author;
    }
}
