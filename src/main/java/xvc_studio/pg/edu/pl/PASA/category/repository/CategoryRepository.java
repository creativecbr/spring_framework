package xvc_studio.pg.edu.pl.PASA.category.repository;

import org.springframework.beans.factory.annotation.Autowired;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.data.DataStore;
import xvc_studio.pg.edu.pl.PASA.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Category repository, using in business layer.
 */
@org.springframework.stereotype.Repository
public class CategoryRepository implements Repository<Category, String> {

    /**
     * Temporary database, that should be replaced with properly DB.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public CategoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Category> find(String name) {  return store.findCategory(name);  }

    @Override
    public List<Category> findAll() {
        return store.findAllCategories();
    }

    @Override
    public void create(Category entity) { store.createCategory(entity);   }

    @Override
    public void delete(Category entity) { store.deleteCategory(entity.getName());  }


}
