package ba.parfemologija.rest;

import ba.parfemologija.service.core.ArticleService;
import ba.parfemologija.service.core.models.article.ArticleCreateRequest;
import ba.parfemologija.service.core.models.article.ArticleModel;
import ba.parfemologija.service.core.models.article.ArticleUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Tag(name = "Article", description = "Article API")
@AllArgsConstructor
@RequestMapping(value = "article")
public class ArticleRESTService {
    ArticleService articleService;

    @Operation(description = "Get paginated articles from the database")
    @GetMapping()
    public ResponseEntity<Page<ArticleModel>> find(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", required = true) Integer pageNumber,
            @RequestParam(name = "size", required = true) Integer pageSize){
        return articleService.find(PageRequest.of(pageNumber, pageSize), search);
    }

    @Operation(description = "Find an article based on its ID")
    @GetMapping(value = "{id}")
    public ResponseEntity<ArticleModel> findById(@PathVariable Long id){
        return articleService.findById(id);
    }

    @Operation(description = "Find a users articles")
    @GetMapping(value = "user")
    public ResponseEntity<List<ArticleModel>> findByUser(Principal principal){
        return articleService.findByUser(principal);
    }

    @Operation(description = "Create a new article entity")
    @PostMapping
    public ResponseEntity<ArticleModel> create(@RequestBody ArticleCreateRequest request, Principal principal) throws Exception {
        return articleService.create(request, principal);
    }

    @Operation(description = "Update an existing article entity")
    @PutMapping
    public ResponseEntity<ArticleModel> update(@RequestBody ArticleUpdateRequest request) throws  Exception {
        return articleService.update(request);
    }

    @Operation(description = "Delete a article record from the database")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return  articleService.deleteById(id);
    }
}
