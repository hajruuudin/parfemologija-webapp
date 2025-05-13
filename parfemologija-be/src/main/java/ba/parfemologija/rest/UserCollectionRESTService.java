// src/main/java/ba/parfemologija/rest/UserCollectionRESTService.java
package ba.parfemologija.rest;

import ba.parfemologija.service.core.UserCollectionService;
import ba.parfemologija.service.core.models.usercollection.UserCollectionCreate;
import ba.parfemologija.service.core.models.usercollection.UserCollectionModel;
import ba.parfemologija.service.core.models.usercollection.UserCollectionUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "UserCollection", description = "User Collection API")
@AllArgsConstructor
@RequestMapping("/usercollection")
public class UserCollectionRESTService {
    private final UserCollectionService service;

    @Operation(description = "Get user collections with pagination")
    @GetMapping
    public ResponseEntity<Page<UserCollectionModel>> findAll() {
        return service.find(PageRequest.of(0, 10));
    }

    @Operation(description = "Create a new user collection")
    @PostMapping
    public ResponseEntity<UserCollectionModel> create(@RequestBody UserCollectionCreate req) throws Exception {
        return service.create(req);
    }

    @Operation(description = "Update an existing user collection")
    @PutMapping
    public ResponseEntity<UserCollectionModel> update(@RequestBody UserCollectionUpdate req) {
        return service.update(req);
    }

    @Operation(description = "Delete a user collection by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
