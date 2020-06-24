package com.book.repository;

import com.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
@RestResource(exported = false)
public interface BookRepository extends JpaRepository<Book, Integer> {
    public Optional<Book> findByIsbn(String isbn);
}
