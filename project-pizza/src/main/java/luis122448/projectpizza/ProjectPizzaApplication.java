package luis122448.projectpizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class ProjectPizzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPizzaApplication.class, args);
	}

}
