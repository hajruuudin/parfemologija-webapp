package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.SampleDAO;
import ba.parfemologija.dao.entities.SampleEntity;
import ba.parfemologija.service.core.SampleService;
import ba.parfemologija.service.core.models.sample.SampleCreateRequest;
import ba.parfemologija.service.core.models.sample.SampleModel;
import ba.parfemologija.service.core.models.sample.SampleUpdateRequest;
import ba.parfemologija.service.implementation.mapper.SampleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/*
*   Every entity has its own service. This is the part where u basically call the dao, get the data,
*   validate if u need to do things and so on. Also, u need to put the @Service annotation always, as well
*   as the @AllArgsConstructor, since we need spring to actually make the dao and mapper instances
*
*   The mapper is used to convert an entity to a model and vice versa.
*/
@Service
@AllArgsConstructor
public class SampleServiceImplementation implements SampleService {
    SampleDAO sampleDAO;
    SampleMapper sampleMapper;

    @Override
    public SampleModel findById(Long id) {
        Optional<SampleEntity> entity = sampleDAO.findById(id); // Try to find the entity by id
        if(entity.isPresent()){ // If the entity is contained within the optional...
            return sampleMapper.entityToDto(entity.get()); // return the mapped version
        } else {
            return null;
        }
    }

    @Override
    public List<SampleModel> getAll() {
        List<SampleEntity> entities = sampleDAO.findAll();

        return sampleMapper.entitiesToDtos(entities);
    }

    @Transactional // all delete, update and add methods should be transactional
    @Override
    public SampleModel save(SampleCreateRequest request) {
        SampleEntity sampleEntity = sampleMapper.dtoToEntity(request);

        sampleDAO.save(sampleEntity);

        return sampleMapper.entityToDto(sampleEntity);
    }

    @Transactional
    @Override
    public SampleModel update(SampleUpdateRequest request) {
        Optional<SampleEntity> entity = sampleDAO.findById(request.getId());

        if(entity.isPresent()){
            sampleMapper.updateEntity(request, entity.get());

            sampleDAO.save(entity.get());
            return sampleMapper.entityToDto(entity.get());
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        sampleDAO.deleteById(id);
    }
}
