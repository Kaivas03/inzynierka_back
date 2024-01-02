package pl.sawiak_company.sok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("pl.sawiak_company")
@ComponentScan("pl.sawiak_company")
@EnableCaching
@SpringBootApplication
public class SokApplication {
	public static void main(String[] args) {
		SpringApplication.run(SokApplication.class, args);
	}
}