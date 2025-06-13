// src/main/java/ba/parfemologija/service/implementation/UserServiceImpl.java
package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.UserDAO;
import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.service.core.UserService;
import ba.parfemologija.service.core.models.user.LoggedUserModel;
import ba.parfemologija.service.core.models.user.UserCreate;
import ba.parfemologija.service.core.models.user.UserModel;
import ba.parfemologija.service.core.models.user.UserUpdate;
import ba.parfemologija.service.implementation.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<Page<UserModel>> find(PageRequest request) {
        try {
            Page<UserEntity> page = userDAO.findAll(request);
            List<UserModel> dtos = userMapper.entitiesToDtos(page.getContent());
            Page<UserModel> result = new PageImpl<>(dtos, request, page.getTotalElements());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<LoggedUserModel> findUserProfile(Principal principal) {
        try{
            UserEntity userEntity = userDAO.findByUsername(principal.getName());

            LoggedUserModel userModel = userMapper.loggedEntityToDto(userEntity);

            return ResponseEntity.ok(userModel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("User Profile Retrieve ERROR");
        }
    }

    @Override
    public ResponseEntity<UserModel> findById(Long id) {
        try{
            Optional<UserEntity> userEntity = userDAO.findById(id);

            if(userEntity.isPresent()){
                UserModel userModel = userMapper.entityToDto(userEntity.get());

                return ResponseEntity.ok(userModel);
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("User Find ERROR");
        }
    }

    @Override
    public ResponseEntity<UserModel> create(UserCreate request) throws Exception {
        try {
            UserEntity entity = userMapper.dtoToEntity(request);
            entity.setJoinedAt(LocalDateTime.now());
            userDAO.save(entity);
            return ResponseEntity.ok(userMapper.entityToDto(entity));
        } catch (Exception e) {
            throw new IllegalArgumentException("User creation error");
        }
    }

    @Override
    public ResponseEntity<UserModel> update(UserUpdate request) {
        try {
            Optional<UserEntity> existingOpt = userDAO.findById(request.getId());
            if (!existingOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            UserEntity existing = existingOpt.get();
            userMapper.update(request, existing);
            UserEntity saved = userDAO.save(existing);
            return ResponseEntity.ok(userMapper.entityToDto(saved));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteById(Long id) {
        try {
            userDAO.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
