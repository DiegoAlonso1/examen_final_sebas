package pe.com.coinstore.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.coinstore.dtos.DTOAuthorSummary;
import pe.com.coinstore.entities.Author;
import pe.com.coinstore.entities.Publication;
import pe.com.coinstore.repositories.AuthorRepository;
import pe.com.coinstore.repositories.PublicationRepository;
import pe.com.coinstore.services.PublicationService;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    public Publication save(Publication publication){
        Author authorFound = authorRepository.findById(publication.getAuthor().getId()).get();
        Publication newPublication = new Publication(publication.getTitle(),publication.getDatePublish(), publication.getGenre(), publication.getSales(), publication.getType(), authorFound);
        Publication savedPublication = publicationRepository.save(newPublication);
        savedPublication.getAuthor().setPublications(null);
        return savedPublication;
    }



}
