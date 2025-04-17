package ba.parfemologija.dao;

import ba.parfemologija.dao.entities.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDAO extends JpaRepository<SampleEntity, Long> {
    // Every entity has its own DAO. This part is mostly done automatically
    /*
    * After the entity, next is the DAO. This dao is basically the part that accesses data from the table
    *
    * Little bit of an explanation: when you write "extends JpaEntity<Entity, Id of the entity>, spring basically
    * creates default methods for CRUD operations with entities. We dont have to write the queries
    * like we did in web programming, its all done by default. You got stuff like .save .findAll, .findById and so on
    *
    * When you need a very specific query to do, you use @Query annotation. Well use it later but for now it aint necessary
    *
    * */
}
