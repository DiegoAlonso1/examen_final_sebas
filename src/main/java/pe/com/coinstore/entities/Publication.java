package pe.com.coinstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="publications")
@Data
@NoArgsConstructor
public class Publication {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Date datePublish;
    private String genre;
    private Double sales;
    private String type;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    public Publication(String title, Date datePublish, String genre, Double sales, String type, Author author) {
        this.title = title;
        this.datePublish = datePublish;
        this.genre = genre;
        this.sales = sales;
        this.type = type;
        this.author = author;
    }
}
