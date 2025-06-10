package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.service.core.models.user.LoggedUserModel;
import ba.parfemologija.service.core.models.user.UserCreate;
import ba.parfemologija.service.core.models.user.UserModel;
import ba.parfemologija.service.core.models.user.UserUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // 1-to-1 mappings
    UserModel   entityToDto(UserEntity entity);
    UserEntity  dtoToEntity(UserCreate create);
    LoggedUserModel loggedEntityToDto(UserEntity entity);

    // 1) map a list of entities to DTOs
    List<UserModel> entitiesToDtos(List<UserEntity> entities);

    // 2) apply an update DTO onto an existing entity
    void update(UserUpdate update, @MappingTarget UserEntity entity);
}
