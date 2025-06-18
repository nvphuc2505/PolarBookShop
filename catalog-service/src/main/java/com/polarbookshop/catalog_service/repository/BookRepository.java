package com.polarbookshop.catalog_service.repository;

import com.polarbookshop.catalog_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository
        extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
