package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Long> {
    @Query("SELECT ue FROM UserEntity ue WHERE  ue.username = :username")
    public UserEntity findByUsername(String username);
}
