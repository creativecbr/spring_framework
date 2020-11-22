package xvc_studio.pg.edu.pl.PASA.ad.entity;

import lombok.*;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Category entity describes one category. Every Ad should have one associated category.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@EqualsAndHashCode
@Table(name = "categories")
public class Category implements Serializable {

    /**
     * Unique id of category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of category.
     */
    private String name;


}
