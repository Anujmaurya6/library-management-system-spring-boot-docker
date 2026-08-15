package com.example.library.management.system.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.library.management.system.dto.BookDto;
import com.example.library.management.system.entity.Book;
import com.example.library.management.system.exception.ResourceNotFoundException;
import com.example.library.management.system.repository.BookRepository;
import com.example.library.management.system.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    // Constructor Injection
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    // ==========================================================
    // GET ALL BOOKS
    // ==========================================================

    @Override
    public List<BookDto> getAllBooks() {

        return repository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ==========================================================
    // GET BOOK BY ID
    // ==========================================================

    @Override
    public BookDto getBookById(Long id) {

        Book book = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with ID : " + id
                        )
                );

        return convertToDto(book);
    }

    // ==========================================================
    // ADD BOOK
    // ==========================================================

    @Override
    public BookDto addBook(BookDto bookDto) {

        // DTO -> Entity
        Book book = convertToEntity(bookDto);

        // Save entity into database
        Book savedBook = repository.save(book);

        // Entity -> DTO
        return convertToDto(savedBook);
    }

    // ==========================================================
    // UPDATE BOOK
    // ==========================================================

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {

        // Step 1: Find existing book
        Book existingBook = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with ID : " + id
                        )
                );

        // Step 2: Update fields
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setIsbn(bookDto.getIsbn());
        existingBook.setCategory(bookDto.getCategory());
        existingBook.setPrice(bookDto.getPrice());
        existingBook.setQuantity(bookDto.getQuantity());
        existingBook.setPublisher(bookDto.getPublisher());
        existingBook.setPublishedYear(bookDto.getPublishedYear());

        // Step 3: Save updated entity
        Book updatedBook = repository.save(existingBook);

        // Step 4: Entity -> DTO
        return convertToDto(updatedBook);
    }

    // ==========================================================
    // DELETE BOOK
    // ==========================================================

    @Override
    public BookDto deleteBook(Long id) {

        // Step 1: Find book
        Book book = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with ID : " + id
                        )
                );

        // Step 2: Delete book
        repository.delete(book);

        // Step 3: Return deleted book as DTO
        return convertToDto(book);
    }

    // ==========================================================
    // ENTITY -> DTO
    // ==========================================================

    private BookDto convertToDto(Book book) {

        BookDto dto = new BookDto();

        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setCategory(book.getCategory());
        dto.setPrice(book.getPrice());
        dto.setQuantity(book.getQuantity());
        dto.setPublisher(book.getPublisher());
        dto.setPublishedYear(book.getPublishedYear());

        return dto;
    }

    // ==========================================================
    // DTO -> ENTITY
    // ==========================================================

    private Book convertToEntity(BookDto dto) {

        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setCategory(dto.getCategory());
        book.setPrice(dto.getPrice());
        book.setQuantity(dto.getQuantity());
        book.setPublisher(dto.getPublisher());
        book.setPublishedYear(dto.getPublishedYear());

        return book;
    }
}