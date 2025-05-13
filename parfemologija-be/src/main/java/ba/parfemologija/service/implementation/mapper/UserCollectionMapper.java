// src/main/java/ba/parfemologija/service/implementation/mapper/UserCollectionMapper.java
package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.UserCollectionEntity;
import ba.parfemologija.service.core.models.usercollection.UserCollectionCreate;
import ba.parfemologija.service.core.models.usercollection.UserCollectionModel;
import ba.parfemologija.service.core.models.usercollection.UserCollectionUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserCollectionMapper {
    List<UserCollectionModel> entitiesToDtos(List<UserCollectionEntity> entities);
    UserCollectionModel       entityToDto(UserCollectionEntity entity);
    UserCollectionEntity      dtoToEntity(UserCollectionCreate create);
    void                      update(UserCollectionUpdate update, @MappingTarget UserCollectionEntity entity);
}
