package ba.parfemologija.service.core.models.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Schema(description = "All properties model of a review entity")
public class ReviewModel {
    @Schema(description = "ID of the review")
    private Long id;

    @Schema(description = "Headline of the review")
    private String head;

    @Schema(description = "Rating of the review")
    private Integer rating;

    @Schema(description = "Body of the review")
    private String body;

    @Schema(description = "Date of creation of the review")
    private LocalDate createdAt;

    @Schema(description = "Person who created the review")
    private String createdBy;

    @Schema(description = "Fragrance ID the review is associated with")
    private Long fragranceId;
}
