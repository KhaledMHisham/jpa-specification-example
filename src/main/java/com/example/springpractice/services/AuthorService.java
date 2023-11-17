package com.example.springpractice.services;

import com.example.springpractice.logging.performance.annotations.LogTimeElapsed;
import com.example.springpractice.persistence.entities.*;
import com.example.springpractice.persistence.repositories.AuthorRepository;
import com.example.springpractice.persistence.repositories.specifications.AuthorSpecification;
import com.example.springpractice.persistence.repositories.specifications.SpecificationsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static com.example.springpractice.persistence.repositories.specifications.SpecificationsUtils.has;
import static com.example.springpractice.persistence.repositories.specifications.SpecificationsUtils.join;
import static com.example.springpractice.utils.RandomNumberGenerator.getRandomNumberInRange;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    @LogTimeElapsed(message = "PERSISTING AUTHORS")
    public void saveAll(List<Author> authors){
        authorRepository.saveAll(authors);
    }

    public List<Author> findAllByName(String authorName, String bookTitle, String isbn){
        return authorRepository.findAll(
            new AuthorSpecification<Author>()
                .getSpec(authorName, bookTitle, isbn)
        );
    }

    @LogTimeElapsed(message = "GENERATING AUTHORS")
    public List<Author> generateRandomAuthors(long numberOfAuthors){
        List<Author> authors = new ArrayList<>();
        for(int i = 0; i < numberOfAuthors; ++i){
            authors.add(generateRandomAuthor());
        }
        return authors;
    }
    public Author generateRandomAuthor(){
        Author author = new Author();
        author.setName(generateRandomName());
        author.setAge(generateRandomAge());
        return author;
    }
    private String generateRandomName() {
        return "Author-" + getRandomNumberInRange(1, 1000);
    }
    private Integer generateRandomAge() {
        return getRandomNumberInRange(20, 120);
    }
}
