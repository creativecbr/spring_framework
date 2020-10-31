package xvc_studio.pg.edu.pl.PASA.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.ad.service.AdService;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.category.service.CategoryService;
import xvc_studio.pg.edu.pl.PASA.dto.*;
import xvc_studio.pg.edu.pl.PASA.user.service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * REST controller for ads resource.
 */

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private UserService userService;
    private CategoryService categoryService;
    private AdService adService;


    @Autowired
    public CategoriesController(UserService userService, CategoryService categoryService, AdService adService)
    {
        this.adService = adService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<GetCategoriesResponse> getCategories()
    {
        List<Category> categories = categoryService.findAll();
        Function<Collection<Category>, GetCategoriesResponse> mapper = GetCategoriesResponse.entityToDtoMapper();
        GetCategoriesResponse response = mapper.apply(categories);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetAdsByCategoriesResponse> getCategories(@PathVariable("name") String name)
    {

        List<Ad> ads = adService.findAllByCategoryName(name);
        Function<Collection<Ad>, GetAdsByCategoriesResponse> mapper = GetAdsByCategoriesResponse.entityToDtoMapper();
        GetAdsByCategoriesResponse response = mapper.apply(ads);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CreateCategoryRequest request, UriComponentsBuilder builder)
    {
        Optional<Category> tmp = categoryService.find(request.getName());

        if(tmp.isEmpty())
        {
            Category category = CreateCategoryRequest.dtoToEntityMapper().apply(request);
            categoryService.create(category);
            return ResponseEntity.created(builder.pathSegment("categories", "{name}")
                    .buildAndExpand(category.getName()).toUri()).build();
        }
        else
        {
            return ResponseEntity.status(403).build();
        }


    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAd(@RequestBody UpdateCategoryRequest request, @PathVariable("id") long id)
    {
        Optional<Category> category = categoryService.find(id);
        if(category.isPresent())
        {
            UpdateCategoryRequest.dtoToEntityUpdater().apply(category.get(), request);
            categoryService.update(category.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") long id)
    {
        Optional<Category> category = categoryService.find(id);
        if(category.isPresent())
        {
            categoryService.delete(category.get().getId());
            return ResponseEntity.accepted().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }




}
