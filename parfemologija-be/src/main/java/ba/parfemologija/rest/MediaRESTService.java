package ba.parfemologija.rest;

import ba.parfemologija.service.core.MediaService;
import ba.parfemologija.service.core.models.media.MediaCreateModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Media", description = "Media API")
@AllArgsConstructor
@RequestMapping(value = "media")
public class MediaRESTService {
    MediaService mediaService;

    @Operation(description = "Add a new media entry")
    @PostMapping
    public ResponseEntity<MediaCreateModel> create(@RequestBody MediaCreateModel request){
        return mediaService.create(request);
    }
}
