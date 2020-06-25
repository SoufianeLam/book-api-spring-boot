package com.book.service;

import com.book.exception.ConflictException;
import com.book.exception.NotFoundException;
import com.book.model.Author;
import com.book.model.Book;
import com.book.repository.AuthorRepository;
import com.book.repository.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty())
            throw new NotFoundException("The database is empty!");
        return books;
    }

    @Override
    public Book findBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent())
            throw new NotFoundException("Book Not Found!");
        return book.get();
    }

    @Override
    public Book save(Book book) throws ConflictException {
        for (Author author : book.getAuthors()) {
            if (!authorRepository.findById(author.getIdAuthor()).isPresent())
                throw new NotFoundException("Author not found!");
        }
        if (!bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            Book newBook = bookRepository.saveAndFlush(book);
            return newBook;
        } else {
            throw new ConflictException("This book is already exist!");
        }
    }

    @Override
    public Book updateBook(Book newBook, Integer id) {
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
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book Not Found!"));
        bookRepository.deleteById(id);
    }

}