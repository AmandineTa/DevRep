package fr.su.devrep.repository;

import fr.su.devrep.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    /*
     -  <S extends T> S save(S entity); Saves the given entity.
     -  Optional<T> findById(ID primaryKey); Returns the entity identified by the given ID.
     -  Iterable<T> findAll(); Returns all entities.
     -  long count(); Returns the number of entities.
     -  void delete(T entity); Deletes the given entity.
     -  boolean existsById(ID primaryKey); 	Indicates whether an entity with the given ID exists.
    */

}
