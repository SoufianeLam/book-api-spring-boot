package com.book.service;

import com.book.exception.ConflictException;
import com.book.exception.NotFoundException;
import com.book.model.Author;
import com.book.repository.AuthorRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AutorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorById(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent())
            throw new NotFoundException("Author Not Found!");
        return author.get();
    }

    @Override
    public Author save(Author author) throws ConflictException {
        return authorRepository.saveAndFlush(author);
    }

    @Override
    public Author updateAuthor(Author newAuthor, Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author Not Found!"));
        author.setFirstname(newAuthor.getFirstname());
        author.setLastname(newAuthor.getLastname());
        author.setCreatedAt(author.getCreatedAt());
        return authorRepository.save(author);
    }

    @Override
    public ResponseEntity<Object> deleteAuthorById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author Not Found!"));
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
