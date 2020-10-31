package xvc_studio.pg.edu.pl.PASA.ad.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.data.DataStore;
import xvc_studio.pg.edu.pl.PASA.repository.Repository;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Repository for advertisement entity. Use in business layer.
 */
@org.springframework.stereotype.Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    /**
     * Seeks for single ad searched by id.
     * @param id ad's id
     * @return container with Ad (can be empty)
     */
    Optional<Ad> findById(Long id);

    /**
     * Get the list of all specify user's add.
     * @param user
     * @return list of user's ads (can be empty)
     */
    List<Ad> findAllByUser(User user);

    List<Ad> findAllByCategory(Category category);

    Optional<Ad> findByIdAndUser(Long id, User user);
}
