package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.FragranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FragranceDAO extends JpaRepository<FragranceEntity, Long> {
}
