package xvc_studio.pg.edu.pl.PASA.user.event.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.event.dto.CreateUserRequest;

@Repository
public class UserEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public UserEventRepository(@Value("${pasa.ads.url}") String baseUrl)
    {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(User user){
        restTemplate.delete("/users/{username}", user.getLogin());
    }
    public void create(User user) { restTemplate.postForLocation("/users", CreateUserRequest.entityToDtoMapper().apply(user)); }

}
