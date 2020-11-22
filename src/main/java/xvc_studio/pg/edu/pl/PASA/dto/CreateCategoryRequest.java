package xvc_studio.pg.edu.pl.PASA.dto;

import lombok.*;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Category;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCategoryRequest {

    /**
     * Category's name.
     */
    private String name;


    public static Function<CreateCategoryRequest, Category> dtoToEntityMapper() {
        return request -> Category.builder()
                .name(request.getName())
                .build();
    }
}