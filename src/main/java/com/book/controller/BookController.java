package com.book.controller;

import com.book.model.Book;
import com.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(BookController.BASE_URL)
@Tag(name = "book", description = "the Book API")
public class BookController {

    public static final String BASE_URL = "/books";

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all books with their authors", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = Book.class)))})
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find book by ID", description = "Returns a single book with its authors", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Book Not Found!")})
    public ResponseEntity<Book> get(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @PostMapping
    @Operation(summary = "Add a new book", description = "", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book created",
                    content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Book already exists")})
    public ResponseEntity<?> save(@Valid @RequestBody Book book) {
        bookService.save(book);
        return ResponseEntity.ok().body("New Book has been saved with title: " + book.getTitle());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing book", description = "", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Book or Author not found!")})
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @Valid @RequestBody Book book) {
        bookService.updateBook(book, id);
        return ResponseEntity.ok().body("Book has been updated successfully.");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleted a book", description = "", tags = {"book"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Book not found")})
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().body("Book has been deleted successfully.");
    }

}
