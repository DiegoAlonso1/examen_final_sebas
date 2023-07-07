package pe.com.coinstore.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.coinstore.entities.Employee;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByDni(String dni);

    Employee findByAgeAndEmail(Integer age, String email);

    List<Employee> findByEmail(String email);

    @Query(value="SELECT * FROM employees WHERE email=?1 AND age<?2", nativeQuery = true)    // con lenguaje SQL nativo
    List<Employee> findByCityAndLowerAge(String city, Integer age);

    @Query("SELECT e FROM Employee e WHERE e.email=?1 AND e.age<?2")    // con lenguaje JPQL
    List<Employee> findByCityAndLowerAgeJPQL(String city, Integer age);


    List<Employee> findByNameContaining(String name);



}
