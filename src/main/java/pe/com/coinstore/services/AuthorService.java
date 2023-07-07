package pe.com.coinstore.services;

import pe.com.coinstore.dtos.DTOAuthorSummary;
import pe.com.coinstore.entities.Author;

import java.util.List;

public interface AuthorService {
    public Author save(Author author);

    public List<DTOAuthorSummary> listAuthorSummary();
}
