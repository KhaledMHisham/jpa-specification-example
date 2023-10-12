package com.example.springpractice.services;

import com.example.springpractice.persistence.entities.Book;
import com.example.springpractice.logging.performance.annotations.LogTimeElapsed;
import com.example.springpractice.persistence.entities.enums.Genre;
import com.example.springpractice.persistence.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

import static com.example.springpractice.utils.RandomNumberGenerator.getRandomNumberInRange;

@Service
@AllArgsConstructor
public class BookService {

    private final HashSet<String> generatedIsbns = new HashSet<>();
    private final BookRepository bookRepository;

    @LogTimeElapsed(message = "PERSISTING BOOKS")
    @Transactional
    public void saveAll(List<Book> books){
        bookRepository.saveAll(books);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @LogTimeElapsed(message = "GENERATING BOOKS")
    public List<Book> generateRandomBooks(int numberOfBooks) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < numberOfBooks; i++) {
            books.add(generateBook());
        }
        return books;
    }

    public Book generateBook() {
        Book book = new Book();
        book.setIsbn(generateRandomIsbn());
        book.setTitle(generateRandomTitle());
        book.setPublicationYear(generateRandomPublicationYear());
        book.setGenre(generateRandomGenre());
        book.setPublisher(generateRandomPublisher());
        book.setPrice(generateRandomPrice());
        return book;
    }

    private String generateRandomIsbn() {
        while (true) {
            int random = getRandomNumberInRange(100000, 9999999);
            String isbn = "ISBN-" + random;
            if (!generatedIsbns.contains(isbn)) {
                generatedIsbns.add(isbn);
                return isbn;
            }
        }
    }

    private String generateRandomTitle() {
        return "Title-" + getRandomNumberInRange(1, 1000);
    }

    private LocalDate generateRandomPublicationYear() {
        int year = getRandomNumberInRange(1900, 2023);
        return LocalDate.of(year, 1, 1);
    }

    private Genre generateRandomGenre() {
        Genre[] genres = Genre.values();
        int randomIndex = getRandomNumberInRange(0, genres.length - 1);
        return genres[randomIndex];
    }

    private String generateRandomPublisher() {
        return "Publisher-" + getRandomNumberInRange(1, 50);
    }

    private Long generateRandomPrice() {
        return (long) getRandomNumberInRange(500, 5000);
    }

}
