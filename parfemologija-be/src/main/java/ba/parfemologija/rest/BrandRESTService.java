// src/main/java/ba/parfemologija/rest/BrandRESTService.java
package ba.parfemologija.rest;

import ba.parfemologija.service.core.BrandService;
import ba.parfemologija.service.core.models.brand.BrandCreate;
import ba.parfemologija.service.core.models.brand.BrandModel;
import ba.parfemologija.service.core.models.brand.BrandUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Brand", description = "Brand API")
@AllArgsConstructor
@RequestMapping("/brand")
public class BrandRESTService {

    private final BrandService brandService;

    @Operation(description = "Get brands from database with pagination")
    @GetMapping
    public ResponseEntity<Page<BrandModel>> findAll() {
        return brandService.find(PageRequest.of(0, 10));
    }

    @Operation(description = "Get a brand from the database by its id")
    @GetMapping(value = "{id}")
    public ResponseEntity<BrandModel> findById(@PathVariable Long id){
        return brandService.findById(id);
    }

    @Operation(description = "Create a new brand")
    @PostMapping
    public ResponseEntity<BrandModel> create(@RequestBody BrandCreate request) throws Exception {
        return brandService.create(request);
    }

    @Operation(description = "Update an existing brand")
    @PutMapping
    public ResponseEntity<BrandModel> update(@RequestBody BrandUpdate request) {
        return brandService.update(request);
    }

    @Operation(description = "Delete a brand by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return brandService.deleteById(id);
    }
}
