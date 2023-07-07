package pe.com.coinstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.coinstore.entities.ProjectEmployee;

import java.util.List;

public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployee, Long> {

    List<ProjectEmployee> findByEmployee_Id(Long id);
    List<ProjectEmployee> findByProject_Id(Long id);


}
