package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.SampleDAO;
import ba.parfemologija.dao.model.SampleEntity;
import ba.parfemologija.service.core.SampleService;
import ba.parfemologija.service.core.models.sample.SampleModel;
import ba.parfemologija.service.implementation.mapper.SampleMapper;
import ba.parfemologija.service.implementation.validation.SampleValidator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackFor = Exception.class)
public class SampleServiceImplementation implements SampleService {
    @Autowired SampleDAO sampleDAO;
    @Autowired SampleMapper sampleMapper;
    @Autowired SampleValidator sampleValidator;

    @Override
    public SampleModel findById(Long id) {
        sampleValidator.validateSampleExists(id);
        System.out.println(sampleDAO);
        SampleEntity entity = sampleDAO.findById(id);
        System.out.println(entity.getName());
        SampleModel mapped = sampleMapper.entityToDto(entity);
        System.out.println(mapped);
        return mapped;
    }
}
