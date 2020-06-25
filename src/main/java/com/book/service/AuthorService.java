package com.book.service;

import com.book.model.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    Author findAuthorById(Integer id);

    Author save(Author author);

    Author updateAuthor(Author author, Integer id);

    ResponseEntity<Object> deleteAuthorById(Integer id);

}
