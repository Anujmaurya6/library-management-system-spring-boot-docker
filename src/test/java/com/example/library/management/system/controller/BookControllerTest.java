package com.example.library.management.system.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.library.management.system.dto.BookDto;
import com.example.library.management.system.service.BookService;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    // ==========================================================
    // MOCK SERVICE
    // ==========================================================

    @Mock
    private BookService service;

    // ==========================================================
    // REAL CONTROLLER
    // ==========================================================

    @InjectMocks
    private BookController controller;

    // ==========================================================
    // TEST DATA
    // ==========================================================

    private BookDto bookDto;

    @BeforeEach
    void setUp() {

        bookDto = new BookDto();

        bookDto.setId(1L);
        bookDto.setTitle("Java Programming");
        bookDto.setAuthor("James Gosling");
        bookDto.setIsbn("1234567890");
        bookDto.setCategory("Programming");
        bookDto.setPrice(599.0);
        bookDto.setQuantity(10);
        bookDto.setPublisher("Tech Publisher");
        bookDto.setPublishedYear(2024);
        bookDto.setAvailable(true);
    }

    // ==========================================================
    // GET ALL BOOKS
    // ==========================================================

    @Test
    void getAllBooks_shouldReturnBooks() {

        List<BookDto> books =
                Arrays.asList(bookDto);

        when(service.getAllBooks())
                .thenReturn(books);

        ResponseEntity<List<BookDto>> response =
                controller.getAllBooks();

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        assertEquals(
                1,
                response.getBody().size()
        );

        assertEquals(
                "Java Programming",
                response.getBody()
                        .get(0)
                        .getTitle()
        );

        verify(service, times(1))
                .getAllBooks();
    }

    // ==========================================================
    // GET BOOK BY ID
    // ==========================================================

    @Test
    void getBookById_shouldReturnBook() {

        when(service.getBookById(1L))
                .thenReturn(bookDto);

        ResponseEntity<BookDto> response =
                controller.getBookById(1L);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        assertEquals(
                1L,
                response.getBody().getId()
        );

        assertEquals(
                "Java Programming",
                response.getBody().getTitle()
        );

        verify(service, times(1))
                .getBookById(1L);
    }

    // ==========================================================
    // ADD BOOK
    // ==========================================================

    @Test
    void addBook_shouldReturnCreated() {

        when(service.addBook(bookDto))
                .thenReturn(bookDto);

        ResponseEntity<BookDto> response =
                controller.addBook(bookDto);

        assertEquals(
                HttpStatus.CREATED,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        assertEquals(
                "Java Programming",
                response.getBody().getTitle()
        );

        verify(service, times(1))
                .addBook(bookDto);
    }

    // ==========================================================
    // UPDATE BOOK
    // ==========================================================

    @Test
    void updateBook_shouldReturnUpdatedBook() {

        when(service.updateBook(1L, bookDto))
                .thenReturn(bookDto);

        ResponseEntity<BookDto> response =
                controller.updateBook(1L, bookDto);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        assertEquals(
                "Java Programming",
                response.getBody().getTitle()
        );

        verify(service, times(1))
                .updateBook(1L, bookDto);
    }

    // ==========================================================
    // DELETE BOOK
    // ==========================================================

    @Test
    void deleteBook_shouldReturnDeletedBook() {

        when(service.deleteBook(1L))
                .thenReturn(bookDto);

        ResponseEntity<BookDto> response =
                controller.deleteBook(1L);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        assertEquals(
                1L,
                response.getBody().getId()
        );

        verify(service, times(1))
                .deleteBook(1L);
    }
}