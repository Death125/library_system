package com.library.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookRequest;

import com.library.dto.SearchData;
import com.library.exceptions.BookNotFoundException;
import com.library.models.entities.Book;

import com.library.services.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<Book>(bookService.createBook(bookRequest), HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody BookRequest bookRequest) throws BookNotFoundException {
        return new ResponseEntity<Book>(bookService.updateBook(bookRequest), HttpStatus.CREATED);

    }

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message) {
        return ResponseEntity.ok("Message sent to the topic");
    }

    @GetMapping("/findAllBook")
    public ResponseEntity<Iterable<Book>> findAllBook() throws BookNotFoundException {
        return ResponseEntity.ok(bookService.findAllBook());
    }

    @GetMapping("/findOneBook/{id}")
    public ResponseEntity<Book> findOneBook(@PathVariable("id") Long id) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.findOneBook(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeBookById(@PathVariable("id") Long id) throws BookNotFoundException {
        bookService.removeBookById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // @PostMapping("/addMember/{id}")
    // public void addMember(@RequestBody Member member, @PathVariable("id") Long
    // bookId) {
    // bookService.addMember(member, bookId);
    // }

    // URL to search book
    @PostMapping("/search/bookName")
    public Book getByBookName(@RequestBody SearchData searchData) {
        return bookService.findByBookName(searchData.getSearchKey());
    }

    @PostMapping("/search/bookLike")
    public List<Book> getBookByNameLike(@RequestBody SearchData searchData) {
        return bookService.findBookByNameLike(searchData.getSearchKey());
    }

    @PostMapping("/search/byNameOrderById")
    public List<Book> findByBookNameOrderById(@RequestBody SearchData searchData) {
        return bookService.findByBookNameOrderByIdDesc(searchData.getSearchKey());
    }

    @PostMapping("/search/byNameOrPrice")
    public List<Book> findByBookNameOrBookPrice(@RequestBody SearchData searchData) {
        return bookService.findByBookNameOrBookPrice(searchData.getSearchKey(), searchData.getOtherSearchKey());
    }
}
