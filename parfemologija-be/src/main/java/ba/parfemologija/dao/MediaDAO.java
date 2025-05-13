package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.MediaEntity;
import ba.parfemologija.utils.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaDAO extends JpaRepository<MediaEntity, Long> {
    @Query("SELECT me FROM MediaEntity me WHERE me.objectId = :objectId AND me.mediaCategory = :mediaCategory")
    List<MediaEntity> findByObjectIdAndMediaCategory(@Param("objectId") Long objectId, @Param("mediaCategory") ObjectType mediaCategory);

    @Query("SELECT me.imageUrl FROM MediaEntity me WHERE me.objectId = :objectId AND me.mediaCategory = :mediaCategory AND me.isThumbnail = true")
    String findThumbnailUrlByObjectIdAndMediaCategory(@Param("objectId") Long objectId, @Param("mediaCategory") ObjectType mediaCategory);

    @Query("SELECT me.imageUrl FROM MediaEntity me WHERE me.objectId = :objectId AND me.mediaCategory = :mediaCategory")
    List<String> findAllImageUrlsByObjectIdAndMediaCategory(@Param("objectId") Long objectId, @Param("mediaCategory") ObjectType mediaCategory);

}
