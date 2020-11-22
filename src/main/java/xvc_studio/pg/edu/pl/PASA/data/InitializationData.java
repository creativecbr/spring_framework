package xvc_studio.pg.edu.pl.PASA.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.service.UserService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitializationData {

    /**
     * Service for handle Users operations.
     */
    private final UserService userService;


    @Autowired
    public InitializationData(UserService userService)
    {
        this.userService = userService;
    }

    @PostConstruct
    private synchronized void init(){

        User admin = User.builder()
                .login("admin")
                .name("Paweł")
                .surname("Leśniewski")
                .password("strongenough")
                .email("s175724@student.pg.edu.pl")
                .birthDate(LocalDate.of(1998, 5, 17))
                .build();

        User user1 = User.builder()
                .login("creativexvc")
                .name("Mosiek")
                .surname("Lichwiarski")
                .password("123123")
                .email("email@gmail.com")
                .birthDate(LocalDate.of(1990, 10, 22))
                .build();

        User user2 = User.builder()
                .login("student")
                .name("Student")
                .surname("Studentowski")
                .password("123123")
                .email("alejaknasiedzaca@gmail.com")
                .birthDate(LocalDate.of(2005, 1, 27))
                .build();

        userService.create(user1);
        userService.create(user2);
        userService.create(admin);

    }
}
