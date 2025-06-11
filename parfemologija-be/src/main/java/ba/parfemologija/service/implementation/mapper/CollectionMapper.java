package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.CollectionItemsEntity;
import ba.parfemologija.service.core.models.collection.CollectionItemModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionMapper {
    CollectionItemModel entityToDto(CollectionItemsEntity entity);
}
