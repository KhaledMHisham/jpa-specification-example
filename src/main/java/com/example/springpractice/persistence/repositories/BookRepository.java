package com.example.springpractice.persistence.repositories;

import com.example.springpractice.persistence.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
