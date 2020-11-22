package xvc_studio.pg.edu.pl.PASA.user.dto;

import lombok.*;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateUserRequest {

    /**
     * User's login.
     */
    private String login;

    /**
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<CreateUserRequest, User> dtoToEntityMapper() {
        return request -> User.builder()
                .login(request.getLogin())
                .build();
    }

}