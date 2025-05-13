// src/main/java/ba/parfemologija/service/core/UserCollectionService.java
package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.usercollection.UserCollectionCreate;
import ba.parfemologija.service.core.models.usercollection.UserCollectionModel;
import ba.parfemologija.service.core.models.usercollection.UserCollectionUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface UserCollectionService {
    ResponseEntity<Page<UserCollectionModel>> find(PageRequest request);
    ResponseEntity<UserCollectionModel> create(UserCollectionCreate request) throws Exception;
    ResponseEntity<UserCollectionModel> update(UserCollectionUpdate request);
    ResponseEntity<Boolean> deleteById(Long id);
}
