package pe.com.coinstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.coinstore.entities.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
