// 6) Mapper
// src/main/java/ba/parfemologija/service/implementation/mapper/NoteMapper.java
package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.NoteEntity;
import ba.parfemologija.service.core.models.note.NoteCreate;
import ba.parfemologija.service.core.models.note.NoteModel;
import ba.parfemologija.service.core.models.note.NoteUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    List<NoteModel>   entitiesToDtos(List<NoteEntity> entities);
    NoteModel         entityToDto(NoteEntity entity);
    NoteEntity        dtoToEntity(NoteCreate create);
    void              update(NoteUpdate update, @MappingTarget NoteEntity entity);
}
