package xvc_studio.pg.edu.pl.PASA.user.dto;

import lombok.*;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import java.time.LocalDate;
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
     * User's unique login.
     */
    private String login;

    /**
     * User's name.
     */
    private String name;

    /**
     * User's surnname.
     */
    private String surname;

    /**
     * User's birth date.
     */
    private LocalDate birthDate;

    /**
     * User's password.
     */
    private String password;

    /**
     * User's unique email.
     */
    private String email;


    public static Function<CreateUserRequest, User> dtoToEntityMapper() {
        return request -> User.builder()
                .login(request.getLogin())
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }
}