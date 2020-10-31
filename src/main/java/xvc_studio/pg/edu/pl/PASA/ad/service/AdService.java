package xvc_studio.pg.edu.pl.PASA.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.ad.repository.AdRepository;

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
    private AdRepository repository;

    /**
     * @param repository repository for Ad entity
     */
    @Autowired
    public AdService(AdRepository repository){ this.repository = repository; }

    public Optional<Ad> find(Long id) { return repository.find(id); }

    public void create(Ad ad) { repository.create(ad); }

    public void delete(Long id) { repository.delete(repository.find(id).orElseThrow()); }

    public List<Ad> findAll() { return repository.findAll(); }



}
