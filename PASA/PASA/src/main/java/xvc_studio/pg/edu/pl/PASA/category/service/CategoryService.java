package xvc_studio.pg.edu.pl.PASA.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.category.repository.CategoryRepository;

import javax.transaction.Transactional;
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
    private CategoryRepository categoryRepository;

    /**
     * @param repository repository for category entity
     */
    @Autowired
    public CategoryService(CategoryRepository repository){ this.categoryRepository = repository; }

    @Transactional
    public Category create(Category category){ return categoryRepository.save(category); }

    @Transactional
    public void delete(Long id){ categoryRepository.deleteById(id); }

    @Transactional
    public void update(Category category) {categoryRepository.save(category); }


    public Optional<Category> find(Long id) { return categoryRepository.findById(id); }

    public Optional<Category> find(String name) { return categoryRepository.findByName(name); }

    public List<Category> findAll(){ return categoryRepository.findAll(); }

}
