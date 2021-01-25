package xvc_studio.pg.edu.pl.PASA.category.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;

import java.util.Optional;


/**
 * Category repository, using in business layer.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findByName(String name);
}
