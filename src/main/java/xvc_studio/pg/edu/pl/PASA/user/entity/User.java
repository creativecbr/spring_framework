package xvc_studio.pg.edu.pl.PASA.user.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
    Entity for system user. Contain information about specific user and enable provide authorization and authentication.
*/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class User implements Serializable {

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
    @ToString.Exclude
    private String password;

    /**
     * User's unique email.
     */
    private String email;

    /**
     * User's list of ads.
     */
    private List<Ad> ads;

}
