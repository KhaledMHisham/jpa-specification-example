package com.example.springpractice.persistence.repositories.specifications;

import com.example.springpractice.persistence.entities.Author;
import com.example.springpractice.persistence.entities.Author_;
import com.example.springpractice.persistence.entities.Book;
import com.example.springpractice.persistence.entities.Book_;
import org.springframework.data.jpa.domain.Specification;

import static com.example.springpractice.persistence.repositories.specifications.SpecificationsUtils.*;


public class AuthorSpecification<T>{
    private final Specification<T> spec = Specification.where(null);
    public Specification<T> getSpec(String authorName, String bookTitle, String isbn) {
        return spec.and(has(Author_.NAME, authorName))
                .and(join(Author_.BOOKS, Book_.TITLE, bookTitle))
                .and(join(Author_.BOOKS, Book_.ISBN, isbn));
    }
}
