package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.ArticleEntity;
import ba.parfemologija.service.core.models.article.ArticleCreateRequest;
import ba.parfemologija.service.core.models.article.ArticleModel;
import ba.parfemologija.service.core.models.article.ArticleUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    List<ArticleModel> entitiesToDtos(List<ArticleEntity> entities);
    ArticleModel entityToDto(ArticleEntity entity);
    ArticleEntity dtoToEntity(ArticleCreateRequest articleCreate);
    void update(ArticleUpdateRequest articleUpdate, @MappingTarget ArticleEntity articleEntity);
}
