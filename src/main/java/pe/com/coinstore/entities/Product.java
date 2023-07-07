
package pe.com.coinstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;



    @ManyToMany
    @JoinTable(
            name = "products_storage",
            joinColumns = {
                    @JoinColumn (
                            name="product_id",
                            referencedColumnName = "id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn (
                            name = "storage_id",
                            referencedColumnName = "id",
                            nullable = false
                    )
            }
    )

    private List<Storage> storages;

}
