package ba.parfemologija.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// This is the images for the article. One is labeled as primary, which is the thumbnail and will show up first.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pf_accord")
public class ArticleImagesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Other columns
}
