package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.UserDAO;
import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.service.core.models.user.*;
import ba.parfemologija.service.implementation.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFind_success() {
        PageRequest request = PageRequest.of(0, 10);
        List<UserEntity> entities = List.of(new UserEntity());
        List<UserModel> dtos = List.of(new UserModel());

        when(userDAO.findAll(request)).thenReturn(new PageImpl<>(entities));
        when(userMapper.entitiesToDtos(entities)).thenReturn(dtos);

        var response = userService.find(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dtos, response.getBody().getContent());
    }

    @Test
    void testFind_exception() {
        when(userDAO.findAll((Example<UserEntity>) any())).thenThrow(new RuntimeException("error"));

        var response = userService.find(PageRequest.of(0, 1));

        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testFindUserProfile_success() {
        Principal principal = () -> "user";
        UserEntity entity = new UserEntity();
        LoggedUserModel dto = new LoggedUserModel();

        when(userDAO.findByUsername("user")).thenReturn(entity);
        when(userMapper.loggedEntityToDto(entity)).thenReturn(dto);

        var response = userService.findUserProfile(principal);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testFindById_exists() {
        UserEntity entity = new UserEntity();
        UserModel dto = new UserModel();

        when(userDAO.findById(1L)).thenReturn(Optional.of(entity));
        when(userMapper.entityToDto(entity)).thenReturn(dto);

        var response = userService.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testFindById_notExists() {
        when(userDAO.findById(999L)).thenReturn(Optional.empty());

        var response = userService.findById(999L);

        assertNull(response);
    }

    @Test
    void testCreate_success() throws Exception {
        UserCreate create = new UserCreate();
        UserEntity entity = new UserEntity();
        UserModel dto = new UserModel();

        when(userMapper.dtoToEntity(create)).thenReturn(entity);
        when(userDAO.save(entity)).thenReturn(entity);
        when(userMapper.entityToDto(entity)).thenReturn(dto);

        var response = userService.create(create);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testCreate_exception() {
        UserCreate create = new UserCreate();
        when(userMapper.dtoToEntity(create)).thenThrow(new RuntimeException());

        assertThrows(IllegalArgumentException.class, () -> userService.create(create));
    }

    @Test
    void testUpdate_exists() {
        UserUpdate update = new UserUpdate();
        update.setId(1L);

        UserEntity entity = new UserEntity();
        UserModel dto = new UserModel();

        when(userDAO.findById(1L)).thenReturn(Optional.of(entity));
        when(userDAO.save(entity)).thenReturn(entity);
        when(userMapper.entityToDto(entity)).thenReturn(dto);

        var response = userService.update(update);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testUpdate_notFound() {
        UserUpdate update = new UserUpdate();
        update.setId(123L);

        when(userDAO.findById(update.getId())).thenReturn(Optional.empty());

        var response = userService.update(update);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testUpdate_exception() {
        UserUpdate update = new UserUpdate();
        update.setId(1L);

        when(userDAO.findById(1L)).thenReturn(Optional.of(new UserEntity()));
        when(userDAO.save(any())).thenThrow(new RuntimeException());

        var response = userService.update(update);

        assertEquals(500, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteById_success() {
        doNothing().when(userDAO).deleteById(1L);

        var response = userService.deleteById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody());
    }

    @Test
    void testDeleteById_exception() {
        doThrow(new RuntimeException()).when(userDAO).deleteById(1L);

        var response = userService.deleteById(1L);

        assertEquals(400, response.getStatusCodeValue());
    }
}
