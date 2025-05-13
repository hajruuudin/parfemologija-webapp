// BrandModel.java
package ba.parfemologija.service.core.models.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Schema(description = "All properties model for a Brand entity")
public class BrandModel implements Serializable {

    @Schema(description = "Unique identifier of the brand")
    private Long id;

    @Schema(description = "Slug identifier of the brand")
    private String slug;

    @Schema(description = "Name of the brand")
    private String brandName;

    @Schema(description = "Description of the brand")
    private String brandDescription;

    @Schema(description = "Date when the brand was created")
    private LocalDate brandCreationDate;
}
