package com.polarbookshop.catalog_service.dto;

import java.math.BigDecimal;

public record BookUpdateDTO(
        String isbn,
        String title,
        String author,
        BigDecimal price
) {
}
