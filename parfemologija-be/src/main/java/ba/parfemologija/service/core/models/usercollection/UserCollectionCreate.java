// src/main/java/ba/parfemologija/service/core/models/usercollection/UserCollectionCreate.java
package ba.parfemologija.service.core.models.usercollection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties for a UserCollection create request")
public class UserCollectionCreate implements Serializable {

    @Schema(description = "Name of the collection")
    private String collectionName;

    @Schema(description = "ID of the user who owns this collection")
    private Long userId;
}
