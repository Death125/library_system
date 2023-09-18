package com.library.models.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_book")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "book_name", length = 200)
    public String bookName;

    @Column(name = "book_description", length = 500)
    public String book_description;

    @Column(name = "book_amount", length = 11)
    public Integer book_amount;

    @Column(name = "book_price", length = 11)
    public Integer bookPrice;

    @ManyToMany
    @JoinTable(name = "tbl_book_member", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    public Set<Member> members;

    @ManyToMany
    @JoinTable(name = "tbl_book_employee", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    public Set<Employee> employees;

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

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
