package xvc_studio.pg.edu.pl.PASA.dto;
import lombok.*;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAdsByCategoriesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Ad {
        /**
         * Title of the ad.
         */
        private String title;

        /**
         * Description of ad.
         */
        private String description;

        /**
         *  Ad's icon path.
         */
        private String iconPath;

    }

    @Singular
    private List<Ad> ads;

    public static Function<Collection<xvc_studio.pg.edu.pl.PASA.ad.entity.Ad>, GetAdsByCategoriesResponse> entityToDtoMapper() {
        return ads -> {
            GetAdsByCategoriesResponseBuilder response = GetAdsByCategoriesResponse.builder();
            ads.stream()
                    .map(ad -> Ad.builder()
                            .title(ad.getTitle())
                            .description(ad.getDescription())
                            .iconPath(ad.getIconPath())
                            .build())
                    .forEach(response::ad);
            return response.build();
        };
    }
}