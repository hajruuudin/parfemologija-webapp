package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.UserCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCollectionDAO extends JpaRepository<UserCollectionEntity, Long> {
}
