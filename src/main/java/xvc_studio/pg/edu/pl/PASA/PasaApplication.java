package xvc_studio.pg.edu.pl.PASA;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xvc_studio.pg.edu.pl.PASA.files_tool.storage.StorageProperties;
import xvc_studio.pg.edu.pl.PASA.files_tool.storage.StorageService;


@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class PasaApplication {

	public static void main(String[] args) throws InterruptedException {

		Thread.sleep(5000);
		SpringApplication.run(PasaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/*").allowedOrigins("http://localhost:8080").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/*").allowedOrigins("http://127.0.0.1:8084").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/*").allowedOrigins("http://localhost:9300").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/*").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "DELETE");

				registry.addMapping("/files/*").allowedOrigins("http://localhost:8080").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/files/*").allowedOrigins("http://127.0.0.1:8084").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/files/*").allowedOrigins("http://localhost:9300").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/files/*").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "DELETE");

				registry.addMapping("/users/*").allowedOrigins("http://localhost:8080").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/users/*").allowedOrigins("http://127.0.0.1:8084").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/users/*").allowedOrigins("http://localhost:9300").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/users/*").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "DELETE");

				registry.addMapping("/users/*/ads").allowedOrigins("http://localhost:8080").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/users/*/ads").allowedOrigins("http://127.0.0.1:8084").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/users/*/ads").allowedOrigins("http://localhost:9300").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/users/*/ads").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "DELETE");

				registry.addMapping("/users/*/ads/*").allowedOrigins("http://localhost:8080").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/users/*/ads/*").allowedOrigins("http://127.0.0.1:8084").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/users/*/ads/*").allowedOrigins("http://localhost:9300").allowedMethods("POST", "GET", "PUT", "DELETE");
				registry.addMapping("/users/*/ads/*").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "DELETE");


			}
		};
	}


}
