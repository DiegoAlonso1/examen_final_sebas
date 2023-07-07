package pe.com.coinstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="projects_employees")
@Data
@NoArgsConstructor
public class ProjectEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    private Integer hoursWorked; //camel case


    public ProjectEmployee(Project project, Employee employee, Integer hoursWorked) {
        this.project = project;
        this.employee = employee;
        this.hoursWorked = hoursWorked;
    }
}
