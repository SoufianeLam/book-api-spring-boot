package com.book.service;

import com.book.model.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {

    ResponseEntity<List<Author>> findAllAuthors();

    ResponseEntity<Author> findAuthorById(Integer id);

    ResponseEntity<Object> deleteAuthorById(Integer id);

    ResponseEntity<Author> save(Author author);

    ResponseEntity<Author> updateAuthor(Author author, Integer id);
}
