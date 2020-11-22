package xvc_studio.pg.edu.pl.PASA.ad.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import javax.persistence.*;
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
@Entity
@Table(name = "ads")
@EqualsAndHashCode
public class Ad implements Serializable {

    /**
     * Unique Ad's identification number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category")
    private Category category;

    /**
     * Advertisement's owner.
     */
    @ManyToOne
    @JoinColumn(name = "user")
    @ToString.Exclude
    private User user;

}
