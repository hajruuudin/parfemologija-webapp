package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.ArticleEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDAO extends JpaRepository<ArticleEntity, Long> {
    @Query("""
    SELECT ae FROM ArticleEntity ae
    WHERE (:search = '' OR LOWER(ae.articleTitle) LIKE LOWER(CONCAT('%', :search, '%')))
    \s""")
    Page<ArticleEntity> findAll(@NotNull Pageable request, String search);
}
