package pe.com.coinstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.coinstore.dtos.DTOAuthorSummary;
import pe.com.coinstore.entities.Author;
import pe.com.coinstore.services.AuthorService;

import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.save(author);
        return new ResponseEntity<Author>(savedAuthor, HttpStatus.CREATED);
    }


    @GetMapping("/authors/summary")
    public ResponseEntity<List<DTOAuthorSummary>> getAuthorsSummary() {
        List<DTOAuthorSummary> dtoAuthorSummaries = authorService.listAuthorSummary();
        return new ResponseEntity<List<DTOAuthorSummary>>(dtoAuthorSummaries, HttpStatus.OK);
    }

}