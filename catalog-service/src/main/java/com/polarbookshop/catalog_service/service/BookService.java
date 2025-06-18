package com.polarbookshop.catalog_service.service;

import com.polarbookshop.catalog_service.dto.BookCreateDTO;
import com.polarbookshop.catalog_service.dto.BookDetailsDTO;
import com.polarbookshop.catalog_service.dto.BookListDTO;
import com.polarbookshop.catalog_service.dto.BookUpdateDTO;
import com.polarbookshop.catalog_service.exception.BookAlreadyExistsException;
import com.polarbookshop.catalog_service.exception.BookNotFoundException;
import com.polarbookshop.catalog_service.model.Book;
import com.polarbookshop.catalog_service.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Service layer responsible for handling business logic related to Book catalog operations.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves all books from the catalog.
     *
     * @return list of all books with BookListDTO object
     */
    public Iterable<BookListDTO> viewBookList() {

        return bookRepository.findAll()
            .stream()
            .map(
                book ->
                    BookListDTO.of(book.getTitle(),
                            book.getAuthor(),
                            book.getPrice())
            ).toList();

    }

    /**
     * Retrieves the book with the given ISBN.
     *
     * @param isbn the ISBN of the book
     * @return the matching Book object
     * @throws BookNotFoundException if no book is found with the given ISBN
     */
    public BookDetailsDTO viewBookDetailsByIsbn(String isbn) {
        return BookDetailsDTO.fromBookModel(bookRepository.findByIsbn(isbn)
                    .orElseThrow(() -> new BookNotFoundException(isbn)));
    }

    /**
     * Adds a new book to the catalog.
     *
     * @param bookCreateDTO the data transfer object containing book details
     * @return the saved Book object
     * @throws BookAlreadyExistsException if a book with the same ISBN already exists
     */
    public Book addBookToCatalog(BookCreateDTO bookCreateDTO) {

        if (bookRepository.existsByIsbn(bookCreateDTO.isbn()))
            throw new BookAlreadyExistsException(bookCreateDTO.isbn());

        Book newBook = new Book (
                bookCreateDTO.isbn(),
                bookCreateDTO.title(),
                bookCreateDTO.author(),
                bookCreateDTO.price()
        );

        return bookRepository.save(newBook);
    }

    /**
     * Removes a book from the catalog by its ISBN.
     *
     * @param isbn the ISBN of the book to be removed
     */
    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    /**
     * Updates details of an existing book in the catalog.
     *
     * @param isbn the ISBN of the book to update
     * @param bookUpdateDTO the DTO containing new book data
     * @return the updated Book object
     * @throws BookNotFoundException if no book is found with the given ISBN
     */
    public Book updateBookDetails(String isbn, BookUpdateDTO bookUpdateDTO) {

        Book existingBook = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        // Update by part
        if (bookUpdateDTO.isbn() != null && !bookUpdateDTO.isbn().equals(isbn))
            existingBook.setIsbn(bookUpdateDTO.isbn());

        if (bookUpdateDTO.title() != null && !bookUpdateDTO.title().equals(existingBook.getTitle()))
            existingBook.setTitle(bookUpdateDTO.title());

        if (bookUpdateDTO.author() != null && !bookUpdateDTO.author().equals(existingBook.getAuthor()))
            existingBook.setAuthor(bookUpdateDTO.author());

        if (bookUpdateDTO.price() != null && !bookUpdateDTO.price().equals(existingBook.getPrice()))
            existingBook.setPrice(bookUpdateDTO.price());

        return bookRepository.save(existingBook);
    }
}
