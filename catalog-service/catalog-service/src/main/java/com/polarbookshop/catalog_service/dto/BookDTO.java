package com.polarbookshop.catalog_service.dto;

import com.polarbookshop.catalog_service.model.Book;
import jakarta.validation.constraints.NotBlank;

public record BookDTO(

        @NotBlank(message = "The book ISBN must be defined.")
        String isbn,

        @NotBlank(message = "The book title must be defined.")
        String title,

        @NotBlank(message = "The book author must be defined.")
        String author,

        @NotBlank(message = "The book price must be defined.")
        Double price

) {
    /*
    public static BookDTO fromBookModel(Book book) {
        return new BookDTO(
                book.getIsbn(),
                book.
        )
    }
    */
}
