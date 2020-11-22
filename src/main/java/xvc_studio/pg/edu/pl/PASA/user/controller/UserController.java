package xvc_studio.pg.edu.pl.PASA.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xvc_studio.pg.edu.pl.PASA.user.dto.*;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.service.UserService;

import java.util.Optional;

/**
 * REST controller for user's ads resource.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("{username}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("username") String username)
    {
        return userService.find(username).map(value -> ResponseEntity.ok(GetUserResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<GetUsersResponse> getUsers()
    {
        return ResponseEntity.ok(GetUsersResponse.entityToDtoMapper().apply(userService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request, UriComponentsBuilder builder)
    {
        User user = CreateUserRequest.dtoToEntityMapper().apply(request);
        Optional <User> user_in_db = userService.find(user.getLogin());
        if(user_in_db.isEmpty())
        {
            userService.create(user);
            return ResponseEntity.created(builder.pathSegment("users", "{username}")
                    .buildAndExpand(user.getLogin()).toUri()).build();
        }
        else
            return ResponseEntity.status(409).build();
    }

    @PutMapping("{username}")
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserRequest request, @PathVariable("username") String username)
    {
        Optional<User> user = userService.find(username);
        if(user.isPresent())
        {
            UpdateUserRequest.dtoToEntityUpdater().apply(user.get(), request);
            userService.update(user.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username)
    {
        Optional<User> user = userService.find(username);
        if(user.isPresent())
        {
            userService.delete(user.get().getLogin());
            return ResponseEntity.accepted().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }


}
