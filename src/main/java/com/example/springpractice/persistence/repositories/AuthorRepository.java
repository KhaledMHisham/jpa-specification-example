package com.example.springpractice.persistence.repositories;

import com.example.springpractice.persistence.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
