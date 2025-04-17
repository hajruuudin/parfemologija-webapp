package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDAO extends JpaRepository<ArticleEntity, Long> {
}
