package xvc_studio.pg.edu.pl.PASA.dto;


import lombok.*;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateAdRequest {

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
    private String category;

    /**
     * Advertisement's owner.
     */
    private String user;




    /**
     * @param categoryFunction function for converting category name to category entity object
     * @param userSupplier     supplier for providing new character owner
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<CreateAdRequest, Ad> dtoToEntityMapper(
            Function<String, Category> categoryFunction,
            Function<String, User> adFunction) {
        return request -> Ad.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .user(adFunction.apply(request.getUser()))
                .category(categoryFunction.apply(request.getCategory()))
                .build();
    }

}
