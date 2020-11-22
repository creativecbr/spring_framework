package xvc_studio.pg.edu.pl.PASA.user.dto;


import lombok.*;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateUserRequest {

    /**
     * User's name.
     */
    private String name;

    /**
     * User's surnname.
     */
    private String surname;



    /**
     * @return updater for convenient updating entity object using dto object
     */
    public static BiFunction<User, UpdateUserRequest, User> dtoToEntityUpdater() {
        return (user, request) -> {
            user.setName(request.getName());
            user.setSurname(request.getSurname());
            return user;
        };
    }
}