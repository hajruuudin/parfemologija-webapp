package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.article.ArticleCreateRequest;
import ba.parfemologija.service.core.models.article.ArticleModel;
import ba.parfemologija.service.core.models.article.ArticleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface ArticleService {
    ResponseEntity<Page<ArticleModel>> find(PageRequest request, String search);
    ResponseEntity<ArticleModel> create(ArticleCreateRequest request, Principal principal) throws Exception;
    ResponseEntity<ArticleModel> findById(Long id);
    ResponseEntity<List<ArticleModel>> findByUser(Principal principal);
    ResponseEntity<ArticleModel> update(ArticleUpdateRequest update);
    ResponseEntity<Boolean> deleteById(Long id);
}
