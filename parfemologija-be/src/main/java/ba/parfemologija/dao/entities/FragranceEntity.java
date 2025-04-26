package ba.parfemologija.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Main table for a fragrance. When returning data, you should join tables for the fragrance accords, notes, type, images and reviews if there are any
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

    //Other columns
}
