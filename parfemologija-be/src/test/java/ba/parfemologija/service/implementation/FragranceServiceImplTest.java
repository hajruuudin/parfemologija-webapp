package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.FragranceDAO;
import ba.parfemologija.dao.UserDAO;
import ba.parfemologija.dao.entities.FragranceEntity;
import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.service.core.LookupImageService;
import ba.parfemologija.service.core.models.fragrance.*;
import ba.parfemologija.service.implementation.mapper.FragranceMapper;
import ba.parfemologija.utils.ObjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FragranceServiceImplTest {

    @Mock
    private FragranceDAO fragranceDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private FragranceMapper fragranceMapper;

    @Mock
    private LookupImageService lookupImageService;

    @InjectMocks
    private FragranceServiceImpl fragranceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFind() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        String search = "dior";
        List<Integer> brandIds = List.of(1);
        Integer typeId = 2;
        String gender = "male";

        List<FragranceEntity> fragranceEntities = List.of(new FragranceEntity());
        List<FragranceModel> fragranceModels = List.of(new FragranceModel());

        when(fragranceDAO.findAllFiltered(pageRequest, search, brandIds, typeId, gender))
                .thenReturn(new PageImpl<>(fragranceEntities));
        when(fragranceMapper.entitiesToDtos(fragranceEntities)).thenReturn(fragranceModels);

        var response = fragranceService.find(pageRequest, search, brandIds, typeId, gender);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getContent().size());
        verify(lookupImageService, times(1)).lookupThumbnailImage(any(), any(), any());
    }

    @Test
    void testFindBySlug() {
        String slug = "cool-water";
        FragranceEntity entity = new FragranceEntity();
        FragranceModel model = new FragranceModel();

        when(fragranceDAO.findBySlug(slug)).thenReturn(entity);
        when(fragranceMapper.entityToDto(entity)).thenReturn(model);

        var response = fragranceService.findBySlug(slug);

        assertEquals(200, response.getStatusCodeValue());
        verify(lookupImageService).lookupThumbnailImage(model, ObjectType.FRAGRANCE, model.getId());
    }

    @Test
    void testCreate() throws Exception {
        FragranceCreateRequest request = new FragranceCreateRequest();
        request.setOfficialName("My Perfume");
        request.setBrandId(1);
        request.setTypeId(2);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("hajrudin");

        Principal principal = () -> "hajrudin";

        FragranceEntity entity = new FragranceEntity();
        FragranceModel model = new FragranceModel();

        when(userDAO.findByUsername("hajrudin")).thenReturn(userEntity);
        when(fragranceMapper.dtoToEntity(request)).thenReturn(entity);
        when(fragranceDAO.save(any())).thenReturn(entity);
        when(fragranceMapper.entityToDto(entity)).thenReturn(model);

        var response = fragranceService.create(request, principal);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdate_existingEntity() {
        FragranceUpdateRequest update = new FragranceUpdateRequest();
        update.setId(1L);

        FragranceEntity entity = new FragranceEntity();
        FragranceModel model = new FragranceModel();

        when(fragranceDAO.findById(1L)).thenReturn(Optional.of(entity));
        when(fragranceDAO.save(entity)).thenReturn(entity);
        when(fragranceMapper.entityToDto(entity)).thenReturn(model);

        var response = fragranceService.update(update);

        assertEquals(200, response.getStatusCodeValue());
        verify(fragranceMapper).update(update, entity);
    }

    @Test
    void testDeleteById_success() {
        doNothing().when(fragranceDAO).deleteById(1L);

        var response = fragranceService.deleteById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody());
    }

    @Test
    void testUpdate_notFound() {
        FragranceUpdateRequest update = new FragranceUpdateRequest();
        update.setId(999L);

        when(fragranceDAO.findById(999L)).thenReturn(Optional.empty());

        var response = fragranceService.update(update);

        assertEquals(404, response.getStatusCodeValue());
    }
}
