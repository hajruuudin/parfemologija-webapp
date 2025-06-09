package ba.parfemologija.rest;

import ba.parfemologija.service.core.FragranceService;
import ba.parfemologija.service.core.models.fragrance.FragranceCreateRequest;
import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import ba.parfemologija.service.core.models.fragrance.FragranceUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Fragrance", description = "Fragrance API")
@AllArgsConstructor
@RequestMapping(value = "fragrance")
public class FragranceRESTService {
    FragranceService fragranceService;

    @Operation(description = "Get paginated fragrances from the database with optional filtering (using search, fragrance type, fragrance gender and potential brands)")
    @GetMapping
    public ResponseEntity<Page<FragranceModel>> find(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "brands", required = false) List<Integer> brandIds,
            @RequestParam(value = "type", required = false) Integer typeId,
            @RequestParam(value = "gender", required = false) String gender
    )
    {
        return fragranceService.find(PageRequest.of(pageNumber, pageSize), search, brandIds, typeId, gender);
    }

    @Operation(description = "Get all the details of a fragrance by the fragrance slug")
    @GetMapping(value = "{slug}")
    public ResponseEntity<FragranceModel> findBySlug(@PathVariable String slug){
        return fragranceService.findBySlug(slug);
    }

    @Operation(description = "Create a new fragrance entity")
    @PostMapping
    public ResponseEntity<FragranceModel> create(@RequestBody FragranceCreateRequest request) throws Exception {
        return fragranceService.create(request);
    }

    @Operation(description = "Update an existing fragrance entity")
    @PutMapping
    public ResponseEntity<FragranceModel> update(@RequestBody FragranceUpdateRequest request) throws  Exception {
        return fragranceService.update(request);
    }

    @Operation(description = "Delete a fragrance record from the database")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return  fragranceService.deleteById(id);
    }
}
