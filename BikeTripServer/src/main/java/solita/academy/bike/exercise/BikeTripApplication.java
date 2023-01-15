package solita.academy.bike.exercise;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BikeTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeTripApplication.class, args);
	}
	@Bean
	public CommandLineRunner instructions() {
		return (String... args) -> {
			Log logger = LogFactory.getLog(BikeTripApplication.class);
			logger.info("Atte Yliverronen and Jesse Stenroth");
			logger.info("username:admin password:admin");
			logger.info("INSTRUCTIONS");
			logger.info("------------");
			logger.info("GET specific blog");
			logger.info("	curl http://localhost:8080/blogs/1");
			logger.info("GET all blogs");
			logger.info("	curl http://localhost:8080/blogs/");
			logger.info("DELETE blog");
			logger.info("	curl -X DELETE http://localhost:8080/delete/1");
			logger.info("POST new blog");
			logger.info("	curl -H \"Accept: application/json\" -H \"Content-type: application/json\" -X POST -d \"{\\\"author\\\":\\\"value\\\",\\\"text\\\":\\\"moiiiii\\\"}\" http://localhost:8080/save");
			logger.info("POST new blog with tags");
			logger.info("	curl -H \"Accept: application/json\" -H \"Content-type: application/json\" -X POST -d \"{\\\"author\\\":\\\"value\\\",\\\"text\\\":\\\"heii\\\",\\\"tags\\\":[{\\\"tagId\\\":\\\"jorma\\\"},{\\\"tagId\\\":\\\"pena\\\"}]}\" http://localhost:8080/save");
			logger.info("POST modified blog");
			logger.info("	curl -H \"Accept: application/json\" -H \"Content-type: application/json\" -X POST -d \"{\\\"id\\\":1,\\\"author\\\":\\\"value\\\",\\\"text\\\":\\\"heii\\\"}\" http://localhost:8080/save");
			logger.info("GET blogs with search word");
			logger.info("	curl http://localhost:8080/search/word");
			logger.info("GET all tags in database");
			logger.info("	curl http://localhost:8080/tags/");
			logger.info("POST comment for a blog");
			logger.info("	curl -H \"Accept: application/json\" -H \"Content-type: application/json\" -X POST -d \"{\\\"author\\\":\\\"value\\\",\\\"text\\\":\\\"heii\\\"}\" http://localhost:8080/comment/1");
			logger.info("GET comments for blog");
			logger.info("	curl http://localhost:8080/comment/1");
			logger.info("Find blogs with given tag");
			logger.info("	curl -H \"Accept: application/json\" -H \"Content-type: application/json\" -X POST -d \"{\\\"tagId\\\":\\\"jorma\\\"}\" http://localhost:8080/blogsWithTag");
		};
	}

}
