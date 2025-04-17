package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.FragranceImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FragranceImagesDAO extends JpaRepository<FragranceImagesEntity, Long> {
}
