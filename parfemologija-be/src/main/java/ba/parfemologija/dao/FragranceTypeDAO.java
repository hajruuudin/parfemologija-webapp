package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.FragranceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FragranceTypeDAO extends JpaRepository<FragranceTypeEntity, Long> {
}
