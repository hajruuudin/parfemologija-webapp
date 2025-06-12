package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.FragranceReviewDAO;
import ba.parfemologija.dao.UserDAO;
import ba.parfemologija.dao.entities.FragranceReviewEntity;
import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.service.core.ReviewService;
import ba.parfemologija.service.core.models.review.ReviewCreateModel;
import ba.parfemologija.service.core.models.review.ReviewModel;
import ba.parfemologija.service.implementation.mapper.ReviewMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    FragranceReviewDAO reviewDAO;
    UserDAO userDAO;
    ReviewMapper reviewMapper;

    @Override
    public ResponseEntity<ReviewModel> create(Principal principal, ReviewCreateModel request) {
        try{
            UserEntity userEntity = userDAO.findByUsername(principal.getName());
            FragranceReviewEntity entity = new FragranceReviewEntity();

            entity.setHead(request.getHead());
            entity.setBody(request.getBody());
            entity.setRating(request.getRating());
            entity.setCreatedBy(userEntity.getUsername());
            entity.setCreatedAt(LocalDateTime.now());
            entity.setFragranceId(request.getFragranceId());

            reviewDAO.save(entity);

            return ResponseEntity.ok(reviewMapper.entityToDto(entity));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Review Add Error");
        }
    }

    @Override
    public ResponseEntity<List<ReviewModel>> findAll(Long fragranceId) {
        try{
            List<FragranceReviewEntity> fragranceReviewEntities = reviewDAO.findAllByFragranceIdOrderByCreatedAtDesc(fragranceId);

            List<ReviewModel> reviewModels = reviewMapper.entitiesToDtos(fragranceReviewEntities);

            return ResponseEntity.ok(reviewModels);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fragrance Get All ERROR");
        }
    }

    @Override
    public ResponseEntity<Boolean> delete(Long reviewId, Principal principal) {
        try{
            UserEntity entity = userDAO.findByUsername(principal.getName());
            Optional<FragranceReviewEntity> reviewEntity = reviewDAO.findById(reviewId);

            if(reviewEntity.isPresent()){
                if(entity.getUsername().equals(reviewEntity.get().getCreatedBy())){
                    reviewDAO.delete(reviewEntity.get());

                    return ResponseEntity.ok(true);
                } else {
                    throw new IllegalAccessException("You cannot delete this review!");
                }
            } else {
                throw new NoSuchObjectException("Review does not exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Review Delete Error");
        }
    }

    @Override
    public ResponseEntity<List<ReviewModel>> getRecent(Integer number) {
        try {
            PageRequest pageRequest = PageRequest.of(0, number);
            List<FragranceReviewEntity> entities = reviewDAO.findRecent(pageRequest);

            List<ReviewModel> models = reviewMapper.entitiesToDtos(entities);

            return ResponseEntity.ok(models);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Recent Review ERROR");
        }
    }
}
