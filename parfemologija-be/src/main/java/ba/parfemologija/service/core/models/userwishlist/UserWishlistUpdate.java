// src/main/java/ba/parfemologija/service/core/models/userwishlist/UserWishlistUpdate.java
package ba.parfemologija.service.core.models.userwishlist;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties for a UserWishlist update request")
public class UserWishlistUpdate implements Serializable {

    @NotNull
    @Schema(description = "Identifier of the wishlist to update")
    private Long id;

    @Schema(description = "New name for the wishlist")
    private String listName;

    @Schema(description = "ID of the user who owns this wishlist")
    private Long userId;
}
