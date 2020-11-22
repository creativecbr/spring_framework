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
public class GetUserResponse {

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


    public static Function<User, GetUserResponse> entityToDtoMapper()
    {
        return user -> GetUserResponse.builder()
                .login(user.getLogin())
                .name(user.getName())
                .surname(user.getSurname())
                .birthDate(user.getBirthDate())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
        }

}
