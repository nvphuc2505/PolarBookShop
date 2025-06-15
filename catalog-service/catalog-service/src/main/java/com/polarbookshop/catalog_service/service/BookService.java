package com.polarbookshop.catalog_service.service;

import com.polarbookshop.catalog_service.dto.BookDTO;
import com.polarbookshop.catalog_service.exception.BookAlreadyExistsException;
import com.polarbookshop.catalog_service.exception.BookNotFoundException;
import com.polarbookshop.catalog_service.model.Book;
import com.polarbookshop.catalog_service.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetailsByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                    .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(BookDTO bookDTO) {

        if (bookRepository.existsByIsbn(bookDTO.isbn()))
            throw new BookAlreadyExistsException(bookDTO.isbn());

        Book newBook = new Book (
                bookDTO.isbn(),
                bookDTO.title(),
                bookDTO.author(),
                bookDTO.price()
        );

        return bookRepository.save(newBook);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book updateBookDetails(String isbn, BookDTO book) {

        Book existingBook = viewBookDetailsByIsbn(isbn);

        if (existingBook == null)
            return null;

        // Update by part
        if (book.isbn() != null && !book.isbn().equals(isbn))
            existingBook.setIsbn(book.isbn());

        if (book.title() != null && !book.title().equals(existingBook.getTitle()))
            existingBook.setTitle(book.title());

        if (book.author() != null && !book.author().equals(existingBook.getAuthor()))
            existingBook.setAuthor(book.author());

        if (book.price() != null && !book.price().equals(existingBook.getPrice()))
            existingBook.setPrice(book.price());

        return existingBook;
    }
}
