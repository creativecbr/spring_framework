package xvc_studio.pg.edu.pl.PASA.dto;


import lombok.*;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Category;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateCategoryRequest {

    /**
     * Category's name.
     */
    private String name;


    /**
     * @return updater for convenient updating entity object using dto object
     */
    public static BiFunction<Category, UpdateCategoryRequest, Category> dtoToEntityUpdater() {
        return (category, request) -> {
            category.setName(request.getName());
            return category;
        };
    }
}