package com.library.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.library.models.entities.Book;

import jakarta.websocket.server.PathParam;

public interface BookRepo extends CrudRepository<Book, Long> {
    // Custom Query
    @Query("SELECT b FROM Book b WHERE b.bookName = :bookName")
    public Book findByBookName(@PathParam("bookName") String bookName);

    @Query("SELECT b FROM Book b WHERE b.bookName LIKE :bookName")
    public List<Book> findBookByNameLike(@PathParam("bookName") String bookName);

    // Derived Query Method
    List<Book> findByBookNameContainsOrderByIdDesc(String bookName);

    List<Book> findByBookNameContainsOrBookPriceContains(String bookName, Integer bookPrice);
}