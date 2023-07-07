package pe.com.coinstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="authors")
@Data
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String firstName;
    private String lastName;
    private Date dateAffiliation;
    private Date dateBirth;

    @OneToMany(mappedBy = "author")
    private List<Publication> publications;

    public Author(String dni, String firstName, String lastName, Date dateAffiliation, Date dateBirth) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateAffiliation = dateAffiliation;
        this.dateBirth = dateBirth;
    }
}
