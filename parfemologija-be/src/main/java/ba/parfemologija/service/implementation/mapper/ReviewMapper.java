package ba.parfemologija.service.implementation.mapper;

import ba.parfemologija.dao.entities.FragranceReviewEntity;
import ba.parfemologija.service.core.models.review.ReviewModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewModel entityToDto(FragranceReviewEntity entity);
    List<ReviewModel> entitiesToDtos(List<FragranceReviewEntity> entities);
}
