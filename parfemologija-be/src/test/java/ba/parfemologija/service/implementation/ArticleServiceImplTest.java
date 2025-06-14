package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.ArticleDAO;
import ba.parfemologija.dao.UserDAO;
import ba.parfemologija.dao.entities.ArticleEntity;
import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.service.core.LookupImageService;
import ba.parfemologija.service.core.models.article.*;
import ba.parfemologija.service.implementation.mapper.ArticleMapper;
import ba.parfemologija.utils.ObjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.security.Principal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {

    @Mock
    private ArticleDAO articleDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private ArticleMapper articleMapper;

    @Mock
    private LookupImageService lookupImageService;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFind() {
        PageRequest request = PageRequest.of(0, 10);
        String search = "some";

        List<ArticleEntity> entities = List.of(new ArticleEntity());
        List<ArticleModel> models = List.of(new ArticleModel());

        when(articleDAO.findAll(request, search)).thenReturn(new PageImpl<>(entities));
        when(articleMapper.entitiesToDtos(entities)).thenReturn(models);

        var response = articleService.find(request, search);

        assertEquals(200, response.getStatusCodeValue());
        verify(lookupImageService).lookupThumbnailImage(any(), eq(ObjectType.ARTICLE), any());
    }

    @Test
    void testCreate() throws Exception {
        ArticleCreateRequest request = new ArticleCreateRequest();
        ArticleEntity entity = new ArticleEntity();
        ArticleModel model = new ArticleModel();
        Principal principal = () -> "user";

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("user");
        user.setLocation("Sarajevo");

        when(articleMapper.dtoToEntity(request)).thenReturn(entity);
        when(userDAO.findByUsername("user")).thenReturn(user);
        when(articleDAO.save(entity)).thenReturn(entity);
        when(articleMapper.entityToDto(entity)).thenReturn(model);

        var response = articleService.create(request, principal);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(model, response.getBody());
    }

    @Test
    void testFindById_exists() {
        ArticleEntity entity = new ArticleEntity();
        entity.setId(1L);
        ArticleModel model = new ArticleModel();
        model.setId(1L);

        when(articleDAO.findById(1L)).thenReturn(Optional.of(entity));
        when(articleMapper.entityToDto(entity)).thenReturn(model);

        var response = articleService.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(lookupImageService).lookupThumbnailImage(model, ObjectType.ARTICLE, 1L);
    }

    @Test
    void testFindById_notExists() {
        when(articleDAO.findById(999L)).thenReturn(Optional.empty());

        var response = articleService.findById(999L);

        assertNull(response);
    }

    @Test
    void testFindByUser() {
        Principal principal = () -> "user";
        UserEntity user = new UserEntity();
        user.setId(1L);

        List<ArticleEntity> entities = List.of(new ArticleEntity());
        List<ArticleModel> models = List.of(new ArticleModel());

        when(userDAO.findByUsername("user")).thenReturn(user);
        when(articleDAO.findByUserId(1L)).thenReturn(entities);
        when(articleMapper.entitiesToDtos(entities)).thenReturn(models);

        var response = articleService.findByUser(principal);

        assertEquals(200, response.getStatusCodeValue());
        verify(lookupImageService).lookupThumbnailImage(any(), eq(ObjectType.ARTICLE), any());
    }

    @Test
    void testUpdate_exists() {
        ArticleUpdateRequest update = new ArticleUpdateRequest();
        update.setId(1L);

        ArticleEntity entity = new ArticleEntity();
        ArticleModel model = new ArticleModel();

        when(articleDAO.findById(1L)).thenReturn(Optional.of(entity));
        when(articleDAO.save(entity)).thenReturn(entity);
        when(articleMapper.entityToDto(entity)).thenReturn(model);

        var response = articleService.update(update);

        assertEquals(200, response.getStatusCodeValue());
    }
}
