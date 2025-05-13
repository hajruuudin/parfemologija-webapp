package ba.parfemologija.service.core;

import ba.parfemologija.utils.ObjectType;

public interface LookupImageService {
    <T> void lookupThumbnailImage(T objectModel, ObjectType objectType, Long objectId);
    <T> void lookupAllImages(T objectModel, ObjectType objectType, Long objectId);
}

