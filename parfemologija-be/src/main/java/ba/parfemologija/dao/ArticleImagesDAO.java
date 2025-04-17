package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.ArticleImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleImagesDAO extends JpaRepository<ArticleImagesEntity, Long> {
}
