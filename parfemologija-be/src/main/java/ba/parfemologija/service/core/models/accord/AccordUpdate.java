package ba.parfemologija.service.core.models.accord;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "Properties of an accord entity update request")
public class AccordUpdate implements Serializable {
    @NotNull
    @Schema(description = "Unique identifier of the accord")
    private Long id;

    @Schema(description = "Slug identifier of the accord")
    private String slug;

    @Schema(description = "Name of the accord")
    private String accordName;

    @Schema(description = "Description of the accord")
    private String descriptionName;
}
