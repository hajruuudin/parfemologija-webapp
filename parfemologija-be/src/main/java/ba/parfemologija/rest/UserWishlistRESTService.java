// src/main/java/ba/parfemologija/rest/UserWishlistRESTService.java
package ba.parfemologija.rest;

import ba.parfemologija.service.core.UserWishlistService;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistCreate;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistModel;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "UserWishlist", description = "User Wishlist API")
@AllArgsConstructor
@RequestMapping("/userwishlist")
public class UserWishlistRESTService {
    private final UserWishlistService service;

    @Operation(description = "Get user wishlists with pagination")
    @GetMapping
    public ResponseEntity<Page<UserWishlistModel>> findAll() {
        return service.find(PageRequest.of(0, 10));
    }

    @Operation(description = "Create a new user wishlist")
    @PostMapping
    public ResponseEntity<UserWishlistModel> create(@RequestBody UserWishlistCreate req) throws Exception {
        return service.create(req);
    }

    @Operation(description = "Update an existing user wishlist")
    @PutMapping
    public ResponseEntity<UserWishlistModel> update(@RequestBody UserWishlistUpdate req) {
        return service.update(req);
    }

    @Operation(description = "Delete a user wishlist by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
