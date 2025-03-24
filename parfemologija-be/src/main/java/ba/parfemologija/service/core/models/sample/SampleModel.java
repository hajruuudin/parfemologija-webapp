package ba.parfemologija.service.core.models.sample;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Schema(description = "Schema of a default Sample Model with its properties")
public class SampleModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the Sample")
    private Long id;

    @Schema(description = "Name of the sample")
    private String name;

}
