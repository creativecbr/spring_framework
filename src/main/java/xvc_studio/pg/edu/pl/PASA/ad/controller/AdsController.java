package xvc_studio.pg.edu.pl.PASA.ad.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.ad.service.AdService;
import xvc_studio.pg.edu.pl.PASA.ad.service.CategoryService;
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
@RequestMapping("/ads")
public class AdsController {

    private CategoryService categoryService;
    private AdService adService;


    @Autowired
    public AdsController(CategoryService categoryService, AdService adService)
    {
        this.adService = adService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<GetAdsResponse> getAds()
    {
        List<Ad> ads = adService.findAll();
        Function<Collection<Ad>, GetAdsResponse> mapper = GetAdsResponse.entityToDtoMapper();
        GetAdsResponse response = mapper.apply(ads);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAdResponse> getAd(@PathVariable("id") long id)
    {
       return adService.find(id).map(value -> ResponseEntity.ok(GetAdResponse.entityToDtoMapper().apply(value)))
               .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Void> createAd(@RequestBody CreateAdRequest request, UriComponentsBuilder builder)
    {
            Ad ad = CreateAdRequest
                    .dtoToEntityMapper(category -> categoryService.find(category).orElseThrow(), () -> null)
                    .apply(request);
            ad = adService.create(ad);
            return ResponseEntity.created(builder.pathSegment("ads", "{id}")
                    .buildAndExpand(ad.getId()).toUri()).build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAd(@RequestBody UpdateAdRequest request, @PathVariable("id") long id)
    {
        Optional<Ad> ad = adService.find(id);
        if(ad.isPresent())
        {
            UpdateAdRequest.dtoToEntityUpdater().apply(ad.get(), request);
            adService.update(ad.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") long id)
    {
        Optional<Ad> ad = adService.find(id);
        if(ad.isPresent())
        {
            adService.delete(ad.get().getId());
            return ResponseEntity.accepted().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }


}
