package ba.parfemologija.service.core.models.sample;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "Schema of a default Sample Model Create request with its properties")
public class SampleCreateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Name of the sample")
    private String name;
}