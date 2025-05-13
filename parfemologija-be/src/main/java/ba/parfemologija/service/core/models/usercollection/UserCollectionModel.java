// src/main/java/ba/parfemologija/service/core/models/usercollection/UserCollectionModel.java
package ba.parfemologija.service.core.models.usercollection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "All properties model for a UserCollection entity")
public class UserCollectionModel implements Serializable {

    @Schema(description = "Unique identifier of the collection")
    private Long id;

    @Schema(description = "Name of the collection")
    private String collectionName;

    @Schema(description = "Timestamp when the collection was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the collection was last modified")
    private LocalDateTime modifiedAt;

    @Schema(description = "ID of the user who owns this collection")
    private Long userId;
}
