package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.accord.AccordCreate;
import ba.parfemologija.service.core.models.accord.AccordModel;
import ba.parfemologija.service.core.models.accord.AccordUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface AccordService {
    ResponseEntity<Page<AccordModel>> find(PageRequest request);
    ResponseEntity<AccordModel> create(AccordCreate request) throws Exception;
    ResponseEntity<AccordModel> update(AccordUpdate update);
    ResponseEntity<Boolean> deleteById(Long id);
}
