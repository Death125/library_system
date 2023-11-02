package com.library.models.entities;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@Builder
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name = "book_generator", sequenceName = "book_sequence_value", allocationSize = 1)
    private Long id;

    @Column(name = "book_name", length = 200)
    private String bookName;

    @Column(name = "book_description", length = 500)
    private String bookDescription;

    @Column(name = "book_price", length = 11)
    private Integer bookPrice;

    @Column(name = "book_amount", length = 11)
    private Integer bookAmount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    // @ManyToMany
    // @JoinTable(name = "tbl_book_member", joinColumns = @JoinColumn(name =
    // "book_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    // private Set<Member> members;

    // @ManyToMany
    // @JoinTable(name = "tbl_book_employee", joinColumns = @JoinColumn(name =
    // "book_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    // private Set<Employee> employees;
}
