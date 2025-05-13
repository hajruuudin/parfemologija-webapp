// 4) Service interface
// src/main/java/ba/parfemologija/service/core/NoteService.java
package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.note.NoteCreate;
import ba.parfemologija.service.core.models.note.NoteModel;
import ba.parfemologija.service.core.models.note.NoteUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface NoteService {
    ResponseEntity<Page<NoteModel>> find(PageRequest request);
    ResponseEntity<NoteModel> create(NoteCreate request) throws Exception;
    ResponseEntity<NoteModel> update(NoteUpdate request);
    ResponseEntity<Boolean> deleteById(Long id);
}
