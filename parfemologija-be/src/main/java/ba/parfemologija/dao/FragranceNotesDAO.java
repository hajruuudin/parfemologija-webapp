package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.FragranceNotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FragranceNotesDAO extends JpaRepository<FragranceNotesEntity, Long> {
}
