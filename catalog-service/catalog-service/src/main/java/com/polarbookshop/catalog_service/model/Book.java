package com.polarbookshop.catalog_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String title;
    private String author;
    private Double price;

    public Book(String isbn, String title, String author, Double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
