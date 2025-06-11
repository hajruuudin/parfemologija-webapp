package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import ba.parfemologija.service.core.models.wishlist.WishlistItemModel;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface WishlistService {
    ResponseEntity<WishlistItemModel> add(String fragranceSlug, Principal principal) throws Exception;
    ResponseEntity<Boolean> delete(String fragranceSlug, Principal principal) throws Exception;
    ResponseEntity<List<FragranceModel>> getAll(Principal principal) throws Exception;
    ResponseEntity<Boolean> checkStatus(String fragranceSlug, Principal principal) throws Exception;
}
