package ba.parfemologija.service.implementation;

import ba.parfemologija.config.JwtUtil;
import ba.parfemologija.dao.UserDAO;
import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.service.core.AuthService;
import ba.parfemologija.service.core.models.user.UserCreate;
import ba.parfemologija.service.core.models.user.UserLogin;
import ba.parfemologija.service.core.models.user.UserModel;
import ba.parfemologija.service.implementation.mapper.UserMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final String JWT_COOKIE_NAME = "par-fem";
    private static final int JWT_COOKIE_MAX_AGE_SECONDS = 60 * 60 * 24;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    PasswordEncoder passwordEncoder;
    JwtUtil jwtUtil;
    UserDAO userDAO;
    UserMapper userMapper;

    @Override
    public UserModel register(UserCreate userCreateRequest) throws Exception {
        UserEntity userEntity = userMapper.dtoToEntity(userCreateRequest);

        try{
            /* Hashing the password and setting admin status to false */
            String hashed_password = passwordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(hashed_password);
            userEntity.setIsAdmin(false);
            /* Hashing the password and setting admin status to false */

            userDAO.save(userEntity);
            return userMapper.entityToDto(userEntity);
        } catch (Exception e){
            throw new Exception("Problem while registering user to the database", e);

        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> login(UserLogin userLoginRequest, HttpServletResponse servletResponse) throws Exception {
        if(userLoginRequest == null){
            throw new IllegalArgumentException("Request cannot be null!");
        }
        UserEntity entity = userDAO.findByUsername(userLoginRequest.getUsername());

        String storedPassword = entity.getPassword();
        String providedPassword = userLoginRequest.getPassword();

        if (passwordEncoder.matches(providedPassword, storedPassword)) {
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userLoginRequest.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            setJwtCookie(servletResponse, jwt);
            return ResponseEntity.ok().body(Map.of(
                    "token", jwt,
                    "user", entity
            ));
        } else {
            throw new Exception("Error while making cookie");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> logout(HttpServletResponse response) {
        Cookie deleteCookie = new Cookie(JWT_COOKIE_NAME, null);
        deleteCookie.setPath("/");
        deleteCookie.setHttpOnly(true);
        deleteCookie.setMaxAge(0);

        String appEnv = System.getenv("APP_ENVIRONMENT");
        StringBuilder cookieHeader = new StringBuilder(deleteCookie.getName() + "=; Path=" + deleteCookie.getPath() + "; HttpOnly; Max-Age=0");

        if ("production".equals(appEnv)) {
            cookieHeader.append("; Secure");
            cookieHeader.append("; SameSite=None");
        }

        response.setHeader("Set-Cookie", cookieHeader.toString());

        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    @Override
    public ResponseEntity<Map<String, Object>> checkUsername(String username) {
        UserEntity registeredUser = userDAO.findByUsername(username);

        if(registeredUser == null){
            return ResponseEntity.ok(Map.of("status", false));
        } else {
            return ResponseEntity.ok(Map.of("status", true));
        }
    }

    private void setJwtCookie(HttpServletResponse response, String token) {
        Cookie jwtCookie = new Cookie(JWT_COOKIE_NAME, token);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge(JWT_COOKIE_MAX_AGE_SECONDS);
        jwtCookie.setPath("/");

        String appEnv = System.getenv("APP_ENVIRONMENT");
        System.out.println(appEnv);
        StringBuilder cookieHeader = new StringBuilder(jwtCookie.getName() + "=" + jwtCookie.getValue() + "; Path=" + jwtCookie.getPath() + "; HttpOnly");

        if ("production".equals(appEnv)) {
            cookieHeader.append("; Secure");
            cookieHeader.append("; SameSite=None");
        }

        response.setHeader("Set-Cookie", cookieHeader.toString());
    }

}
