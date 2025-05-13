package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.FragranceEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FragranceDAO extends JpaRepository<FragranceEntity, Long> {
    Page<FragranceEntity> findAll(@NotNull Pageable request);
}
