package xvc_studio.pg.edu.pl.PASA.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.ad.service.AdService;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.category.service.CategoryService;
import xvc_studio.pg.edu.pl.PASA.dto.CreateAdRequest;
import xvc_studio.pg.edu.pl.PASA.dto.GetAdResponse;
import xvc_studio.pg.edu.pl.PASA.dto.GetAdsResponse;
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
    public ResponseEntity<Void> createAd(@PathVariable("username") String username, @RequestBody CreateAdRequest request, UriComponentsBuilder builder)
    {
        Optional<User> user = userService.find(username);
        if(user.isPresent())
        {
            Ad ad = CreateAdRequest
                    .dtoToEntityMapper(name -> categoryService.find(name).orElseThrow(), user::get)
                    .apply(request);
            ad = adService.create(ad);

            return ResponseEntity.created(builder.pathSegment("users", "{username}", "ads", "{id}")
                    .buildAndExpand(user.get().getLogin(), ad.getId()).toUri()).build();

        }
        else
            return ResponseEntity.notFound().build();
    }


}
