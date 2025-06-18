package com.polarbookshop.catalog_service.dto;

import com.polarbookshop.catalog_service.model.Book;

import java.math.BigDecimal;

public record BookDetailsDTO(
        String isbn,
        String title,
        String author,
        BigDecimal price
) {
    public static BookDetailsDTO fromBookModel(Book book) {
        return new BookDetailsDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPrice());
    }
}
