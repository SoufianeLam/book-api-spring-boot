package com.book.service;

import com.book.exception.ConflictException;
import com.book.exception.NotFoundException;
import com.book.model.Author;
import com.book.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AutorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public ResponseEntity<List<Author>> findAllAuthors() {

        return new ResponseEntity<List<Author>>(authorRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Author> save(Author author) throws ConflictException {
        Author newAuthor = authorRepository.saveAndFlush(author);
        return new ResponseEntity<Author>(newAuthor, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Author> updateAuthor(Author newAuthor, Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author Not Found!"));
        author.setFirstname(newAuthor.getFirstname());
        author.setLastname(newAuthor.getLastname());
        author.setCreatedAt(author.getCreatedAt());
        final Author updatedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @Override
    public ResponseEntity<Author> findAuthorById(Integer id) {
        if (!authorRepository.findById(id).isPresent())
            throw new NotFoundException("Author Not Found!");
        return new ResponseEntity<Author>(authorRepository.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteAuthorById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author Not Found!"));
        //authorRepository.deleteById(id);
        if (authorRepository.findById(id).isPresent()) {
            if (authorRepository.getOne(id).getBooks().size() == 0) {
                authorRepository.deleteById(id);
                if (authorRepository.findById(id).isPresent()) {
                    return ResponseEntity.unprocessableEntity().body("Failed to delete the specified author");
                } else return ResponseEntity.ok().body("Successfully deleted specified author");
            } else
                return ResponseEntity.unprocessableEntity().body("Failed to delete, Please delete books associated with this author");
        } else
            return ResponseEntity.unprocessableEntity().body("No Author Found");
    }
}

       

