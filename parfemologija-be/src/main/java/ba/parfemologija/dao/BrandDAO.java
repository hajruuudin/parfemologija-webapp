package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDAO extends JpaRepository<BrandEntity, Long> {
}
