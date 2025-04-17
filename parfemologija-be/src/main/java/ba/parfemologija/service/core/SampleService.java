package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.sample.SampleCreateRequest;
import ba.parfemologija.service.core.models.sample.SampleModel;
import ba.parfemologija.service.core.models.sample.SampleUpdateRequest;

import java.util.List;

// This is just the definition of the service. The actual implementation is in SampleServiceImplementation.
public interface SampleService {
    SampleModel findById(Long id);
    List<SampleModel> getAll();
    SampleModel save(SampleCreateRequest request);
    SampleModel update(SampleUpdateRequest request);
    void delete(Long id);
}
