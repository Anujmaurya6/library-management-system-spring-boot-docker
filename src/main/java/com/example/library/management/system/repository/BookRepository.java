package com.example.library.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.management.system.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {

}
