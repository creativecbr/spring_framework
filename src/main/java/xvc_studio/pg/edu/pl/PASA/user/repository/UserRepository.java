package xvc_studio.pg.edu.pl.PASA.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.data.DataStore;
import xvc_studio.pg.edu.pl.PASA.repository.Repository;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User repository, should be used in buisness layer.
 */
@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User, String> {

    /**
     * Temporary database, that should be replaced with properly DB.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public UserRepository(DataStore store)
    {
        this.store = store;
    }

    @Override
    public Optional<User> find(String login) {  return store.findUser(login); }

    @Override
    public List<User> findAll() {  return store.findAllUsers(); }

    @Override
    public void create(User entity) {  store.createUser(entity); }

    @Override
    public void delete(User entity) { store.deleteUser(entity.getLogin()); }

    public List<Ad> findAds(String login) {
        return store.getAdsStream()
                .filter(ad -> ad.getUser().getLogin().equals(login))
                .collect(Collectors.toList());
    }

    public Optional<User> find(String login, String password) {   return store.findUser(login, password);   }

}
