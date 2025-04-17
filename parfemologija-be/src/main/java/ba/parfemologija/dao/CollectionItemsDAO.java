package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.CollectionItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionItemsDAO extends JpaRepository<CollectionItemsEntity, Long> {
}
