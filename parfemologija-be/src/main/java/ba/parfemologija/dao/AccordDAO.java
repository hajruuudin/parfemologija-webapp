package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.AccordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccordDAO extends JpaRepository<AccordEntity, Long> {
}
