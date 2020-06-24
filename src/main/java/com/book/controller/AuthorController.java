package com.book.controller;

import com.book.model.Author;
import com.book.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(AuthorController.BASE_URL)
@Tag(name = "author", description = "the Author API")
public class AuthorController {

    public static final String BASE_URL = "/authors";

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @Operation(summary = "Get all authors with their books", tags = {"author"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")})
    public ResponseEntity<List<Author>> getAll() {
        return authorService.findAllAuthors();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find author by ID", description = "Returns a single author", tags = {"author"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Author not found!")})
    public ResponseEntity<Author> get(@PathVariable("id") Integer id) {
        return authorService.findAuthorById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new author", description = "", tags = {"author"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    public ResponseEntity<?> save(@Valid @RequestBody Author author) {
        authorService.save(author);
        return ResponseEntity.ok().body("New Author has been saved with name: " + author.getFirstname() + " " + author.getLastname());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing author", description = "", tags = {"author"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Author not found")})
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @Valid @RequestBody Author author) {
        authorService.updateAuthor(author, id);
        return ResponseEntity.ok().body("Author has been updated successfully.");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleted an author", description = "", tags = { "author" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "422", description = "Author cannot be deleted") })
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return authorService.deleteAuthorById(id);
    }

}
