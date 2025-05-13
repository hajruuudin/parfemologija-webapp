// src/main/java/ba/parfemologija/service/core/models/userwishlist/UserWishlistModel.java
package ba.parfemologija.service.core.models.userwishlist;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "All properties model for a UserWishlist entity")
public class UserWishlistModel implements Serializable {

    @Schema(description = "Unique identifier of the wishlist")
    private Long id;

    @Schema(description = "Name of the wishlist")
    private String listName;

    @Schema(description = "Timestamp when the wishlist was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the wishlist was last modified")
    private LocalDateTime modifiedAt;

    @Schema(description = "ID of the user who owns this wishlist")
    private Long userId;
}
