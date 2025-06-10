package ba.parfemologija.service.core;

import ba.parfemologija.service.core.models.media.MediaCreateModel;
import ba.parfemologija.service.core.models.media.MediaModel;
import org.springframework.http.ResponseEntity;

public interface MediaService {
    ResponseEntity<MediaCreateModel> create(MediaCreateModel request);
}
