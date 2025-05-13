// src/main/java/ba/parfemologija/service/core/UserWishlistService.java
package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.userwishlist.UserWishlistCreate;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistModel;
import ba.parfemologija.service.core.models.userwishlist.UserWishlistUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface UserWishlistService {
    ResponseEntity<Page<UserWishlistModel>> find(PageRequest request);
    ResponseEntity<UserWishlistModel> create(UserWishlistCreate request) throws Exception;
    ResponseEntity<UserWishlistModel> update(UserWishlistUpdate request);
    ResponseEntity<Boolean> deleteById(Long id);
}
