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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
            throw new IllegalArgumentException();
        }

    }

    @Override
    public ResponseEntity<AccordModel> update(AccordUpdate update) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> deleteById(Long id) {
        return null;
    }
}
