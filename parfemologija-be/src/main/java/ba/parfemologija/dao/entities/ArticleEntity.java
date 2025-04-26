package ba.parfemologija.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// This is let's say an article which represents a user selling a fragrance. So not the actual fragrance information on the database but his own personal fragrance item he wants to sell
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pf_article")
public class ArticleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Other columns
}
