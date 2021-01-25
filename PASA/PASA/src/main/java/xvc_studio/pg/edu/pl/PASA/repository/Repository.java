package xvc_studio.pg.edu.pl.PASA.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {

    /**
     * Find entity object using its primary key.
     *
     * @param id object primary key
     * @return container (can be empty/null) with entity object
     */
    Optional<E> find(K id);

    /**
     * Find all objects.
     *
     * @return list (can be empty) with all objects.
     */
    List<E> findAll();

    /**
     * Save new object in the data store.
     *
     * @param entity object to be saved in data store.
     */
    void create(E entity);

    /**
     * Delete object from data store
     *
     * @param entity object to be deleted
     */
    void delete(E entity);



}
