package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.FragranceDAO;
import ba.parfemologija.dao.entities.FragranceEntity;
import ba.parfemologija.service.core.FragranceService;
import ba.parfemologija.service.core.models.fragrance.FragranceCreateRequest;
import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import ba.parfemologija.service.core.models.fragrance.FragranceUpdateRequest;
import ba.parfemologija.service.implementation.mapper.FragranceMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FragranceServiceImpl implements FragranceService {
    FragranceDAO fragranceDAO;
    FragranceMapper fragranceMapper;

    @Override
    public ResponseEntity<Page<FragranceModel>> find(PageRequest request) {
        try{
            Page<FragranceEntity> pagedFragranceResponse = fragranceDAO.findAll(request);

            List<FragranceModel> fragranceModelList = fragranceMapper.entitiesToDtos(pagedFragranceResponse.getContent());

            Page<FragranceModel> fragranceResponse = new PageImpl<>(
                    fragranceModelList,
                    request,
                    pagedFragranceResponse.getTotalElements()
            );

            return ResponseEntity.ok(fragranceResponse);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @Override
    public ResponseEntity<FragranceModel> create(FragranceCreateRequest request) throws Exception {
        try{
            FragranceEntity fragranceEntity = fragranceMapper.dtoToEntity(request);

            fragranceEntity.setCreatedAt(LocalDateTime.now());
            fragranceEntity.setCreatedBy("hajrudin.imamovic"); // Switch later with user request from frontend
            fragranceDAO.save(fragranceEntity);

            return ResponseEntity.ok(fragranceMapper.entityToDto(fragranceEntity));
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Fragrance creation error");
        }

    }

    @Override
    public ResponseEntity<FragranceModel> update(FragranceUpdateRequest update) {
        try {
            Optional<FragranceEntity> existingEntityOptional = fragranceDAO.findById(update.getId());
            if (!existingEntityOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            FragranceEntity existingEntity = existingEntityOptional.get();
            existingEntity.setModifiedAt(LocalDateTime.now());
            existingEntity.setModifiedBy("hajrudin.imamovic"); // Switch later with user request from frontend

            fragranceMapper.update(update, existingEntity);

            FragranceEntity updatedEntity = fragranceDAO.save(existingEntity);
            FragranceModel updatedModel = fragranceMapper.entityToDto(updatedEntity);
            return ResponseEntity.ok(updatedModel);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteById(Long id) {
        try{
            fragranceDAO.deleteById(id);

            return ResponseEntity.ok(true);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(false);
        }
    }
}
