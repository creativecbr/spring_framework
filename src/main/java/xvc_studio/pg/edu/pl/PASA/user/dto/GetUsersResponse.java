package xvc_studio.pg.edu.pl.PASA.user.dto;


import lombok.*;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;

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
public class GetUsersResponse {


    @Singular
    private List<String> users;

    public static Function<Collection<User>, GetUsersResponse> entityToDtoMapper()
    {
        return users -> {
            GetUsersResponseBuilder response = GetUsersResponse.builder();
            users.stream()
                    .map(User::getLogin)
                    .forEach(response::user);
            return response.build();
        };
    }
}
