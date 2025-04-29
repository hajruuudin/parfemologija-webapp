package ba.parfemologija.dao.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


// ACCORD: This just serves as a collection of different fragrance accords, similar to how it's done on Fragrantica
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pf_accord")
public class AccordEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "accord_name")
    private String accordName;

    @Column(name = "accord_description")
    private String accordDescription;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
