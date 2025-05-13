// BrandUpdate.java
package ba.parfemologija.service.core.models.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties for a Brand update request")
public class BrandUpdate implements Serializable {

    @NotNull
    @Schema(description = "Unique identifier of the brand")
    private Long id;

    @Schema(description = "Slug identifier of the brand")
    private String slug;

    @Schema(description = "Name of the brand")
    private String brandName;

    @Schema(description = "Description of the brand")
    private String brandDescription;
}
