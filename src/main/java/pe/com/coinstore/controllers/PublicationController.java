package pe.com.coinstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.coinstore.entities.Publication;
import pe.com.coinstore.services.PublicationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @PostMapping("/publications")
    public ResponseEntity<Publication> createPublication(@RequestBody Publication publication) {
        Publication savedPublication=publicationService.save(publication);
        return new ResponseEntity<Publication>(savedPublication, HttpStatus.CREATED);
    }


}
