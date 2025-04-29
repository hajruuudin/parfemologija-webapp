package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.AccordEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccordDAO extends JpaRepository<AccordEntity, Long> {
    Page<AccordEntity> findAll(@NotNull Pageable request);
}
