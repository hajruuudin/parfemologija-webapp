// src/main/java/ba/parfemologija/service/core/UserService.java
package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.user.LoggedUserModel;
import ba.parfemologija.service.core.models.user.UserCreate;
import ba.parfemologija.service.core.models.user.UserModel;
import ba.parfemologija.service.core.models.user.UserUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {
    ResponseEntity<Page<UserModel>> find(PageRequest request);
    ResponseEntity<LoggedUserModel> findUserProfile(Principal principal);
    ResponseEntity<UserModel> create(UserCreate request) throws Exception;
    ResponseEntity<UserModel> update(UserUpdate request);
    ResponseEntity<Boolean> deleteById(Long id);
}
