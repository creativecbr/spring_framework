package xvc_studio.pg.edu.pl.PASA.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * User business layer, created for handle all actions regarding User entity.
 */
@Service
public class UserService {

    /**
     * Repository for User entity.
     */
    private UserRepository userRepository;

    /**
     * @param userRepository repository for user entity
     */
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     *
     * @param login
     * @return container with user
     */
    public Optional<User> find(String login){ return userRepository.findById(login); }

    /**
     * Stores new user.
     *
     * @param user
     */
    @Transactional
    public void create(User user){ userRepository.save(user); }

    /**
     * Delete existing user.     *
     */
    @Transactional
    public void delete(User user){ userRepository.delete(user); }



}
