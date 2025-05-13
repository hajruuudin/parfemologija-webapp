// 3b) NoteModel DTO
// src/main/java/ba/parfemologija/service/core/models/note/NoteModel.java
package ba.parfemologija.service.core.models.note;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "All properties model for a Note entity")
public class NoteModel implements Serializable {

    @Schema(description = "Unique identifier of the note")
    private Long id;

    @Schema(description = "Slug identifier of the note")
    private String slug;

    @Schema(description = "Name of the note")
    private String name;

    @Schema(description = "Description text of the note")
    private String description;
}
