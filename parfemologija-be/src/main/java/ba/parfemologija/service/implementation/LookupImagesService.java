package ba.parfemologija.service.implementation;

import ba.parfemologija.dao.MediaDAO;
import ba.parfemologija.service.core.LookupImageService;
import ba.parfemologija.utils.ObjectType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
@AllArgsConstructor
public class LookupImagesService implements LookupImageService {
    MediaDAO mediaDAO;

    @Override
    public <T> void lookupThumbnailImage(T objectModel, ObjectType objectType, Long objectId) {
        if (objectModel == null || objectType == null || objectId == null) {
            return; // Handle null inputs appropriately
        }

        String thumbnailUrl = mediaDAO.findThumbnailUrlByObjectIdAndMediaCategory(objectId, objectType);

        if (thumbnailUrl != null) {
            try {
                // Use reflection to find and invoke the setImageURL method
                Method setImageURLMethod = objectModel.getClass().getMethod("setThumbnailImageUrl", String.class);
                setImageURLMethod.invoke(objectModel, thumbnailUrl);
            } catch (NoSuchMethodException e) {
                System.err.println("Object of type " + objectModel.getClass().getName() +
                        " does not have a setImageUrl(String) method.");
                // Optionally throw an exception or handle this case differently
            } catch (Exception e) {
                System.err.println("Error invoking setImageUrl method on " + objectModel.getClass().getName() + ": " + e.getMessage());
                // Optionally throw an exception or handle this case differently
            }
        } else {
            // Optionally handle the case where no image is found
            try {
                Method setImageURLMethod = objectModel.getClass().getMethod("setThumbnailImageUrl", String.class);
                setImageURLMethod.invoke(objectModel, "/assets/object-images/default.jpg"); // Or a default URL
            } catch (NoSuchMethodException e) {
                // Handle if the object doesn't have the setter
            } catch (Exception e) {
                // Handle invocation errors
            }
        }
    }

    @Override
    public <T> void lookupAllImages(T objectModel, ObjectType objectType, Long objectId) {

    }
}