package xvc_studio.pg.edu.pl.PASA.ad.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.ad.service.AdService;
import xvc_studio.pg.edu.pl.PASA.ad.service.CategoryService;
import xvc_studio.pg.edu.pl.PASA.dto.*;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.service.UserService;

import java.util.Optional;

/**
 * REST controller for user's ads resource.
 */

@RestController
@RequestMapping("/users/{username}/ads")
public class UserAdsController {

    private UserService userService;
    private CategoryService categoryService;
    private AdService adService;


    @Autowired
    public UserAdsController(UserService userService, CategoryService categoryService, AdService adService)
    {
        this.adService = adService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<GetAdsResponse> getAds(@PathVariable("username") String username)
    {
        return userService.find(username).map(value -> ResponseEntity.ok(GetAdsResponse.entityToDtoMapper().apply(adService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAdResponse> getAd(@PathVariable("username") String username, @PathVariable("id") long id)
    {
        return adService.find(id, username).map(value -> ResponseEntity.ok(GetAdResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createAd(@PathVariable("username") String username, @RequestBody CreateAdOnUserRequest request, UriComponentsBuilder builder)
    {
        Optional<User> user = userService.find(username);
        if(user.isPresent())
        {
            Ad ad = CreateAdOnUserRequest
                    .dtoToEntityMapper(name -> categoryService.find(name).orElseThrow(), user::get)
                    .apply(request);
            ad = adService.create(ad);

            return ResponseEntity.created(builder.pathSegment("users", "{username}", "ads", "{id}")
                    .buildAndExpand(user.get().getLogin(), ad.getId()).toUri()).build();

        }
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAd(@PathVariable("username") String username, @RequestBody UpdateAdRequest request, @PathVariable("id") long id)
    {
        Optional<Ad> ad = adService.find(id, username);
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
    public ResponseEntity<Void> deleteUserAd(@PathVariable("username") String username, @PathVariable("id") long id)
    {
        Optional<Ad> ad = adService.find(id, username);
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
