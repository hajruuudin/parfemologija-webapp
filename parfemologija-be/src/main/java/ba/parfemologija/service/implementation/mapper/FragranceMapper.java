package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.FragranceEntity;
import ba.parfemologija.service.core.models.fragrance.FragranceCreateRequest;
import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import ba.parfemologija.service.core.models.fragrance.FragranceUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FragranceMapper {
    List<FragranceModel> entitiesToDtos(List<FragranceEntity> entities);
    FragranceModel entityToDto(FragranceEntity entity);
    FragranceEntity dtoToEntity(FragranceCreateRequest fragranceCreate);
    void update(FragranceUpdateRequest fragranceUpdate, @MappingTarget FragranceEntity fragranceEntity);
}
