package xvc_studio.pg.edu.pl.PASA.dto;


import lombok.*;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateAdRequest {

    /**
     * Ad's title.
     */
    private String title;

    /**
     * Ad's description.
     */
    private String description;



    /**
     * @return updater for convenient updating entity object using dto object
     */
    public static BiFunction<Ad, UpdateAdRequest, Ad> dtoToEntityUpdater() {
        return (ad, request) -> {
            ad.setTitle(request.getTitle());
            ad.setDescription(request.getDescription());
            return ad;
        };
    }
}