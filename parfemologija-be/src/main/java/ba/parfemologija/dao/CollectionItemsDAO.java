package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.CollectionItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionItemsDAO extends JpaRepository<CollectionItemsEntity, Long> {
    CollectionItemsEntity findByFragranceSlugAndUserId(String fragranceSlug, Long userId);
    List<CollectionItemsEntity> findByUserId(Long userId);
}
