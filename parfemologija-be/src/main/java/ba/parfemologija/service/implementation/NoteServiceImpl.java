// 5) Service implementation
// src/main/java/ba/parfemologija/service/implementation/NoteServiceImpl.java
package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.NoteDAO;
import ba.parfemologija.dao.entities.NoteEntity;
import ba.parfemologija.service.core.NoteService;
import ba.parfemologija.service.core.models.note.NoteCreate;
import ba.parfemologija.service.core.models.note.NoteModel;
import ba.parfemologija.service.core.models.note.NoteUpdate;
import ba.parfemologija.service.implementation.mapper.NoteMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteDAO noteDAO;
    private final NoteMapper noteMapper;

    @Override
    public ResponseEntity<Page<NoteModel>> find(PageRequest request) {
        try {
            Page<NoteEntity> page = noteDAO.findAll(request);
            List<NoteModel> dtos = noteMapper.entitiesToDtos(page.getContent());
            Page<NoteModel> result = new PageImpl<>(dtos, request, page.getTotalElements());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<NoteModel> create(NoteCreate request) throws Exception {
        try {
            NoteEntity entity = noteMapper.dtoToEntity(request);
            noteDAO.save(entity);
            return ResponseEntity.ok(noteMapper.entityToDto(entity));
        } catch (Exception e) {
            throw new IllegalArgumentException("Note creation error");
        }
    }

    @Override
    public ResponseEntity<NoteModel> update(NoteUpdate request) {
        try {
            Optional<NoteEntity> existingOpt = noteDAO.findById(request.getId());
            if (!existingOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            NoteEntity existing = existingOpt.get();
            noteMapper.update(request, existing);
            NoteEntity saved = noteDAO.save(existing);
            return ResponseEntity.ok(noteMapper.entityToDto(saved));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteById(Long id) {
        try {
            noteDAO.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
