package ba.parfemologija.service.core.models;

import ba.parfemologija.service.core.models.article.ArticleCreateRequest;
import ba.parfemologija.service.core.models.article.ArticleModel;
import ba.parfemologija.service.core.models.article.ArticleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface ArticleService {
    ResponseEntity<Page<ArticleModel>> find(PageRequest request);
    ResponseEntity<ArticleModel> create(ArticleCreateRequest request) throws Exception;
    ResponseEntity<ArticleModel> update(ArticleUpdateRequest update);
    ResponseEntity<Boolean> deleteById(Long id);
}
