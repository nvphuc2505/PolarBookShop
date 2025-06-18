package com.polarbookshop.catalog_service.controller;

import com.polarbookshop.catalog_service.dto.BookCreateDTO;
import com.polarbookshop.catalog_service.dto.BookDetailsDTO;
import com.polarbookshop.catalog_service.dto.BookListDTO;
import com.polarbookshop.catalog_service.dto.BookUpdateDTO;
import com.polarbookshop.catalog_service.model.Book;
import com.polarbookshop.catalog_service.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<BookListDTO> get() {
        return bookService.viewBookList();
    }

    @GetMapping("/{isbn}")
    public BookDetailsDTO getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetailsByIsbn(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody BookCreateDTO book) {
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    @PatchMapping("/{isbn}")
    public Book put(@PathVariable String isbn, @RequestBody BookUpdateDTO bookUpdateDTO) {
        return bookService.updateBookDetails(isbn, bookUpdateDTO);
    }

}
