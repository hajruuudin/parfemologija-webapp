package ba.parfemologija.service.core.models.fragrance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(description = "Fragrance model for all properties of a fragrance entity")
public class FragranceModel implements Serializable {

    @Schema(description = "Unique identifier of the fragrance")
    private Long id;

    @Schema(description = "Slug identifier of the fragrance")
    private String slug;

    @Schema(description = "Official name of the fragrance")
    private String officialName;

    @Schema(description = "Identifier of the brand")
    private Integer brandId;

    @Schema(description = "Identifier of the fragrance type")
    private Integer typeId;

    @Schema(description = "Rating for winter suitability (1-10)")
    private Integer winterRating;

    @Schema(description = "Rating for fall suitability (1-10)")
    private Integer fallRating;

    @Schema(description = "Rating for summer suitability (1-10)")
    private Integer summerRating;

    @Schema(description = "Rating for spring suitability (1-10)")
    private Integer springRating;

    @Schema(description = "Rating for sillage (projection) (1-10)")
    private Integer sillageRating;

    @Schema(description = "Rating for longevity (1-10)")
    private Integer longevityRating;

    @Schema(description = "General price range of the fragrance")
    private String priceRange;

    @Schema(description = "Identifier of the user who created the fragrance entry")
    private String createdBy;

    @Schema(description = "Timestamp of when the fragrance entry was created")
    private LocalDateTime createdAt;

    @Schema(description = "Identifier of the user who last modified the fragrance entry")
    private String modifiedBy;

    @Schema(description = "Timestamp of when the fragrance entry was last modified")
    private LocalDateTime modifiedAt;

    @Schema(description = "Gender category of the fragrance")
    private String gender;

    @Schema(description = "Thumbnail Image URL of the fragrance")
    private String thumbnailImageUrl;
}
