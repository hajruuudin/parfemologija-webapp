// src/main/java/ba/parfemologija/service/implementation/UserCollectionServiceImpl.java
package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.UserCollectionDAO;
import ba.parfemologija.dao.entities.UserCollectionEntity;
import ba.parfemologija.service.core.UserCollectionService;
import ba.parfemologija.service.core.models.usercollection.UserCollectionCreate;
import ba.parfemologija.service.core.models.usercollection.UserCollectionModel;
import ba.parfemologija.service.core.models.usercollection.UserCollectionUpdate;
import ba.parfemologija.service.implementation.mapper.UserCollectionMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserCollectionServiceImpl implements UserCollectionService {
    private final UserCollectionDAO dao;
    private final UserCollectionMapper mapper;

    @Override
    public ResponseEntity<Page<UserCollectionModel>> find(PageRequest request) {
        try {
            Page<UserCollectionEntity> page = dao.findAll(request);
            List<UserCollectionModel> dtos = mapper.entitiesToDtos(page.getContent());
            Page<UserCollectionModel> result = new PageImpl<>(dtos, request, page.getTotalElements());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<UserCollectionModel> create(UserCollectionCreate request) throws Exception {
        try {
            UserCollectionEntity entity = mapper.dtoToEntity(request);
            LocalDateTime now = LocalDateTime.now();
            entity.setCreatedAt(now);
            entity.setModifiedAt(now);
            dao.save(entity);
            return ResponseEntity.ok(mapper.entityToDto(entity));
        } catch (Exception e) {
            throw new IllegalArgumentException("UserCollection creation error");
        }
    }

    @Override
    public ResponseEntity<UserCollectionModel> update(UserCollectionUpdate request) {
        try {
            Optional<UserCollectionEntity> existingOpt = dao.findById(request.getId());
            if (!existingOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            UserCollectionEntity existing = existingOpt.get();
            mapper.update(request, existing);
            existing.setModifiedAt(LocalDateTime.now());
            UserCollectionEntity saved = dao.save(existing);
            return ResponseEntity.ok(mapper.entityToDto(saved));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteById(Long id) {
        try {
            dao.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
