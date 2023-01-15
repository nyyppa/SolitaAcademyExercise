package solita.academy.bike.exercise;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

@RestController
public class MyController {
    @Autowired
    BikeTripDatabaseHandler bikeTripDatabaseHandler;


    /**
     * Populates the database with some dummy blogs posts and their tags
     */
    @PostConstruct
    private void init(){
        try {
            test();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }


    private void test() throws IOException, URISyntaxException, CsvValidationException {
        Path path = Paths.get(
                ClassLoader.getSystemResource("testi.csv").toURI());
        Reader reader = Files.newBufferedReader(path);
		CSVParser parser = new CSVParserBuilder()
				.withSeparator(',')
				.withIgnoreQuotations(true)
				.build();
		CSVReader csvReader = new CSVReaderBuilder(reader)
				.withSkipLines(1)
				.withCSVParser(parser)
				.build();
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            BikeTrip bikeTrip=new BikeTrip(line);
            System.out.println(bikeTrip);
            bikeTripDatabaseHandler.save(bikeTrip);
        }
    }


}
