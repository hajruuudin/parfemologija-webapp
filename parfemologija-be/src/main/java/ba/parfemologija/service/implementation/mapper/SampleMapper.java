package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.model.SampleEntity;
import ba.parfemologija.service.core.models.sample.SampleModel;
import jakarta.persistence.MappedSuperclass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Mapper(componentModel = "spring")
public interface SampleMapper {

    Logger LOGGER = LoggerFactory.getLogger(SampleMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    default SampleModel entityToDto(SampleEntity entity) {
        SampleModel model = new SampleModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        LOGGER.info("Mapped SampleModel: {}", model); // Add this line
        return model;
    }
}
