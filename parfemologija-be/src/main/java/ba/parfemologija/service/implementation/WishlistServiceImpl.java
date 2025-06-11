package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.FragranceDAO;
import ba.parfemologija.dao.UserDAO;
import ba.parfemologija.dao.WishlistItemsDAO;
import ba.parfemologija.dao.entities.FragranceEntity;
import ba.parfemologija.dao.entities.UserEntity;
import ba.parfemologija.dao.entities.WishlistItemsEntity;
import ba.parfemologija.service.core.LookupImageService;
import ba.parfemologija.service.core.WishlistService;
import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import ba.parfemologija.service.core.models.wishlist.WishlistItemModel;
import ba.parfemologija.service.implementation.mapper.FragranceMapper;
import ba.parfemologija.service.implementation.mapper.WishlistMapper;
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
public class WishlistServiceImpl implements WishlistService {
    WishlistItemsDAO wishlistItemsDAO;
    UserDAO userDAO;
    FragranceDAO fragranceDAO;
    FragranceMapper fragranceMapper;
    LookupImageService lookupImageService;
    WishlistMapper wishlistMapper;

    @Override
    public ResponseEntity<WishlistItemModel> add(String fragranceSlug, Principal principal) throws Exception {
        try{
            UserEntity userEntity = userDAO.findByUsername(principal.getName());

            WishlistItemsEntity entity = new WishlistItemsEntity();

            entity.setUserId(userEntity.getId());
            entity.setFragranceSlug(fragranceSlug);
            entity.setCreatedAt(LocalDateTime.now());
            entity.setCreatedBy(userEntity.getUsername());

            wishlistItemsDAO.save(entity);

            return ResponseEntity.ok(wishlistMapper.entityToDto(entity));
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Wishlist Add Error");
        }
    }

    @Override
    public ResponseEntity<Boolean> delete(String fragranceSlug, Principal principal) throws Exception {
        try{
            UserEntity userEntity = userDAO.findByUsername(principal.getName());

            WishlistItemsEntity entity = wishlistItemsDAO.findByFragranceSlugAndUserId(fragranceSlug, userEntity.getId());

            wishlistItemsDAO.delete(entity);

            return ResponseEntity.ok(true);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Wishlist Add Error");
        }
    }

    @Override
    public ResponseEntity<List<FragranceModel>> getAll(Principal principal) throws Exception {
        try{
            UserEntity user = userDAO.findByUsername(principal.getName());
            List<FragranceModel> response = new ArrayList<>();

            List<WishlistItemsEntity> wishlistItemsEntities = wishlistItemsDAO.findByUserId(user.getId());

            for(WishlistItemsEntity entity : wishlistItemsEntities){
                FragranceEntity fragranceEntity = fragranceDAO.findBySlug(entity.getFragranceSlug());

                FragranceModel fragranceModel = fragranceMapper.entityToDto(fragranceEntity);

                lookupImageService.lookupThumbnailImage(fragranceModel, ObjectType.FRAGRANCE, fragranceModel.getId());

                response.add(fragranceModel);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Wishlist Get Error");
        }
    }

    @Override
    public ResponseEntity<Boolean> checkStatus(String fragranceSlug, Principal principal) throws Exception {
        try{
            UserEntity user = userDAO.findByUsername(principal.getName());

            WishlistItemsEntity entity = wishlistItemsDAO.findByFragranceSlugAndUserId(fragranceSlug, user.getId());

            if(entity == null){
                return ResponseEntity.ok(false);
            } else {
                return ResponseEntity.ok(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Status Wishlist ERROR");
        }
    }
}
