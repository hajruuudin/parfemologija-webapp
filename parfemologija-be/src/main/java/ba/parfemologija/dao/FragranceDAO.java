package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.FragranceEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FragranceDAO extends JpaRepository<FragranceEntity, Long> {
    @Query("""
    SELECT f FROM FragranceEntity f
    WHERE
        (:search IS NULL OR :search = '' OR LOWER(f.officialName) LIKE LOWER(CONCAT('%', :search, '%')))
        AND (:brandIds IS NULL OR f.brandId IN :brandIds)
        AND (:typeId IS NULL OR f.typeId = :typeId)
        AND (:gender IS NULL OR f.gender = :gender)
    """)
    Page<FragranceEntity> findAllFiltered(
            Pageable pageable,
            @Param("search") String search,
            @Param("brandIds") List<Integer> brandIds,
            @Param("typeId") Integer typeId,
            @Param("gender") String gender
    );

}
