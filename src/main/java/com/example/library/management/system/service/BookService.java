package com.example.library.management.system.service;

import java.util.List;

import com.example.library.management.system.dto.BookDto;
import com.example.library.management.system.entity.Book;

import jakarta.validation.Valid;

public interface BookService {

	List<BookDto> getAllBooks();

	BookDto getBookById(Long id);

	BookDto addBook(@Valid BookDto bookDto);

	BookDto updateBook(Long id, @Valid BookDto bookDto);

	BookDto deleteBook(Long id);
	

}
