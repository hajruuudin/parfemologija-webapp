// 7) REST Controller
// src/main/java/ba/parfemologija/rest/NoteRESTService.java
package ba.parfemologija.rest;

import ba.parfemologija.service.core.NoteService;
import ba.parfemologija.service.core.models.note.NoteCreate;
import ba.parfemologija.service.core.models.note.NoteModel;
import ba.parfemologija.service.core.models.note.NoteUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Note", description = "Note API")
@AllArgsConstructor
@RequestMapping("/note")
public class NoteRESTService {
    private final NoteService noteService;

    @Operation(description = "Get notes from database with pagination")
    @GetMapping
    public ResponseEntity<Page<NoteModel>> findAll() {
        return noteService.find(PageRequest.of(0, 10));
    }

    @Operation(description = "Create a new note")
    @PostMapping
    public ResponseEntity<NoteModel> create(@RequestBody NoteCreate request) throws Exception {
        return noteService.create(request);
    }

    @Operation(description = "Update an existing note")
    @PutMapping
    public ResponseEntity<NoteModel> update(@RequestBody NoteUpdate request) {
        return noteService.update(request);
    }

    @Operation(description = "Delete a note by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return noteService.deleteById(id);
    }
}
