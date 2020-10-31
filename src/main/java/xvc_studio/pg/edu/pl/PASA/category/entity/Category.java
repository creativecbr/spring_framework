package xvc_studio.pg.edu.pl.PASA.category.entity;

import lombok.*;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;

import java.io.Serializable;
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
@EqualsAndHashCode
public class Category implements Serializable {


    /**
     * Unique name of category.
     */
    private String name;

}
