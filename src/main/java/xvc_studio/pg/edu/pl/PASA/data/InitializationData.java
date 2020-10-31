package xvc_studio.pg.edu.pl.PASA.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.ad.service.AdService;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.category.service.CategoryService;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.service.UserService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

@Component
public class InitializationData {

    /**
     * Service for handle Users operations.
     */
    private final UserService userService;

    /**
     * Service for handle Ads operation.
     */
    private final AdService adService;

    /**
     * Service for handle Categories operations.
     */
    private final CategoryService categoryService;

    @Autowired
    public InitializationData(UserService userService, AdService adService, CategoryService categoryService)
    {
        this.userService = userService;
        this.adService = adService;
        this.categoryService = categoryService;
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

        Category automotive = Category.builder()
                .name("Motoryzacja")
                .build();

        Category estate = Category.builder()
                .name("Nieruchomosci")
                .build();

        Category electronics = Category.builder()
                .name("Elektronika")
                .build();

        categoryService.create(automotive);
        categoryService.create(estate);
        categoryService.create(electronics);

        Ad ad1 = Ad.builder()
                .title("Opel Kadet - niezniszczalna jednostka.")
                .description("Sprzedam Opla, 65 KM, 22 sekundy do setki. Ekonomiczny silnik, wykonanie jeszcze niemieckie, rok 1986.")
                .category(automotive)
                .user(user1)
                .build();

        Ad ad2 = Ad.builder()
                .title("Audi A8 D3 - limuzyna")
                .description("Audi 2005 rok, stan idealny, rozrząd wymieniony 2 miesiące temu, przebieg 600k km.")
                .category(automotive)
                .user(user1)
                .build();

        Ad ad3 = Ad.builder()
                .title("Mieszkanie w Gdańsku 56m^2")
                .description("Mieszkanie Gdańsk 56m^2 blisko środmieścia, w Pruszczu gdańskim, cena okazyjna 900 tyś. zł. ")
                .category(estate)
                .user(user1)
                .build();

        Ad ad4 = Ad.builder()
                .title("iPhone 8 plus, wszystko sprawne")
                .description("Sprzedam iPhone 8 plus, tylko wysyłka za przedplatą, stan jak na zdjęciach, używany przez kobiete. ")
                .category(electronics)
                .user(user1)
                .build();

        Ad ad5 = Ad.builder()
                .title("Mercedes G klasa, comfort")
                .description("Mercedes G klasa, comfort, świetnie sie prowadzi, jak droga jest prosta to ma autopilota.")
                .category(automotive)
                .user(user2)
                .build();

        Ad ad6 = Ad.builder()
                .title("Działka ROD, 2 ary, świetna okolica, trochę zalana.")
                .description("Tak jak w opisie, cena wywoławcza 80 tyś. do negocjacji.")
                .category(estate)
                .user(user2)
                .build();

        Ad ad7 = Ad.builder()
                .title("Mieszkanie w Sopocie 16m^2")
                .description("Jeden pokój z kibelkiem i gazówką, blat roboczy, idealny dla młodego małżeństwa z dzieckiem. Cena: 350 000 zł. ")
                .category(estate)
                .user(user2)
                .build();

        Ad ad8 = Ad.builder()
                .title("airPods (nie)oryginalne w cenie oryginalnych")
                .description("Sprzedam sluchawki airPads firmy manta, wyglądają identycznie jak airPodsy firmy apple, aczkolwiek nie ma w nich głośników.")
                .category(electronics)
                .user(user2)
                .build();



        adService.create(ad1);
        adService.create(ad2);
        adService.create(ad3);
        adService.create(ad4);
        adService.create(ad5);
        adService.create(ad6);
        adService.create(ad7);
        adService.create(ad8);



    }
}
