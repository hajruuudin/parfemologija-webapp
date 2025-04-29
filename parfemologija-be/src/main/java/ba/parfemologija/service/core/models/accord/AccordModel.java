package ba.parfemologija.service.core.models.accord;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "All properties model for an accord entity")
public class AccordModel implements Serializable {

    @Schema(description = "Unique identifier of the accord")
    private Long id;

    @Schema(description = "Slug identifier of the accord")
    private String slug;

    @Schema(description = "Name of the accord")
    private String accordName;

    @Schema(description = "Description of the accord")
    private String accordDescription;

    @Schema(description = "Date of creation of the accord")
    private LocalDateTime createdAt;

}
