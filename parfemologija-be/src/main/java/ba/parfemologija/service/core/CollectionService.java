package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.collection.CollectionItemModel;
import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface CollectionService {
    ResponseEntity<CollectionItemModel> add(String fragranceSlug, Principal principal) throws Exception;
    ResponseEntity<Boolean> delete(String fragranceSlug, Principal principal) throws Exception;
    ResponseEntity<List<FragranceModel>> getAll(Principal principal) throws Exception;
    ResponseEntity<Boolean> checkStatus(String fragranceSlug, Principal principal) throws Exception;
}

