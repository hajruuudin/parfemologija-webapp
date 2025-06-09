// src/main/java/ba/parfemologija/service/core/BrandService.java
package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.brand.BrandCreate;
import ba.parfemologija.service.core.models.brand.BrandModel;
import ba.parfemologija.service.core.models.brand.BrandUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

public interface BrandService {
    ResponseEntity<Page<BrandModel>> find(PageRequest request);
    ResponseEntity<BrandModel> findById(Long id);
    ResponseEntity<BrandModel> create(BrandCreate request) throws Exception;
    ResponseEntity<BrandModel> update(BrandUpdate request);
    ResponseEntity<Boolean> deleteById(Long id);
}
