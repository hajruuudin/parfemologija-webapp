// BrandCreate.java
package ba.parfemologija.service.core.models.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties for a Brand create request")
public class BrandCreate implements Serializable {

    @Schema(description = "Slug identifier of the brand")
    private String slug;

    @Schema(description = "Name of the brand")
    private String brandName;

    @Schema(description = "Description of the brand")
    private String brandDescription;
}
