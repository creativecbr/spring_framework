package xvc_studio.pg.edu.pl.PASA.ad.repository;

import org.springframework.beans.factory.annotation.Autowired;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.data.DataStore;
import xvc_studio.pg.edu.pl.PASA.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for advertisement entity. Use in business layer.
 */
@org.springframework.stereotype.Repository
public class AdRepository  implements Repository<Ad, Long> {

    /**
     * Temporary database, that should be replaced with properly DB.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public AdRepository(DataStore store) { this.store = store;}

    @Override
    public Optional<Ad> find(Long id) {
        return store.findAd(id);
    }

    @Override
    public List<Ad> findAll() { return store.findAllAds(); }

    @Override
    public void create(Ad entity) { store.createAd(entity); }

    @Override
    public void delete(Ad entity) { store.deleteAd(entity.getId()); }

}
