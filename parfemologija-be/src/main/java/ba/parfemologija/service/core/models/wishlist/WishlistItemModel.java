package ba.parfemologija.service.core.models.wishlist;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "Properties of a wishlist item entity")
public class WishlistItemModel implements Serializable {
    @Schema(description = "ID of the wishlist item")
    private Long id;

    @Schema(description = "User id the wishlist associates to")
    private Long userId;

    @Schema(description = "Slog of the fragrance in the wishlist")
    private String fragranceSlug;

    @Schema(description = "Date when the item was added")
    private LocalDateTime createdAt;

    @Schema(description = "Username of the user")
    private String createdBy;
}
