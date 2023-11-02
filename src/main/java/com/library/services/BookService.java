package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.BookRequest;
import com.library.exceptions.BookNotFoundException;
// import com.library.kafka.KafkaProducer;
import com.library.models.entities.Book;
import com.library.models.entities.Member;
import com.library.models.repositories.BookRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepo;
    // private KafkaProducer kafkaProducer;

    // public BookService(KafkaProducer kafkaProducer) {
    // this.kafkaProducer = kafkaProducer;
    // }

    public Book createBook(BookRequest bookRequest) {
        Book book = Book.builder().bookName(bookRequest.getBookName()).bookDescription(bookRequest.getBookDescription())
                .bookPrice(bookRequest.getBookPrice()).bookAmount(bookRequest.getBookAmount()).build();

        return bookRepo.save(book);
    }

    public Book updateBook(BookRequest bookRequest) throws BookNotFoundException {
        Optional<Book> book = bookRepo.findById(bookRequest.getId());

        if (book.isPresent()) {
            book.get().setBookName(bookRequest.getBookName());
            book.get().setBookDescription(bookRequest.getBookDescription());
            book.get().setBookPrice(bookRequest.getBookPrice());
            book.get().setBookAmount(bookRequest.getBookAmount());
            book.get().setDateUpdated(bookRequest.getDateUpdated());

            return bookRepo.save(book.get());
        } else {
            throw new BookNotFoundException("Book With ID " + bookRequest.getId() + " Not Found");
        }
    }

    // public String publish(String message) {
    // kafkaProducer.sendMessage(message);
    // return "Message sent to the topic";
    // }

    public Iterable<Book> findAllBook() throws BookNotFoundException {
        List<Book> books = bookRepo.findAll();

        if (books.size() <= 0) {
            throw new BookNotFoundException("No one book in here !!");
        } else {
            return books;
        }
    }

    public Book findOneBook(Long id) throws BookNotFoundException {
        Optional<Book> book = bookRepo.findById(id);

        if (book.isPresent())
            return book.get();
        else
            throw new BookNotFoundException("Book with id " + id + " not found");
    }

    public void removeBookById(Long id) throws BookNotFoundException {
        Optional<Book> book = bookRepo.findById(id);

        if (!book.isPresent()) {
            throw new BookNotFoundException("Book with id " + book.get().getId() + " not found");
        } else {
            bookRepo.deleteById(id);
        }
    }

    // public void addMember(Member member, Long bookId) {
    // Book book = findOneBook(bookId);

    // if (book == null) {
    // throw new RuntimeException("Book With ID : " + bookId + "Not Found");
    // }
    // book.getMembers().add(member);
    // saveBook(book);
    // }

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
