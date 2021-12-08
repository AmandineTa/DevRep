package fr.su.devrep.repository;

import fr.su.devrep.models.Client;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.email = :email")
    Client findByEmail(@Param("email") String email);

    @Query("select c from Client c where c.tel = :tel")
    Client findByTel(@Param("tel") String tel);



    /*
     -  <S extends T> S save(S entity); Saves the given entity.
     -  Optional<T> findById(ID primaryKey); Returns the entity identified by the given ID.
     -  Iterable<T> findAll(); Returns all entities.
     -  long count(); Returns the number of entities.
     -  void delete(T entity); Deletes the given entity.
     -  boolean existsById(ID primaryKey); 	Indicates whether an entity with the given ID exists.
    */

}
