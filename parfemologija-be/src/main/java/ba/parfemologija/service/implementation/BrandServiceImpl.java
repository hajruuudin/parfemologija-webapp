// src/main/java/ba/parfemologija/service/implementation/BrandServiceImpl.java
package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.BrandDAO;
import ba.parfemologija.dao.entities.BrandEntity;
import ba.parfemologija.service.core.BrandService;
import ba.parfemologija.service.core.models.brand.BrandCreate;
import ba.parfemologija.service.core.models.brand.BrandModel;
import ba.parfemologija.service.core.models.brand.BrandUpdate;
import ba.parfemologija.service.implementation.mapper.BrandMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandDAO brandDAO;
    private final BrandMapper brandMapper;

    @Override
    public ResponseEntity<Page<BrandModel>> find(PageRequest request) {
        try {
            Page<BrandEntity> page = brandDAO.findAll(request);
            List<BrandModel> dtos = brandMapper.entitiesToDtos(page.getContent());
            Page<BrandModel> result = new PageImpl<>(dtos, request, page.getTotalElements());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<BrandModel> create(BrandCreate request) throws Exception {
        try {
            BrandEntity entity = brandMapper.dtoToEntity(request);
            entity.setBrandCreationDate(LocalDate.now());
            brandDAO.save(entity);
            return ResponseEntity.ok(brandMapper.entityToDto(entity));
        } catch (Exception e) {
            throw new IllegalArgumentException("Brand creation error");
        }
    }

    @Override
    public ResponseEntity<BrandModel> update(BrandUpdate request) {
        try {
            Optional<BrandEntity> existingOpt = brandDAO.findById(request.getId());
            if (!existingOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            BrandEntity existing = existingOpt.get();
            brandMapper.update(request, existing);
            BrandEntity saved = brandDAO.save(existing);
            return ResponseEntity.ok(brandMapper.entityToDto(saved));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteById(Long id) {
        try {
            brandDAO.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
