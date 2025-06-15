package com.polarbookshop.catalog_service.controller;

import com.polarbookshop.catalog_service.dto.BookDTO;
import com.polarbookshop.catalog_service.model.Book;
import com.polarbookshop.catalog_service.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetailsByIsbn(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody BookDTO book) {
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    public Book put(@PathVariable String isbn, @Valid @RequestBody BookDTO book) {
        return bookService.updateBookDetails(isbn, book);
    }

}
