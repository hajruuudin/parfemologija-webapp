package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.service.core.models.user.UserCreate;
import ba.parfemologija.service.core.models.user.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Maps a {@link UserEntity} to a corresponding {@link UserModel} to be retrieved from the database.
     *
     * @return The user selected from the database.
     */
    UserModel entityToDto(UserEntity entity);

    /**
     * Maps a {@link UserCreate} to a corresponding {@link UserEntity} to be stored within the database.
     * ID, isAdmin and joinedAt are all added within the service. The rest of the fields should be
     * provided via a form.
     *
     * @return The newly generated user from the database.
     */
    UserEntity dtoToEntity(UserCreate model);
}
