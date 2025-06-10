package ba.parfemologija.service.core.models.media;

import ba.parfemologija.utils.ObjectType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties for creating a media object")
public class MediaCreateModel implements Serializable {
    @Schema(description = "Category od the object")
    private ObjectType mediaCategory;

    @Schema(description = "ID of the associated object")
    private Long objectId;

    @Schema(description = "Image url of the object")
    private String imageUrl;
}
