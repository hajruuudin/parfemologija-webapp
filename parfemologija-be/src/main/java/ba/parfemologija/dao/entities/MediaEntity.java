package ba.parfemologija.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// This is the table where the images are actually kept. For example:
// media_category - this should be values like ARTICLE or FRAGRANCE, since on this project only fragrances and articles have images
// object_id - the object this media is associated
// image_url - the actual media url
// Basically, it's only purpose is to not have images stored with every main table, but to have one dedicated table for media
// Images will be stored on some random ahh server online, well figure it out
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pf_media")
public class MediaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Other columns
}

