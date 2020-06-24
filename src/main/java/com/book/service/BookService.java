package com.book.service;

import com.book.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    ResponseEntity<List<Book>> findAllBooks();

    ResponseEntity<Book> findBookById(Integer id);

    ResponseEntity<Book> deleteBookById(Integer id);

    ResponseEntity<Book> save(Book book);

    ResponseEntity<Book> updateBook(Book book, Integer id);
}
