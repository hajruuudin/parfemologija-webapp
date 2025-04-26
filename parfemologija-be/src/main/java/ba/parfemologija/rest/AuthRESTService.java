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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
