package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.WishlistItemsEntity;
import ba.parfemologija.service.core.models.wishlist.WishlistItemModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
    WishlistItemModel entityToDto(WishlistItemsEntity entity);
}
