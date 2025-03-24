package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.sample.SampleModel;

public interface SampleService {
    SampleModel findById(Long id);
}
