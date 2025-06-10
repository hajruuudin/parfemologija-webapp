package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.review.ReviewCreateModel;
import ba.parfemologija.service.core.models.review.ReviewModel;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface ReviewService {
    ResponseEntity<ReviewModel> create(Principal principal, ReviewCreateModel request);
    ResponseEntity<List<ReviewModel>> findAll(Long fragranceId);
    ResponseEntity<Boolean> delete(Long reviewId, Principal principal);
}
