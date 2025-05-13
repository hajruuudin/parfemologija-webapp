// src/main/java/ba/parfemologija/service/implementation/UserWishlistServiceImpl.java
package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.UserWishlistDAO;
import ba.parfemologija.dao.entities.UserWishlistEntity;
import ba.parfemologija.service.core.UserWishlistService;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistCreate;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistModel;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistUpdate;
import ba.parfemologija.service.implementation.mapper.UserWishlistMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserWishlistServiceImpl implements UserWishlistService {
    private final UserWishlistDAO dao;
    private final UserWishlistMapper mapper;

    @Override
    public ResponseEntity<Page<UserWishlistModel>> find(PageRequest request) {
        try {
            Page<UserWishlistEntity> page = dao.findAll(request);
            List<UserWishlistModel> dtos = mapper.entitiesToDtos(page.getContent());
            Page<UserWishlistModel> result = new PageImpl<>(dtos, request, page.getTotalElements());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<UserWishlistModel> create(UserWishlistCreate request) throws Exception {
        try {
            UserWishlistEntity entity = mapper.dtoToEntity(request);
            LocalDateTime now = LocalDateTime.now();
            entity.setCreatedAt(now);
            entity.setModifiedAt(now);
            dao.save(entity);
            return ResponseEntity.ok(mapper.entityToDto(entity));
        } catch (Exception e) {
            throw new IllegalArgumentException("UserWishlist creation error");
        }
    }

    @Override
    public ResponseEntity<UserWishlistModel> update(UserWishlistUpdate request) {
        try {
            Optional<UserWishlistEntity> existingOpt = dao.findById(request.getId());
            if (!existingOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            UserWishlistEntity existing = existingOpt.get();
            mapper.update(request, existing);
            existing.setModifiedAt(LocalDateTime.now());
            UserWishlistEntity saved = dao.save(existing);
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
