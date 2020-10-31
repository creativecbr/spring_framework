package xvc_studio.pg.edu.pl.PASA.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.data.DataStore;
import org.springframework.stereotype.Repository;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User repository, should be used in buisness layer.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Seeks for single user using login and password (can be empty)
     * @param login         user's login
     * @param password      user's password
     * @return              container with user (can be empty)
     */
    Optional<User> findByLoginAndPassword(String login, String password);


    /**
     * Seeks for single user by login.
     * @param login user's login.
     * @return container with user (can be empty)
     */
    Optional<User> findByLogin(String login);

}
