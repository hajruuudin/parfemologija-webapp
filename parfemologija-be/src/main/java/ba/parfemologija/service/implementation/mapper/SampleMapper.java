package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.SampleEntity;
import ba.parfemologija.service.core.models.sample.SampleCreateRequest;
import ba.parfemologija.service.core.models.sample.SampleModel;
import ba.parfemologija.service.core.models.sample.SampleUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SampleMapper {
    SampleModel entityToDto(SampleEntity entity); // Usually u use this one when getting something by id

    SampleEntity dtoToEntity(SampleCreateRequest model); // Usually u use this one when making a new entity

    List<SampleModel> entitiesToDtos(List<SampleEntity> entities); // Usually u use this one when getting all records

    void updateEntity(SampleUpdateRequest request, @MappingTarget SampleEntity sampleEntity); // This one when updating
}
