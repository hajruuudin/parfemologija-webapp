package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.AccordDAO;
import ba.parfemologija.dao.entities.AccordEntity;
import ba.parfemologija.service.core.AccordService;
import ba.parfemologija.service.core.models.accord.AccordCreate;
import ba.parfemologija.service.core.models.accord.AccordModel;
import ba.parfemologija.service.core.models.accord.AccordUpdate;
import ba.parfemologija.service.implementation.mapper.AccordMapper;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
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
public class AccordServiceImpl implements AccordService {
    AccordDAO accordDAO;
    AccordMapper accordMapper;

    @Override
    public ResponseEntity<Page<AccordModel>> find(PageRequest request) {
        try{
            Page<AccordEntity> pagedAccordResponse = accordDAO.findAll(request);

            List<AccordModel> accordModelList = accordMapper.entitiesToDtos(pagedAccordResponse.getContent());

            Page<AccordModel> accordResponse = new PageImpl<>(
                    accordModelList,
                    request,
                    pagedAccordResponse.getTotalElements()
            );

            return ResponseEntity.ok(accordResponse);
        } catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }
    }

    @Override
    public ResponseEntity<AccordModel> create(AccordCreate request) throws Exception {
        try{
            AccordEntity accordEntity = accordMapper.dtoToEntity(request);

            accordEntity.setCreatedAt(LocalDateTime.now());
            accordDAO.save(accordEntity);

            return ResponseEntity.ok(accordMapper.entityToDto(accordEntity));
        } catch (Exception e){
            throw new IllegalArgumentException("Accord creation error");
        }

    }

    @Override
    public ResponseEntity<AccordModel> update(AccordUpdate update) {
        try {
            Optional<AccordEntity> existingEntityOptional = accordDAO.findById(update.getId());
            if (!existingEntityOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            AccordEntity existingEntity = existingEntityOptional.get();

            accordMapper.update(update, existingEntity);

            AccordEntity updatedEntity = accordDAO.save(existingEntity);
            AccordModel updatedModel = accordMapper.entityToDto(updatedEntity);
            return ResponseEntity.ok(updatedModel);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteById(Long id) {
        try{
            accordDAO.deleteById(id);

            return ResponseEntity.ok(true);
        } catch (Exception e){
            return ResponseEntity.status(400).body(false);
        }
    }
}
