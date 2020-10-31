package xvc_studio.pg.edu.pl.PASA.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.category.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

/**
* Category service created for all business actions towards category entity.
*/
@Service
public class CategoryService {

    /**
     * Repository for Category entity.
     */
    private CategoryRepository repository;

    /**
     * @param repository repository for category entity
     */
    @Autowired
    public CategoryService(CategoryRepository repository){ this.repository = repository; }

    public void create(Category category){ repository.create(category); }

    public void delete(String name){ repository.delete(repository.find(name).orElseThrow()); }

    public Optional<Category> find(String name) { return repository.find(name); }

    public List<Category> findAll(){ return repository.findAll(); }

}
