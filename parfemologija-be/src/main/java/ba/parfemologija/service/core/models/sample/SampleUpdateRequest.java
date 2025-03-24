package ba.parfemologija.service.core.models.sample;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "Schema of a default Sample Model Update request with its properties")
public class SampleUpdateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Schema(description = "Unique identifier of the Sample")
    private Long id;

    @Schema(description = "Name of the sample")
    private String name;
}
