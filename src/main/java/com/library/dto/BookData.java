package com.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookData {

    private Long id;

    @NotEmpty(message = "Name is required")
    private String bookName;

    @NotEmpty(message = "Description is required")
    private String book_description;

    @Min(value = 1, message = "Amount >= 1")
    @NotNull(message = "Amount cannot be null")
    private Integer book_amount;

    @Min(value = 1, message = "Price >= 1")
    @NotNull(message = "Price cannot be null")
    private Integer bookPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public Integer getBook_amount() {
        return book_amount;
    }

    public void setBook_amount(Integer book_amount) {
        this.book_amount = book_amount;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

}
