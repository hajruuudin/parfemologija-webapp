package ba.parfemologija.rest;

import ba.parfemologija.service.core.ReviewService;
import ba.parfemologija.service.core.models.review.ReviewCreateModel;
import ba.parfemologija.service.core.models.review.ReviewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Tag(name = "Review", description = "Review API")
@AllArgsConstructor
@RequestMapping("review")
public class ReviewRESTService {
    ReviewService reviewService;

    @Operation(description = "Create a new fragrance review")
    @PostMapping
    public ResponseEntity<ReviewModel> create(@RequestBody ReviewCreateModel request, Principal principal){
        return reviewService.create(principal, request);
    }

    @Operation(description = "Get all review from the database for the specific fragrance sorted by most recently added")
    @GetMapping
    public  ResponseEntity<List<ReviewModel>> findAll(@RequestParam(name = "fragranceId", required = true) Long fragranceId){
        return reviewService.findAll(fragranceId);
    }

    @Operation(description = "Delete a review based on its id")
    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam(name = "reviewId", required = true) Long reviewId, Principal principal){
        return reviewService.delete(reviewId, principal);
    }

    @Operation(description = "Get N recent reviews from the database")
    @GetMapping(value = "recent")
    public ResponseEntity<List<ReviewModel>> getRecent(@RequestParam(name = "n", required = true) Integer number){
        return reviewService.getRecent(number);
    }
}
