package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaDAO extends JpaRepository<MediaEntity, Long> {
}
