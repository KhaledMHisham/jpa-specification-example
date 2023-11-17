package com.example.springpractice.persistence.entities;

import com.example.springpractice.persistence.entities.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @JsonIgnore
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Set<Author> authors = new HashSet<>();

    @Column(name = "publication_year")
    private LocalDate publicationYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    private String publisher;

    private Long price;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", genre=" + genre +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                '}';
    }
}