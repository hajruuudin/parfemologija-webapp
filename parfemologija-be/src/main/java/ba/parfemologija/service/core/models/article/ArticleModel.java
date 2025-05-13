package ba.parfemologija.service.core.models.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "All properties of an Article model")
public class ArticleModel implements Serializable {

    @Schema(description = "Unique identifier of the article")
    private Long id;

    @Schema(description = "User identifier associated with the article", nullable = false)
    private Integer userId;

    @Schema(description = "Title of the article", nullable = false)
    private String articleTitle;

    @Schema(description = "Description of the article")
    private String articleDescription;

    @Schema(description = "Timestamp of when the article was created")
    private LocalDateTime createdAt;

    @Schema(description = "Identifier of the user who created the article")
    private String createdBy;

    @Schema(description = "Timestamp of when the article was last modified")
    private LocalDateTime modifiedAt;

    @Schema(description = "Identifier of the user who last modified the article")
    private String modifiedBy;

    @Schema(description = "Identifier of the fragrance associated with the article")
    private Integer articleFragranceId;

    @Schema(description = "Type of the article")
    private String articleType;

    @Schema(description = "Price of the article")
    private BigDecimal articlePrice;
}
