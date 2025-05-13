// 3a) NoteCreate DTO
// src/main/java/ba/parfemologija/service/core/models/note/NoteCreate.java
package ba.parfemologija.service.core.models.note;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties for a Note create request")
public class NoteCreate implements Serializable {

    @Schema(description = "Slug identifier of the note")
    private String slug;

    @Schema(description = "Name of the note")
    private String name;

    @Schema(description = "Description text of the note")
    private String description;
}
