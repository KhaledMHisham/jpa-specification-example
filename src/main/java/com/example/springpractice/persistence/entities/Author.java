package com.example.springpractice.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Integer age;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public void addBooks(Set<Book> books) {
        this.getBooks().addAll(books);
    }
}
