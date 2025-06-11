package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.CollectionItemsDAO;
import ba.parfemologija.dao.FragranceDAO;
import ba.parfemologija.dao.UserDAO;
import ba.parfemologija.dao.entities.CollectionItemsEntity;
import ba.parfemologija.dao.entities.FragranceEntity;
import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.dao.entities.WishlistItemsEntity;
import ba.parfemologija.service.core.CollectionService;
import ba.parfemologija.service.core.LookupImageService;
import ba.parfemologija.service.core.models.collection.CollectionItemModel;
import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import ba.parfemologija.service.implementation.mapper.CollectionMapper;
import ba.parfemologija.service.implementation.mapper.FragranceMapper;
import ba.parfemologija.utils.ObjectType;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CollectionServiceImpl implements CollectionService {
    CollectionItemsDAO collectionItemsDAO;
    UserDAO userDAO;
    FragranceDAO fragranceDAO;
    FragranceMapper fragranceMapper;
    LookupImageService lookupImageService;
    CollectionMapper collectionMapper;

    @Override
    public ResponseEntity<CollectionItemModel> add(String fragranceSlug, Principal principal) throws Exception {
        try{
            UserEntity userEntity = userDAO.findByUsername(principal.getName());

            CollectionItemsEntity entity = new CollectionItemsEntity();

            entity.setUserId(userEntity.getId());
            entity.setFragranceSlug(fragranceSlug);
            entity.setCreatedAt(LocalDateTime.now());
            entity.setCreatedBy(userEntity.getUsername());

            collectionItemsDAO.save(entity);

            return ResponseEntity.ok(collectionMapper.entityToDto(entity));
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Collection Add Error");
        }
    }

    @Override
    public ResponseEntity<Boolean> delete(String fragranceSlug, Principal principal) throws Exception {
        try{
            UserEntity userEntity = userDAO.findByUsername(principal.getName());

            CollectionItemsEntity entity = collectionItemsDAO.findByFragranceSlugAndUserId(fragranceSlug, userEntity.getId());

            collectionItemsDAO.delete(entity);

            return ResponseEntity.ok(true);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Collection Add Error");
        }
    }

    @Override
    public ResponseEntity<List<FragranceModel>> getAll(Principal principal) throws Exception {
        try{
            UserEntity user = userDAO.findByUsername(principal.getName());
            List<FragranceModel> response = new ArrayList<>();

            List<CollectionItemsEntity> collectionItemsEntities = collectionItemsDAO.findByUserId(user.getId());

            for(CollectionItemsEntity entity : collectionItemsEntities){
                FragranceEntity fragranceEntity = fragranceDAO.findBySlug(entity.getFragranceSlug());

                FragranceModel fragranceModel = fragranceMapper.entityToDto(fragranceEntity);

                lookupImageService.lookupThumbnailImage(fragranceModel, ObjectType.FRAGRANCE, fragranceModel.getId());

                response.add(fragranceModel);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Collection Get Error");
        }
    }

    @Override
    public ResponseEntity<Boolean> checkStatus(String fragranceSlug, Principal principal) throws Exception {
        try{
            UserEntity user = userDAO.findByUsername(principal.getName());

            CollectionItemsEntity entity = collectionItemsDAO.findByFragranceSlugAndUserId(fragranceSlug, user.getId());

            if(entity == null){
                return ResponseEntity.ok(false);
            } else {
                return ResponseEntity.ok(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Status Collection ERROR");
        }
    }
}
