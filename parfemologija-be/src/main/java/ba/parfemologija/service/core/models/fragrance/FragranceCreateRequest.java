package ba.parfemologija.service.core.models.fragrance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Properties for creating  a Fragrance entity")
public class FragranceCreateRequest {

    @Schema(description = "Slug identifier of the fragrance")
    private String slug;

    @Schema(description = "Official name of the fragrance")
    private String officialName;

    @Schema(description = "Identifier of the brand")
    private Integer brandId;

    @Schema(description = "Identifier of the fragrance type")
    private Integer typeId;

    @Schema(description = "Rating for winter suitability (1-10)")
    private Short winterRating;

    @Schema(description = "Rating for fall suitability (1-10)")
    private Short fallRating;

    @Schema(description = "Rating for summer suitability (1-10)")
    private Short summerRating;

    @Schema(description = "Rating for spring suitability (1-10)")
    private Short springRating;

    @Schema(description = "Rating for sillage (projection) (1-10)")
    private Short sillageRating;

    @Schema(description = "Rating for longevity (1-10)")
    private Short longevityRating;

    @Schema(description = "General price range of the fragrance")
    private String priceRange;

    @Schema(description = "Gender category of the fragrance")
    private String gender;
}
