package xvc_studio.pg.edu.pl.PASA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xvc_studio.pg.edu.pl.PASA.ad.entity.Ad;
import xvc_studio.pg.edu.pl.PASA.ad.service.AdService;
import xvc_studio.pg.edu.pl.PASA.category.entity.Category;
import xvc_studio.pg.edu.pl.PASA.category.service.CategoryService;
import xvc_studio.pg.edu.pl.PASA.user.entity.User;
import xvc_studio.pg.edu.pl.PASA.user.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Optional;


/**
 * Component for interaction with user using command line. Components annotated with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    private UserService userService;
    private CategoryService categoryService;
    private AdService adService;

    private String login;
    private String name;
    private String password;
    private String id;
    private BufferedReader reader = null;
    private User currentUser = null;

    @Autowired
    public CommandLine(UserService userService, AdService adService, CategoryService categoryService) {
        this.userService = userService;
        this.adService = adService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        boolean work = true;

        printLegend();

        while(work)
        {

            reader = new BufferedReader(new InputStreamReader(System.in));
            if(currentUser == null)
            {
                System.out.println("At first, you should log in. Have you account? yes/no");
                String answer = reader.readLine().toLowerCase();
                if(answer.equals("yes"))
                {
                    System.out.println("Put login: ");
                    login = reader.readLine();
                    System.out.println("Put password: ");
                    password = reader.readLine();

                    Optional<User> usr = userService.find(login, password);

                    if(usr.isPresent())
                        currentUser = usr.get();
                    else
                        System.out.println("Incorrect password or login. Try again.");
                }
                else
                {
                    System.out.println("Now you can create new account, please fill fields. \n");
                    currentUser = createUser();
                }
            }
            else
            {
                System.out.println("\n --------- Write help for commands. --------- \n");
                String cmd = reader.readLine();
                cmd = cmd.toLowerCase();
                switch (cmd) {

                    case "exit":
                        work = false;
                        break;

                    case "add_user":
                        createUser();
                        break;

                    case "add_category":
                        createCategory();
                        break;

                    case "add_ad":
                        createAd();
                        break;

                    case "del_user":
                        deleteUser();
                        break;

                    case "del_category":
                        deleteCategory();
                        break;

                    case "del_ad":
                        deleteAd();
                        break;

                    case "categories":
                        printCategories();
                        break;

                    case "users":
                        printUsers();
                        break;

                    case "ads":
                        printAds();
                        break;

                    case "user_ads":
                        printUserAds();
                        break;

                    case "help":
                        printLegend();
                        break;


                }
            }
        }

    }

    private void printLegend() {
        System.out.println("Add new User - Write add_user. \n" +
                "Add new Category - Write add_category. \n" +
                "Add new Ad - Write add_ad. \n" +
                "Delete existing User - Write del_user.\n" +
                "Delete existing Category - Write del_category.  \n" +
                "Delete existing Ad - Write del_ad.  \n" +
                "Show all categories - Write categories. \n" +
                "Show all Users - Write users. \n" +
                "Show all Ads - Write ads. \n" +
                "Show specific user's ads - Write user_ads. \n" +
                "Need heelp? - Write help. \n" +
                "For exit app - Write exit. \n");
    }

    private void printUserAds() throws IOException {
        System.out.println("Put login to show user's ads.");
        login = reader.readLine();

        userService.find(login).ifPresentOrElse(
                original ->{
                    adService.findAll(original).forEach(System.out::println);

                },
                () -> {
                    System.out.println("User with this login doesn't exist.");
                }
        );
    }

    private void printAds() {
        System.out.println("Actual announcements: \n");
        adService.findAll().forEach(System.out::println);
    }

    private void printUsers() {
        System.out.println("Registered Users: \n");
        userService.findAll().forEach(System.out::println);
    }

    private void printCategories() {
        System.out.println("Available categories: \n");
        categoryService.findAll().forEach(System.out::println);
    }

    private void deleteAd() throws IOException {
        System.out.println("Put ad's id to delete ad. ");
        System.out.println("Available ads: \n");
        adService.findAll().forEach(System.out::println);
        System.out.println("Id: ");
        id = reader.readLine();
        adService.find(Long.parseLong(id)).ifPresentOrElse(
                original ->{
                    adService.delete(Long.parseLong(id));
                    System.out.println("Ad number " + id + " removed.");
                },
                () -> {
                    System.out.println("That ad doesn't exist.");
                }
        );
    }

    private void deleteCategory() throws IOException {
        System.out.println("Put category name to delete category. ");
        System.out.println("Available categories: \n");
        categoryService.findAll().forEach(System.out::println);
        System.out.println("Name: ");
        name = reader.readLine();
        categoryService.find(name).ifPresentOrElse(
                original ->{
                    categoryService.delete(original.getId());
                    System.out.println("Category " + name + " deleted.");
                },
                () -> {
                    System.out.println("Category " + name + " doesn't exist.");
                }
        );
    }

    private void deleteUser() throws IOException {

        System.out.println("Put user login to delete user. ");
        System.out.println("Existing users: \n");
        userService.findAll().forEach(System.out::println);
        System.out.println("Login: ");
        login = reader.readLine();
        userService.find(login).ifPresentOrElse(
                original ->{
                    userService.delete(login);
                    System.out.println("User " + login + " deleted.");
                },
                () -> {
                    System.out.println("User with this login doesn't exist.");
                }
        );
    }


    private void createAd() throws IOException {
        System.out.println("Title:");
        String title = reader.readLine();
        System.out.println("Description:");
        String description = reader.readLine();
        System.out.println("Available categories: \n");
        categoryService.findAll().forEach(System.out::println);
        System.out.println("Choose one category name:");
        String categoryName = reader.readLine();
        Optional<Category> tmp = categoryService.find(categoryName);
        Category category = null;

        if(tmp.isPresent())
            category = tmp.get();

        if(category!=null)
        {
            Ad ad = Ad.builder()
                    .title(title)
                    .description(description)
                    .category(category)
                    .user(currentUser)
                    .build();

            adService.create(ad);
        }
    }

    private void createCategory() throws IOException {

        System.out.println("Name:");
        name = reader.readLine();


        Category cat = Category.builder()
                .name(name)
                .build();

        categoryService.create(cat);
    }

    private User createUser() throws IOException {


        System.out.println("Login:");
        login = reader.readLine();
        System.out.println("Name:");
        name = reader.readLine();
        System.out.println("Surname:");
        String surname = reader.readLine();
        System.out.println("Password:");
        password = reader.readLine();
        System.out.println("Email:");
        String email = reader.readLine();
        System.out.println("Birth year: ");
        String year = reader.readLine();
        System.out.println("Birth month: ");
        String month = reader.readLine();
        System.out.println("Birth day: ");
        String day = reader.readLine();

        User user = User.builder()
                .login(login)
                .name(name)
                .surname(surname)
                .password(password)
                .email(email)
                .birthDate(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)))
                .build();

        userService.create(user);
        return user;
    }
}
