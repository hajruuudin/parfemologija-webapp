package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.user.UserCreate;
import ba.parfemologija.service.core.models.user.UserLogin;
import ba.parfemologija.service.core.models.user.UserModel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthService {
    UserModel register(UserCreate userCreateRequest) throws Exception;
    ResponseEntity<Map<String, Object>> login (UserLogin userLoginRequest, HttpServletResponse servletResponse) throws Exception;
    ResponseEntity<Map<String, Object>> logout(HttpServletResponse response);
    ResponseEntity<Map<String, Object>> checkUsername(String username);
}
