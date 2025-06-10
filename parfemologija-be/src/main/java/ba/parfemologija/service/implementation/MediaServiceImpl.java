package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.MediaDAO;
import ba.parfemologija.dao.entities.MediaEntity;
import ba.parfemologija.service.core.MediaService;
import ba.parfemologija.service.core.models.media.MediaCreateModel;
import ba.parfemologija.service.core.models.media.MediaModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {
    MediaDAO mediaDAO;


    @Override
    public ResponseEntity<MediaCreateModel> create(MediaCreateModel request) {
        try{
            MediaEntity mediaEntity = new MediaEntity();

            mediaEntity.setObjectId(request.getObjectId());
            mediaEntity.setImageUrl(request.getImageUrl());
            mediaEntity.setMediaCategory(request.getMediaCategory());
            mediaEntity.setIsThumbnail(true);

            mediaDAO.save(mediaEntity);

            return ResponseEntity.ok(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Media Create Error");
        }
    }
}
