package ba.parfemologija.service.core.models.accord;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Properties for an accord model create request")
public class AccordCreate {

    @Schema(description = "Slug identifier of the accord")
    private String slug;

    @Schema(description = "Name of the accord")
    private String accordName;

    @Schema(description = "Description of the accord")
    private String accordDescription;
}
