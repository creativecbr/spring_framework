package xvc_studio.pg.edu.pl.PASA.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.repository.UserRepository;

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
    private UserRepository repository;

    /**
     * @param repository repository for user entity
     */
    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    /**
     * Stores new user in the storage
     * @param user
     */
    public void create(User user){ repository.create(user); }

    public void delete(String login){ repository.delete(repository.find(login).orElseThrow()); }

    public List<User> findAll(){ return repository.findAll(); }

    public Optional<User> find(String login){ return repository.find(login); }

    public Optional<User> find(String login, String password){ return repository.find(login, password); }

    public List<Ad> findAllUsersAds(String login) { return repository.findAds(login); }

}
