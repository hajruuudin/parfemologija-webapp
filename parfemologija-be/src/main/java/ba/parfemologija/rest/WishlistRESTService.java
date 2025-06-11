package ba.parfemologija.rest;

import ba.parfemologija.service.core.WishlistService;
import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import ba.parfemologija.service.core.models.wishlist.WishlistItemModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Tag(name = "Wishlist", description = "Wishlist API")
@AllArgsConstructor
@RequestMapping(value = "wishlist")
public class WishlistRESTService {
    WishlistService wishlistService;

    @Operation(description = "Add a fragrance to the users wishlist")
    @PostMapping
    public ResponseEntity<WishlistItemModel> add(@RequestBody String fragranceSlug, Principal principal) throws Exception {
        return wishlistService.add(fragranceSlug, principal);
    }

    @Operation(description = "Remove a fragrance from the users wishlist")
    @DeleteMapping(value = "{fragranceSlug}")
    public ResponseEntity<Boolean> delete(@PathVariable String fragranceSlug, Principal principal) throws Exception {
        return wishlistService.delete(fragranceSlug, principal);
    }

    @Operation(description = "Get the users fragrance wishlist sorted by recently added")
    @GetMapping
    public ResponseEntity<List<FragranceModel>> getAll(Principal principal) throws Exception {
        return wishlistService.getAll(principal);
    }

    @Operation(description = "Get the status of a wishlist item from the database")
    @GetMapping(value = "{fragranceSlug}")
    public ResponseEntity<Boolean> getBySlug(@PathVariable String fragranceSlug, Principal principal) throws Exception {
        return wishlistService.checkStatus(fragranceSlug, principal);
    }
}
