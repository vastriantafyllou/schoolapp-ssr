package gr.aueb.cf.schoolapp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Schoolapp2Application {

	public static void main(String[] args) {

		SpringApplication.run(Schoolapp2Application.class, args);
	}

}
