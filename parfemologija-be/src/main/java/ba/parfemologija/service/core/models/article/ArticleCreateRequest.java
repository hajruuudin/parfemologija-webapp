package ba.parfemologija.service.core.models.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "Properties for creating a new Article entity")
public class ArticleCreateRequest implements Serializable {

    @Schema(description = "Title of the article", nullable = false)
    private String articleTitle;

    @Schema(description = "Description of the article")
    private String articleDescription;

    @Schema(description = "Identifier of the fragrance associated with the article")
    private Integer articleFragranceId;

    @Schema(description = "Type of the article")
    private String articleType;

    @Schema(description = "Price of the article")
    private BigDecimal articlePrice;
}
