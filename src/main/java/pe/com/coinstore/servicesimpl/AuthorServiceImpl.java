package pe.com.coinstore.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.coinstore.dtos.DTOAuthorSummary;
import pe.com.coinstore.entities.Author;
import pe.com.coinstore.repositories.AuthorRepository;
import pe.com.coinstore.services.AuthorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author save(Author author) {
        Author newAuthor = new Author(author.getDni(), author.getFirstName(), author.getLastName(), author.getDateAffiliation(), author.getDateBirth());
        Author savedAuthor = authorRepository.save(newAuthor);
        return savedAuthor;
    }


    public List<DTOAuthorSummary> listAuthorSummary() {
        List<Author> authorsList = authorRepository.findAll();
        List<DTOAuthorSummary> authorSummaryList = new ArrayList<>();

        for(Author a: authorsList) {
            String fullName = a.getFirstName()+" "+a.getLastName();
            //Opcion Larga
            /*
            Integer countBooks=0;
            Integer countPoems=0;
            Double totalSales=0.0;
            for(Publication p: a.getPublications() ) {
                if (p.getType().equals("Libro")) {
                    countBooks++;
                } else {
                     if (p.getType().equals("Poemario")) {
                        countPoems++;
                    }
                }
                totalSales = totalSales + p.getSales();
            }
             */
            //Opcion Corta
            Integer countBooks = (int)a.getPublications().stream().filter(p->p.getType().equals("Libro")).count();
            Integer countPoems = (int)a.getPublications().stream().filter(p->p.getType().equals("Poemario")).count();
            Double totalSales = a.getPublications().stream().map(p->p.getSales()).reduce(0.0,Double::sum);

            DTOAuthorSummary dtoAuthorSummary = new DTOAuthorSummary(a.getDni(), fullName, countBooks, countPoems, a.getDateAffiliation(), totalSales);
            authorSummaryList.add(dtoAuthorSummary);

        }

        return authorSummaryList;

    }
}
