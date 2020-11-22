package xvc_studio.pg.edu.pl.PASA.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import java.util.Optional;

/**
 * User repository, should be used in buisness layer.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
