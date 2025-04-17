package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.FragranceReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FragranceReviewDAO extends JpaRepository<FragranceReviewEntity, Long> {
}
