package com.book.service;

import com.book.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book findBookById(Integer id);

    Book save(Book book);

    Book updateBook(Book book, Integer id);

    void deleteBookById(Integer id);

}
