package ba.parfemologija.service.core.models.collection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "Properties of a collection item entity")
public class CollectionItemModel implements Serializable {
    @Schema(description = "ID of the collection item")
    private Long id;

    @Schema(description = "User id the collection associates to")
    private Long userId;

    @Schema(description = "Slog of the fragrance in the collection")
    private String fragranceSlug;

    @Schema(description = "Date when the item was added")
    private LocalDateTime createdAt;

    @Schema(description = "Username of the user")
    private String createdBy;
}
