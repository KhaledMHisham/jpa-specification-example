package com.example.springpractice.controllers;


import com.example.springpractice.persistence.entities.Book;
import com.example.springpractice.persistence.repositories.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public Collection<Book> findAllBooks(){
        Pageable pageRequest = PageRequest.of(1,2);
        return bookRepository.findAll(pageRequest).toList();
    }

    @PostMapping("/books")
    public String doSomeShit(@RequestParam("shit") String shit){
        if(shit != null){
            return shit;
        }
        return "wut?";
    }
}
