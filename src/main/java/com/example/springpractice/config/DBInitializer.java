package com.example.springpractice.config;

import com.example.springpractice.persistence.entities.Author;
import com.example.springpractice.persistence.entities.Book;
import com.example.springpractice.services.AuthorService;
import com.example.springpractice.services.BookService;
import com.example.springpractice.utils.RandomNumberGenerator;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import static com.example.springpractice.utils.RandomNumberGenerator.getRandomNumberInRange;


@Component
@AllArgsConstructor
public class DBInitializer implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        List<Book> books = bookService.generateRandomBooks(100000);
        List<Author> authors = authorService.generateRandomAuthors(10000);
        bookService.saveAll(books);
        authorService.saveAll(authors);
        associateRandomBooksToAuthors(books, authors);
    }

    @Transactional
    public void associateRandomBooksToAuthors(List<Book> books, List<Author> authors) {
        Set<Book> randomBooks = new HashSet<>();
        for(int i = 0; i < 3; ++i){
            randomBooks.add(books.get(getRandomNumberInRange(1, books.size())));
        }
        for (Author author : authors) {
            author.addBooks(randomBooks);
        }
        authorService.saveAll(authors);
    }
 }