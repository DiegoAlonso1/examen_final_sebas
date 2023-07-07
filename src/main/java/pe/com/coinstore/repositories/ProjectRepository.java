package pe.com.coinstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.coinstore.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
