package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.AccordEntity;
import ba.parfemologija.service.core.models.accord.AccordCreate;
import ba.parfemologija.service.core.models.accord.AccordModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccordMapper {
    List<AccordModel> entitiesToDtos(List<AccordEntity> entities);
    AccordModel entityToDto(AccordEntity entity);
    AccordEntity dtoToEntity(AccordCreate accordCreate);

}
