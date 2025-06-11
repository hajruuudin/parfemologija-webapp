package ba.parfemologija.rest;

import ba.parfemologija.service.core.CollectionService;
import ba.parfemologija.service.core.models.collection.CollectionItemModel;
import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Tag(name = "Collection", description = "Collection API")
@AllArgsConstructor
@RequestMapping(value = "collection")
public class CollectionRESTService {
    CollectionService collectionService;

    @Operation(description = "Add a fragrance to the users collection")
    @PostMapping
    public ResponseEntity<CollectionItemModel> add(@RequestBody String fragranceSlug, Principal principal) throws Exception {
        return collectionService.add(fragranceSlug, principal);
    }

    @Operation(description = "Remove a fragrance from the users collection")
    @DeleteMapping(value = "{fragranceSlug}")
    public ResponseEntity<Boolean> delete(@PathVariable String fragranceSlug, Principal principal) throws Exception {
        return collectionService.delete(fragranceSlug, principal);
    }

    @Operation(description = "Get the users fragrance collection sorted by recently added")
    @GetMapping
    public ResponseEntity<List<FragranceModel>> getAll(Principal principal) throws Exception {
        return collectionService.getAll(principal);
    }

    @Operation(description = "Get the status of a collection item (true or false)")
    @GetMapping(value = "{fragranceSlug}")
    public ResponseEntity<Boolean> checkStatus(@PathVariable String fragranceSlug, Principal principal) throws Exception {
        return collectionService.checkStatus(fragranceSlug, principal);
    }

}
