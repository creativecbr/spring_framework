package xvc_studio.pg.edu.pl.PASA.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.event.repository.UserEventRepository;
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
     * Repository for sending events about actions on user entities.
     */
    private UserEventRepository eventRepository;


    /**
     * @param userRepository repository for user entity
     */
    @Autowired
    public UserService(UserRepository userRepository, UserEventRepository eventRepository){
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    /**
     *
     * @param login
     * @return container with user
     */
    public Optional<User> find(String login){ return userRepository.findById(login); }

    /**
     *
     * @param login
     * @param password
     * @return container with user. Seeks for single user using login and password.
     */
    public Optional<User> find(String login, String password){ return userRepository.findByLoginAndPassword(login, password); }

    /**
     * Stores new user.
     *
     * @param user
     */
    @Transactional
    public void create(User user){
        userRepository.save(user);
        eventRepository.create(user);
    }

    /**
     * Delete existing user.
     * @param login
     */
    @Transactional
    public void delete(String login){
        User user = userRepository.findByLogin(login).orElseThrow();
        userRepository.delete(user);
        eventRepository.delete(user);
    }

    /**
     * Return all user in list.
     * @return List<User>
     */
    public List<User> findAll(){ return userRepository.findAll(); }


    public void update(User user) { userRepository.save(user); }
}
