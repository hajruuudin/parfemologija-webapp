// src/main/java/ba/parfemologija/service/implementation/mapper/UserWishlistMapper.java
package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.UserWishlistEntity;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistCreate;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistModel;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserWishlistMapper {
    List<UserWishlistModel>   entitiesToDtos(List<UserWishlistEntity> entities);
    UserWishlistModel         entityToDto(UserWishlistEntity entity);
    UserWishlistEntity        dtoToEntity(UserWishlistCreate create);
    void                      update(UserWishlistUpdate update, @MappingTarget UserWishlistEntity entity);
}

