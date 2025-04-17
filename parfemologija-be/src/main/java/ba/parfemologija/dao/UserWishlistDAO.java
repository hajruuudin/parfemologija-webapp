package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.UserWishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWishlistDAO extends JpaRepository<UserWishlistEntity, Long> {
}
