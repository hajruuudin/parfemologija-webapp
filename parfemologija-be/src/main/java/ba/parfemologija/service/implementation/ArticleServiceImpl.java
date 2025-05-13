package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.ArticleDAO;
import ba.parfemologija.dao.entities.ArticleEntity;
import ba.parfemologija.service.core.LookupImageService;
import ba.parfemologija.service.core.models.ArticleService;
import ba.parfemologija.service.core.models.article.ArticleCreateRequest;
import ba.parfemologija.service.core.models.article.ArticleModel;
import ba.parfemologija.service.core.models.article.ArticleUpdateRequest;
import ba.parfemologija.service.implementation.mapper.ArticleMapper;
import ba.parfemologija.utils.ObjectType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    ArticleDAO articleDAO;
    ArticleMapper articleMapper;
    LookupImageService lookupImageService;

    @Override
    public ResponseEntity<Page<ArticleModel>> find(PageRequest request) {
        try {
            Page<ArticleEntity> pagedArticleResponse = articleDAO.findAll(request);

            List<ArticleModel> articleModelList = articleMapper.entitiesToDtos(pagedArticleResponse.getContent());

            for(ArticleModel articleModel : articleModelList){
                lookupImageService.lookupThumbnailImage(articleModel, ObjectType.ARTICLE, articleModel.getId());
            }

            Page<ArticleModel> articleResponse = new PageImpl<>(
                    articleModelList,
                    request,
                    pagedArticleResponse.getTotalElements()
            );

            return ResponseEntity.ok(articleResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @Override
    public ResponseEntity<ArticleModel> create(ArticleCreateRequest request) throws Exception {
        try {
            ArticleEntity articleEntity = articleMapper.dtoToEntity(request);

            articleEntity.setCreatedAt(LocalDateTime.now());
            articleEntity.setUserId(5); //senad senad za sad
            articleEntity.setCreatedBy("user-from-frontend-session"); // Switch later with user request from frontend
            articleDAO.save(articleEntity);

            return ResponseEntity.ok(articleMapper.entityToDto(articleEntity));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Article creation error");
        }
    }

    @Override
    public ResponseEntity<ArticleModel> update(ArticleUpdateRequest update) {
        try {
            Optional<ArticleEntity> existingEntityOptional = articleDAO.findById(update.getId());
            if (!existingEntityOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            ArticleEntity existingEntity = existingEntityOptional.get();
            existingEntity.setModifiedAt(LocalDateTime.now());
            existingEntity.setModifiedBy("user-from-frontend"); // Switch later with user request from frontend

            articleMapper.update(update, existingEntity);

            ArticleEntity updatedEntity = articleDAO.save(existingEntity);
            ArticleModel updatedModel = articleMapper.entityToDto(updatedEntity);
            return ResponseEntity.ok(updatedModel);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteById(Long id) {
        try {
            articleDAO.deleteById(id);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(false);
        }
    }
}
