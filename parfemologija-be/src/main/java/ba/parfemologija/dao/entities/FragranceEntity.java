package ba.parfemologija.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pf_fragrance")
public class FragranceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "official_name", nullable = false)
    private String officialName;

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

    @Column(name = "type_id", nullable = false)
    private Integer typeId;

    @Column(name = "winter_rating")
    private Short winterRating;

    @Column(name = "fall_rating")
    private Short fallRating;

    @Column(name = "summer_rating")
    private Short summerRating;

    @Column(name = "spring_rating")
    private Short springRating;

    @Column(name = "sillageRating") // Corrected column name
    private Short sillageRating;

    @Column(name = "longevity_rating")
    private Short longevityRating;

    @Column(name = "price_range")
    private String priceRange;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "gender")
    private String gender;
}
