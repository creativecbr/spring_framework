package xvc_studio.pg.edu.pl.PASA.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.ad.repository.AdRepository;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.category.repository.CategoryRepository;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.repository.UserRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for all business actions towards Ad entity.
 */
@Service
public class AdService {

    /**
     * Repository for Ad entity.
     */
    private AdRepository adRepository;

    /**
     * Repository for User entity.
     */
    private UserRepository userRepository;

    /**
     * Repository for Category entity.
     */
    private CategoryRepository categoryRepository;



    /**
     * @param adRepository repository of Ad entity.
     * @param userRepository repository of User entity.
     */
    @Autowired
    public AdService(AdRepository adRepository, UserRepository userRepository){
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Ad create(Ad ad){ return adRepository.save(ad); }

    @Transactional
    public void delete(Long id){ adRepository.deleteById(id); }

    @Transactional
    public void update(Ad ad){ adRepository.save(ad); }

    public List<Ad> findAll() { return adRepository.findAll(); }

    public List<Ad> findAll(User user) { return adRepository.findAllByUser(user); }

    public Optional<Ad> find(Long id) { return adRepository.findById(id); }

    public Optional<Ad> find(Long id, String login) {

        Optional<User> user =userRepository.findByLogin(login);
        if(user.isPresent()) {
            return adRepository.findByIdAndUser(id, user.get());
        } else {
            return Optional.empty();
        }

    }


    /** Seeks for all ads in specify category by category's name.
     *
     * @param name category's name
     * @return list with ad's in this category.
     */
    public List<Ad> findAllByCategoryName(String name) {

        Optional<Category> category = categoryRepository.findById(name);
        if(category.isPresent())
        {
            return adRepository.findAllByCategory(category.get());
        } else {
            return Collections.emptyList();
        }
    }


}
