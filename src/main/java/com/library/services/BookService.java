package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.models.entities.Book;
import com.library.models.entities.Member;
import com.library.models.repos.BookRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    public Iterable<Book> findAllBook() {
        return bookRepo.findAll();
    }

    public Book findOneBook(Long id) {
        Optional<Book> book = bookRepo.findById(id);

        if (!book.isPresent()) {
            return null;
        }
        return book.get();
    }

    public void removeBookById(Long id) {
        bookRepo.deleteById(id);
    }

    public void addMember(Member member, Long bookId) {
        Book book = findOneBook(bookId);

        if (book == null) {
            throw new RuntimeException("Book With ID : " + bookId + "Not Found");
        }
        book.getMembers().add(member);
        saveBook(book);
    }

    // Function to search
    public Book findByBookName(String book_name) {
        return bookRepo.findByBookName(book_name);
    }

    public List<Book> findBookByNameLike(String bookName) {
        return bookRepo.findBookByNameLike("%" + bookName + "%");
    }

    public List<Book> findByBookNameOrderByIdDesc(String bookName) {
        return bookRepo.findByBookNameContainsOrderByIdDesc(bookName);
    }

    public List<Book> findByBookNameOrBookPrice(String bookName, Integer bookPrice) {
        return bookRepo.findByBookNameContainsOrBookPriceContains(bookName, bookPrice);
    }
}
