package ba.parfemologija.rest;

import ba.parfemologija.service.core.UserService;
import ba.parfemologija.service.core.models.user.UserCreate;
import ba.parfemologija.service.core.models.user.UserModel;
import ba.parfemologija.service.core.models.user.UserUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User", description = "User API")
@AllArgsConstructor
@RequestMapping("/user")
public class UserRESTService {
    
    private final UserService userService;

    @Operation(description = "Get users from database with pagination")
    @GetMapping
    public ResponseEntity<Page<UserModel>> findAll() {
        // fixed page 0, size 10 like Accord example
        return userService.find(PageRequest.of(0, 10));
    }

    @Operation(description = "Register a new user")
    @PostMapping
    public ResponseEntity<UserModel> create(@RequestBody UserCreate request) throws Exception {
        return userService.create(request);
    }

    @Operation(description = "Update an existing user")
    @PutMapping
    public ResponseEntity<UserModel> update(@RequestBody UserUpdate request) {
        return userService.update(request);
    }

    @Operation(description = "Delete a user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }
}
