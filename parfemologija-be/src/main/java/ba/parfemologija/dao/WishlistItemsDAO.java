package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.WishlistItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistItemsDAO extends JpaRepository<WishlistItemsEntity, Long> {
    WishlistItemsEntity findByFragranceSlugAndUserId(String fragranceSlug, Long userId);
    List<WishlistItemsEntity> findByUserId(Long userId);
}
