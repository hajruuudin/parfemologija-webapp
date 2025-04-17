package ba.parfemologija.service.core.models.sample;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/*
 *   MODELS: like entities, each entity has its own model
 *   Think of models like the frontend request part. Basically, when you make a request from the frontend, ur going
 *   to send data from a form. That data should be mapped to some Java object. Sometimes, the data you send might
 *   not contain all the columns (like the date o whatever). Because of this, we create these models. Models
 *   are mapped to Entities before they are inserted into the database. Same goes for getting entities from the database.
 *   When you get an entity from the DAO, in the service layer you create a new Model and map all the properties
 *   from the entities to the model.
 *   We can do it without it, but it can make stupid errors later on and this way it looks more professional.
 *
 *   So tl:dr - for every entity you create Models of potential requests for that entity. FOr example, for this sample
 *   you would have this all properties model which you use when getting samples from the database. For a create
 *   sample request, you wouldn't have the ID column because that is added automatically.
 *
 *  Also, when making the models, the names of the variables need to be exactly the same as in the entities,
 *  SO for example, if I have sampleName in my entity, and have sample_name here, it will always be null
 *
 */
@Data
@NoArgsConstructor
@Schema(description = "Schema of a default Sample Model with its properties")
public class SampleModel implements Serializable {

    @Schema(description = "Unique identifier of the Sample")
    private Long id;

    @Schema(description = "Name of the sample")
    private String sampleName;

}
