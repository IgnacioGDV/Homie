// archivo: BackApplication.java
package es.ufv.homie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "es.ufv.homie")
@EntityScan(basePackages = "es.ufv.homie.model")
@EnableJpaRepositories(basePackages = "es.ufv.homie.repository")
public class BackApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}
}

