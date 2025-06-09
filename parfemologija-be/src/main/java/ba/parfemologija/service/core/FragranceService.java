package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.fragrance.FragranceCreateRequest;
import ba.parfemologija.service.core.models.fragrance.FragranceModel;
import ba.parfemologija.service.core.models.fragrance.FragranceUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FragranceService {
    ResponseEntity<Page<FragranceModel>> find(PageRequest request, String search, List<Integer> brandIds, Integer typeId, String gender);
    ResponseEntity<FragranceModel> create(FragranceCreateRequest request) throws Exception;
    ResponseEntity<FragranceModel> update(FragranceUpdateRequest update);
    ResponseEntity<Boolean> deleteById(Long id);
}
