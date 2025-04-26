package ba.parfemologija.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Images for the fragrance. Similar to how its done for an article
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pf_fragrance_images")
public class FragranceImagesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Other columns
}

