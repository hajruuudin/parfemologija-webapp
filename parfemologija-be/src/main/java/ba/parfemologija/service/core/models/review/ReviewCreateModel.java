package ba.parfemologija.service.core.models.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Properties for creating a review in the system")
public class ReviewCreateModel {
    @Schema(description = "Headline of the review")
    private String head;

    @Schema(description = "Rating of the review")
    private Short rating;

    @Schema(description = "Body of the review")
    private String body;

    @Schema(description = "Fragrance Id the review is associated with")
    private Long fragranceId;
}
