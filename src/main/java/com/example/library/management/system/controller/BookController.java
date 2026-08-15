package com.example.library.management.system.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.library.management.system.dto.BookDto;
import com.example.library.management.system.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger =
            LoggerFactory.getLogger(BookController.class);

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // ==========================================================
    // GET ALL BOOKS
    // ==========================================================
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {

        logger.info("Fetching all books");

        List<BookDto> books = service.getAllBooks();

        return ResponseEntity.ok(books);
    }

    // ==========================================================
    // GET BOOK BY ID
    // ==========================================================
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {

        logger.info("Fetching book with id : {}", id);

        BookDto book = service.getBookById(id);

        return ResponseEntity.ok(book);
    }

    // ==========================================================
    // ADD BOOK
    // ==========================================================
    @PostMapping
    public ResponseEntity<BookDto> addBook(
            @Valid @RequestBody BookDto bookDto) {

        logger.info("Adding new book : {}", bookDto.getTitle());

        BookDto savedBook = service.addBook(bookDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBook);
    }

    // ==========================================================
    // UPDATE BOOK
    // ==========================================================
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookDto bookDto) {

        logger.info("Updating book with id : {}", id);

        BookDto updatedBook = service.updateBook(id, bookDto);

        return ResponseEntity.ok(updatedBook);
    }

    // ==========================================================
    // DELETE BOOK
    // ==========================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long id) {

        logger.warn("Deleting book with id : {}", id);

        BookDto deletedBook = service.deleteBook(id);

        return ResponseEntity.ok(deletedBook);
    }

}