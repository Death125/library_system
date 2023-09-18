package com.library.controllers;

import java.util.List;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookData;
import com.library.dto.ResponseData;
import com.library.dto.SearchData;
import com.library.models.entities.Book;
import com.library.models.entities.Member;
import com.library.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Book>> create(@Valid @RequestBody BookData bookData, Errors errors) {
        ResponseData<Book> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Book book = modelMapper.map(bookData, Book.class);

        responseData.setStatus(true);
        responseData.setPayload(bookService.saveBook(book));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData<Book>> update(@Valid @RequestBody BookData bookData, Errors errors) {
        ResponseData<Book> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Book book = modelMapper.map(bookData, Book.class);

        responseData.setStatus(true);
        responseData.setPayload(bookService.updateBook(book));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/findAllBook")
    public Iterable<Book> findAllBook() {
        return bookService.findAllBook();
    }

    @GetMapping("/{id}")
    public Book findOneBook(@PathVariable("id") Long id) {
        return bookService.findOneBook(id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeBookById(@PathVariable("id") Long id) {
        bookService.removeBookById(id);
    }

    @PostMapping("/addMember/{id}")
    public void addMember(@RequestBody Member member, @PathVariable("id") Long bookId) {
        bookService.addMember(member, bookId);
    }

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
