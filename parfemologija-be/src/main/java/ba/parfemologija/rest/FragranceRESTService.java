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

@RestController
@Tag(name = "Fragrance", description = "Fragrance API")
@AllArgsConstructor
@RequestMapping(value = "fragrance")
public class FragranceRESTService {
    FragranceService fragranceService;

    @Operation(description = "Get paginated fragrances from the database")
    @GetMapping(value = "{pageNumber}/{pageSize}")
    public ResponseEntity<Page<FragranceModel>> find(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize){
        return fragranceService.find(PageRequest.of(pageNumber, pageSize));
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
