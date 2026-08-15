package com.example.library.management.system.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "books",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_books_isbn",
            columnNames = "isbn"
        )
    }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================================
    // TITLE
    // ==========================================================

    @Column(
        nullable = false,
        length = 150
    )
    private String title;

    // ==========================================================
    // AUTHOR
    // ==========================================================

    @Column(
        nullable = false,
        length = 100
    )
    private String author;

    // ==========================================================
    // ISBN
    // ==========================================================

    @Column(
        nullable = false,
        unique = true,
        length = 20
    )
    private String isbn;

    // ==========================================================
    // CATEGORY
    // ==========================================================

    @Column(
        nullable = false,
        length = 50
    )
    private String category;

    // ==========================================================
    // PRICE
    // ==========================================================

    @Column(
        nullable = false
    )
    private Double price;

    // ==========================================================
    // QUANTITY
    // ==========================================================

    @Column(
        nullable = false
    )
    private Integer quantity;

    // ==========================================================
    // PUBLISHER
    // ==========================================================

    @Column(
        nullable = false,
        length = 100
    )
    private String publisher;

    // ==========================================================
    // PUBLISHED YEAR
    // ==========================================================

    @Column(
        nullable = false
    )
    private Integer publishedYear;

    // ==========================================================
    // AVAILABLE
    // ==========================================================

    @Column(
        nullable = false
    )
    private boolean available = true;

    // ==========================================================
    // DEFAULT CONSTRUCTOR
    // ==========================================================

    public Book() {
    }

    // ==========================================================
    // GETTERS & SETTERS
    // ==========================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // ==========================================================
    // TO STRING
    // ==========================================================

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", publisher='" + publisher + '\'' +
                ", publishedYear=" + publishedYear +
                ", available=" + available +
                '}';
    }
}