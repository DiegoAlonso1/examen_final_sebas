package pe.com.coinstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="employees")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;
    private String dni;

    //@JsonIgnore
    @OneToMany(mappedBy = "employee", cascade={CascadeType.REMOVE})
    private List<ProjectEmployee> projectEmployeeList;
    public Employee(String name, String email, int age, String dni) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.dni = dni;
    }
}
