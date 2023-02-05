package solita.academy.bike.exercise;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BikeTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeTripApplication.class, args);
	}
	@Bean
	public CommandLineRunner instructions() {
		return (String... args) -> {
			Log logger = LogFactory.getLog(BikeTripApplication.class);
			logger.info("Atte Yliverronen");
			logger.info("Server running");
		};
	}

}
