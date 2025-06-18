package com.polarbookshop.catalog_service.dto;

import java.math.BigDecimal;

public record BookListDTO(
        String title,
        String author,
        BigDecimal price
) {
    public static BookListDTO of(String title,
                                 String author,
                                 BigDecimal price) {
        return new BookListDTO(title, author, price);
    }
}
