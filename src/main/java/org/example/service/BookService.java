package org.example.service;

import org.example.exception.NotFoundException;
import org.example.exception.ValidationException;
import org.example.model.Author;
import org.example.model.Book;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book addBook(Book book) throws ValidationException, NotFoundException {
        if (book.getName() == null || book.getNumberOfPages() <= 0){
            throw new ValidationException("Комплексные книги не принимаем");
        }

        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new ValidationException("Автор должен быть указан");
        }

         Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new NotFoundException("Такого автора не существует"));

        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public Page<Book> getBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Book getById(Long id) throws NotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Неправильно все"));
    }

    private boolean checkAuthor(Book book){
        Optional<Author> author = authorRepository.findById(book.getAuthor().getId());

        return author.isEmpty();
    }
}
