package xvc_studio.pg.edu.pl.PASA.dto;

import lombok.*;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAdResponse {

    /**
     * Unique advertisement's title.
     */
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
    private String category;

    /**
     * Advertisement's owner.
     */
    private String user;

    /**
     * Advertisement's icon path.
     */
    private String iconPath;



    public static Function<Ad, GetAdResponse> entityToDtoMapper()
    {
        return ad -> GetAdResponse.builder()
                .id(ad.getId())
                .title(ad.getTitle())
                .description(ad.getDescription())
                .category(ad.getCategory().getName())
                .user(ad.getUser().getLogin())
                .iconPath(ad.getIconPath())
                .build();

        }

}
