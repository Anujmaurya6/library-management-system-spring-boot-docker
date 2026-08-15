package com.example.library.management.system.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.library.management.system.dto.BookDto;
import com.example.library.management.system.entity.Book;
import com.example.library.management.system.exception.ResourceNotFoundException;
import com.example.library.management.system.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    // ==========================================================
    // MOCK REPOSITORY
    // ==========================================================

    @Mock
    private BookRepository repository;

    // ==========================================================
    // REAL SERVICE
    // ==========================================================

    @InjectMocks
    private BookServiceImpl service;

    // ==========================================================
    // TEST DATA
    // ==========================================================

    private Book book;

    @BeforeEach
    void setUp() {

        book = new Book();

        book.setId(1L);
        book.setTitle("Java Programming");
        book.setAuthor("James Gosling");
        book.setIsbn("1234567890");
        book.setCategory("Programming");
        book.setPrice(599.0);
        book.setQuantity(10);
        book.setPublisher("Tech Publisher");
        book.setPublishedYear(2024);
        book.setAvailable(true);
    }

    // ==========================================================
    // GET ALL BOOKS
    // ==========================================================

    @Test
    void getAllBooks_shouldReturnBooks() {

        Book book2 = new Book();

        book2.setId(2L);
        book2.setTitle("Spring Boot");
        book2.setAuthor("Spring Team");
        book2.setIsbn("9876543210");
        book2.setCategory("Programming");
        book2.setPrice(699.0);
        book2.setQuantity(5);
        book2.setPublisher("Spring Publisher");
        book2.setPublishedYear(2025);
        book2.setAvailable(true);

        when(repository.findAll())
                .thenReturn(Arrays.asList(book, book2));

        List<BookDto> result = service.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(
                "Java Programming",
                result.get(0).getTitle()
        );

        assertEquals(
                "Spring Boot",
                result.get(1).getTitle()
        );

        verify(repository, times(1)).findAll();
    }

    // ==========================================================
    // GET BOOK BY ID - SUCCESS
    // ==========================================================

    @Test
    void getBookById_shouldReturnBook() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(book));

        BookDto result = service.getBookById(1L);

        assertNotNull(result);

        assertEquals(1L, result.getId());
        assertEquals(
                "Java Programming",
                result.getTitle()
        );

        assertEquals(
                "James Gosling",
                result.getAuthor()
        );

        assertEquals(
                599.0,
                result.getPrice()
        );

        verify(repository, times(1))
                .findById(1L);
    }

    // ==========================================================
    // GET BOOK BY ID - NOT FOUND
    // ==========================================================

    @Test
    void getBookById_shouldThrowException() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.getBookById(99L)
        );

        verify(repository, times(1))
                .findById(99L);
    }

    // ==========================================================
    // ADD BOOK
    // ==========================================================

    @Test
    void addBook_shouldSaveBook() {

        BookDto request = new BookDto();

        request.setTitle("Java Programming");
        request.setAuthor("James Gosling");
        request.setIsbn("1234567890");
        request.setCategory("Programming");
        request.setPrice(599.0);
        request.setQuantity(10);
        request.setPublisher("Tech Publisher");
        request.setPublishedYear(2024);
        request.setAvailable(true);

        when(repository.save(any(Book.class)))
                .thenReturn(book);

        BookDto result = service.addBook(request);

        assertNotNull(result);

        assertEquals(
                "Java Programming",
                result.getTitle()
        );

        assertEquals(
                "James Gosling",
                result.getAuthor()
        );

        verify(repository, times(1))
                .save(any(Book.class));
    }

    // ==========================================================
    // UPDATE BOOK
    // ==========================================================

    @Test
    void updateBook_shouldUpdateBook() {

        BookDto request = new BookDto();

        request.setTitle("Advanced Java");
        request.setAuthor("James Gosling");
        request.setIsbn("1234567890");
        request.setCategory("Advanced Programming");
        request.setPrice(799.0);
        request.setQuantity(20);
        request.setPublisher("Tech Publisher");
        request.setPublishedYear(2025);
        request.setAvailable(true);

        when(repository.findById(1L))
                .thenReturn(Optional.of(book));

        when(repository.save(any(Book.class)))
                .thenReturn(book);

        BookDto result =
                service.updateBook(1L, request);

        assertNotNull(result);

        assertEquals(
                "Advanced Java",
                result.getTitle()
        );

        assertEquals(
                799.0,
                result.getPrice()
        );

        assertEquals(
                20,
                result.getQuantity()
        );

        verify(repository, times(1))
                .findById(1L);

        verify(repository, times(1))
                .save(any(Book.class));
    }

    // ==========================================================
    // UPDATE BOOK - NOT FOUND
    // ==========================================================

    @Test
    void updateBook_shouldThrowException() {

        BookDto request = new BookDto();

        request.setTitle("Advanced Java");

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.updateBook(99L, request)
        );

        verify(repository, times(1))
                .findById(99L);

        verify(repository, never())
                .save(any(Book.class));
    }

    // ==========================================================
    // DELETE BOOK
    // ==========================================================

    @Test
    void deleteBook_shouldDeleteBook() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(book));

        BookDto result =
                service.deleteBook(1L);

        assertNotNull(result);

        assertEquals(
                1L,
                result.getId()
        );

        assertEquals(
                "Java Programming",
                result.getTitle()
        );

        verify(repository, times(1))
                .findById(1L);

        verify(repository, times(1))
                .delete(book);
    }

    // ==========================================================
    // DELETE BOOK - NOT FOUND
    // ==========================================================

    @Test
    void deleteBook_shouldThrowException() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.deleteBook(99L)
        );

        verify(repository, times(1))
                .findById(99L);

        verify(repository, never())
                .delete(any(Book.class));
    }
}