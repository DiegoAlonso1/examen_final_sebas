package pe.com.coinstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.coinstore.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
