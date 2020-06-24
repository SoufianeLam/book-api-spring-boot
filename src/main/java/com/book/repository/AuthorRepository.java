package com.book.repository;

import com.book.model.Author;
import com.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    public Optional<Book> findByFirstname(String firstname);

    public Optional<Book> findByLastname(String lastname);

    @RestResource(exported = false)
    Author save(Author author);


}
