package ba.parfemologija.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
@Table(name = "pf_sample")
@NamedQuery(name = "SampleEntity.findAll", query = "SELECT e FROM SampleEntity e")
public class SampleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sample_name")
    private String name;

}
