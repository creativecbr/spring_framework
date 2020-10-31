package xvc_studio.pg.edu.pl.PASA.ad.entity;

import lombok.*;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import java.io.Serializable;

/**
 * Entity for advertisement added by user. Contains title and description. Ad belongs to one category.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Ad implements Serializable {

    /**
     * Unique Ad's identification number.
     */
    private Long Id;

    /**
     * Advertisement's title.
     */
    private String title;

    /**
     * Advertisement's content.
     */
    private String description;

    /**
     * Advertisement's category.
     */
    private Category category;

    /**
     * Advertisement's owner.
     */
    @ToString.Exclude
    private User user;

}
