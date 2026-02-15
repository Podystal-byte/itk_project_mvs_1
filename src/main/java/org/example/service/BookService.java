package org.example.service;

import org.example.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final JdbcTemplate jdbcTemplate;

    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> new Book(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getInt("publication_year")
    );

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM BOOKS", bookRowMapper);
    }

    public Book findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM BOOKS WHERE id = ?", bookRowMapper, id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO BOOKS (title, author, publication_year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public void update(Long id, Book book) {
        jdbcTemplate.update("UPDATE BOOKS SET title = ?, author = ?, publication_year = ? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getPublicationYear(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM BOOKS WHERE id = ?", id);
    }
}