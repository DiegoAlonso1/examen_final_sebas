package pe.com.coinstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DTOAuthorSummary {

    private String dni;
    private String fullName;
    private Integer countBooks;
    private Integer countPoems;
    private Date dateAffiliation;
    private Double totalSales;

}
