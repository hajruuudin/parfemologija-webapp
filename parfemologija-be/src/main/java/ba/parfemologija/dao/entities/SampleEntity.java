package ba.parfemologija.dao.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
*   ENTITIES: Every table inside the database has its corresponding entity here. All the columns
*   from the database table have tobe present here. Its basically a mirror of the table.
*/

@Entity // This thing basically says that it will be an entity class, this is the thing that accesses data from a table
@NoArgsConstructor // This is from lombok, its basically there so that u don't have to write a no argument constructor
@Data // This is from lombok as well, gives the class getters and setters so that u don't have to wrote it
@Table(name = "pf_sample") // This is the table from the database, it needs to match the exact name
public class SampleEntity implements Serializable { // Every entity needs to implement Serializable, to make it available for JSON requests on the frontend
    /* Every entity id has this @Id, @GeneratedValue and @Column */
    @Id // This labels it as the ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This basically is used to generate a new id for a new entity
    @Column(name = "id") // This is the column name in the database. Again, it needs to be an exact match
    private Long id;

    // Then for every column inside the database table you basically create it here in the entity. For example here I have sample_name
    @Column(name = "sample_name")
    private String sampleName;

}
