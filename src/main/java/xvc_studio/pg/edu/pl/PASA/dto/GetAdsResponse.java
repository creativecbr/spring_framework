package xvc_studio.pg.edu.pl.PASA.dto;

import lombok.*;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;

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
public class GetAdsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Ad {
        /**
         * Unique id identifying ad.
         */
        private Long id;

        /**
         * Title of the ad.
         */
        private String title;

        /**
         * Description of ad.
         */
        private String description;
    }

    @Singular
    private List<Ad> ads;

    public static Function<Collection<xvc_studio.pg.edu.pl.PASA.ad.entity.Ad>, GetAdsResponse> entityToDtoMapper()
    {
        return ads -> {
            GetAdsResponseBuilder response = GetAdsResponse.builder();
            ads.stream()
                    .map(ad -> Ad.builder()
                        .id(ad.getId())
                        .title(ad.getTitle())
                        .description(ad.getDescription())
                        .build())
                    .forEach(response::ad);
            return response.build();
        };
    }
}
