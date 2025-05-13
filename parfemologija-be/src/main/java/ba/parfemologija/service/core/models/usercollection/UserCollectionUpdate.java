// src/main/java/ba/parfemologija/service/core/models/usercollection/UserCollectionUpdate.java
package ba.parfemologija.service.core.models.usercollection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties for a UserCollection update request")
public class UserCollectionUpdate implements Serializable {

    @NotNull
    @Schema(description = "Identifier of the collection to update")
    private Long id;

    @Schema(description = "New name for the collection")
    private String collectionName;

    @Schema(description = "ID of the user who owns this collection")
    private Long userId;
}
