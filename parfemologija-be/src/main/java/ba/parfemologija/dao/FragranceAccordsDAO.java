package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.FragranceAccordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FragranceAccordsDAO extends JpaRepository<FragranceAccordsEntity, Long> {
}
