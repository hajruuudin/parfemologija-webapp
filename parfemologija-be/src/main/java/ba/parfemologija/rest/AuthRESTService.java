package ba.parfemologija.rest;

import ba.parfemologija.service.core.AuthService;
import ba.parfemologija.service.core.models.user.UserCreate;
import ba.parfemologija.service.core.models.user.UserLogin;
import ba.parfemologija.service.core.models.user.UserModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Tag(name = "Authentication", description = "Authentication API")
@AllArgsConstructor
@RequestMapping(value = "/auth")
public class AuthRESTService {
    AuthService authService;

    @Operation(description = "Register a user to the platform")
    @PostMapping(value = "/register")
    public UserModel register(@RequestBody UserCreate userCreateRequest) throws Exception {
        return authService.register(userCreateRequest);
    }

    @Operation(description = "Login a user to the platform")
    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserLogin userLoginRequest, HttpServletResponse servletResponse) throws Exception {
        return authService.login(userLoginRequest, servletResponse);
    }

    @Operation(description = "Check if a username is already taken")
    @GetMapping(value = "/check/{username}")
    public ResponseEntity<Map<String, Object>> checkUsername(@PathVariable String username){
        System.out.println("Checking for name:" + username);
        return authService.checkUsername(username);
    }

    @Operation(description = "Log a user out of his session")
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpServletResponse response) {
        return authService.logout(response);
    }
}
