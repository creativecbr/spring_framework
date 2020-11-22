package xvc_studio.pg.edu.pl.PASA.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import xvc_studio.pg.edu.pl.PASA.user.dto.CreateUserRequest;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.service.UserService;

import java.util.Optional;

/**
 * REST controller for user resource. It does not return or receive entity objects but dto objects which present
 * only those fields which are converted to JSON.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Service for managing users.
     */
    private UserService userService;


    /**
     * @param userService service for managing users
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Deletes selected user.
     *
     * @param username user's login
     * @return accepted for not found if character does not exist
     */
    @DeleteMapping("{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        Optional<User> user = userService.find(username);
        if (user.isPresent()) {
            userService.delete(user.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param request new user parsed from JSON
     * @param builder URI builder
     * @return response with location header
     */
    @PostMapping("")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request, UriComponentsBuilder builder) {
        User user = CreateUserRequest.dtoToEntityMapper().apply(request);
        userService.create(user);
        return ResponseEntity.created(builder.pathSegment("users", "{username}")
                .buildAndExpand(user.getLogin()).toUri()).build();
    }

}
