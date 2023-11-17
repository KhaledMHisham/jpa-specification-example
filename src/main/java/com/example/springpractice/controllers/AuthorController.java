package com.example.springpractice.controllers;

import com.example.springpractice.persistence.entities.*;
import com.example.springpractice.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    @GetMapping
    public List<Author> findAllAuthors(
            @RequestParam(value = "authorName", required = false) String authorName
            ,@RequestParam(value = "bookTitle", required = false) String bookTitle
            ,@RequestParam(value = "isbn", required = false) String isbn) {

        System.out.println("AUTHOR -> " + authorName);
        System.out.println("BOOK   -> " + bookTitle);
        System.out.println("ISBN   -> " + isbn);
        return authorService.findAllByName(authorName, bookTitle, isbn);
    }
}
