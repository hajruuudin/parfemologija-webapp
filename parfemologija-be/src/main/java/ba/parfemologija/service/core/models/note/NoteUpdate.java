// 3c) NoteUpdate DTO
// src/main/java/ba/parfemologija/service/core/models/note/NoteUpdate.java
package ba.parfemologija.service.core.models.note;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties for a Note update request")
public class NoteUpdate implements Serializable {

    @NotNull
    @Schema(description = "Unique identifier of the note to update")
    private Long id;

    @Schema(description = "Slug identifier of the note")
    private String slug;

    @Schema(description = "Name of the note")
    private String name;

    @Schema(description = "Description text of the note")
    private String description;
}
