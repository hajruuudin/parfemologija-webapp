package ba.parfemologija.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pf_brand")
public class BrandEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slug", length = 255)
    private String slug;

    @Column(name = "brand_name", length = 255)
    private String brandName;

    @Column(name = "brand_description", columnDefinition = "text")
    private String brandDescription;

    @Column(name = "brand_creation_date")
    private LocalDate brandCreationDate;
}
