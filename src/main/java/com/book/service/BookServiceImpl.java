package com.book.service;

import com.book.exception.ConflictException;
import com.book.exception.NotFoundException;
import com.book.model.Author;
import com.book.model.Book;
import com.book.repository.AuthorRepository;
import com.book.repository.BookRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public ResponseEntity<List<Book>> findAllBooks() {
        if (bookRepository.findAll().isEmpty())
            throw new NotFoundException("The database is empty!");
        return new ResponseEntity<List<Book>>(bookRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> findBookById(Integer id) {
        if (!bookRepository.findById(id).isPresent())
            throw new NotFoundException("Book Not Found!");
        return new ResponseEntity<Book>(bookRepository.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> save(Book book) throws ConflictException {
        for (Author author : book.getAuthors()) {
            if (!authorRepository.findById(author.getIdAuthor()).isPresent())
                throw new NotFoundException("Author not found!");
        }
        if (!bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            Book newBook = bookRepository.saveAndFlush(book);
            return new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
        } else {
            throw new ConflictException("This book is already exist!");
        }
    }

    @Override
    public ResponseEntity<Book> updateBook(Book newBook, Integer id) {
        for (Author author : newBook.getAuthors()) {
            if (!authorRepository.findById(author.getIdAuthor()).isPresent())
                throw new NotFoundException("Author not found!");
        }
        if (bookRepository.findByIsbn(newBook.getIsbn()).isPresent()
                && !bookRepository.findById(id).get().getIsbn().equals(newBook.getIsbn()))
            throw new ConflictException("Book with ISBN " + newBook.getIsbn() + " is already exist");
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found!"));
        book.setTitle(newBook.getTitle());
        book.setIsbn(newBook.getIsbn());
        book.setAuthors(newBook.getAuthors());
        book.setCreatedAt(book.getCreatedAt());
        final Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @Override
    public ResponseEntity<Book> deleteBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book Not Found!"));
        bookRepository.deleteById(id);
        return new ResponseEntity<Book>(HttpStatus.OK);
    }

}