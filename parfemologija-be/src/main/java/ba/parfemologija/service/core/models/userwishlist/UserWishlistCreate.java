// src/main/java/ba/parfemologija/service/core/models/userwishlist/UserWishlistCreate.java
package ba.parfemologija.service.core.models.userwishlist;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties for a UserWishlist create request")
public class UserWishlistCreate implements Serializable {

    @Schema(description = "Name of the wishlist")
    private String listName;

    @Schema(description = "ID of the user who owns this wishlist")
    private Long userId;
}
