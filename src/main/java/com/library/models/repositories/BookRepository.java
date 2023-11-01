package com.library.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.models.entities.Book;

import jakarta.websocket.server.PathParam;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Custom Query
    @Query("SELECT b FROM Book b WHERE b.bookName = :bookName")
    public Book findByBookName(@PathParam("bookName") String bookName);

    @Query("SELECT b FROM Book b WHERE b.bookName LIKE :bookName")
    public List<Book> findBookByNameLike(@PathParam("bookName") String bookName);

    // Derived Query Method
    List<Book> findByBookNameContainsOrderByIdDesc(String bookName);

    List<Book> findByBookNameContainsOrBookPriceContains(String bookName, Integer bookPrice);
}