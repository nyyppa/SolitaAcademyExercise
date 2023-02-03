package solita.academy.bike.exercise;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.util.*;

@Controller
public class MyController {
    @Autowired
    BikeTripDatabaseHandler bikeTripDatabaseHandler;

    List<String>stations=new ArrayList<>();
    String [] cvsFilesToImport={"biketrip1.csv","biketrip2.csv","biketrip3.csv","biketrip4.csv","biketrip5.csv"};

    /**
     * Populates the database with some dummy blogs posts and their tags
     */
    @PostConstruct
    private void init(){
        try {
            for(String s:cvsFilesToImport){
                readBikeTripsFromCVSFile(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        List<BikeTrip>list=bikeTripDatabaseHandler.findAllByDepartureStation("Viiskulma").toList();
        System.out.println("hello");
        for(BikeTrip bikeTrip:list){
            System.out.println(bikeTrip);
        }
        list=bikeTripDatabaseHandler.findAllByDepartureStationAndReturnStation("Viiskulma","Hernesaarenranta").toList();
        System.out.println("hello");
        for(BikeTrip bikeTrip:list){
            System.out.println(bikeTrip);
        }

    }

    @RequestMapping(path="/jotain",method={RequestMethod.POST, RequestMethod.GET})
    public String populateList(@RequestParam Optional<String> startStation,@RequestParam Optional<String> stopStation, Model model) {
        BikeTripListHolder bikeTripListHolder=new BikeTripListHolder();
        if(startStation.isPresent()&&stopStation.isPresent()){
            bikeTripListHolder.setBikeTrips(bikeTripDatabaseHandler.findAllByDepartureStationAndReturnStation(startStation.get(),stopStation.get()).toList());
            model.addAttribute("form",bikeTripListHolder);
        }else{
            model.addAttribute("form",bikeTripListHolder);
        }
        model.addAttribute("options", stations);
        if(startStation.isPresent()){
            model.addAttribute("startStation",startStation);

        }
        if(stopStation.isPresent()){
            model.addAttribute("stopStation",stopStation);
        }
        return "jotain";
    }

    private void readBikeStationsFromCVSFile(String filename) throws IOException, URISyntaxException, CsvValidationException {

    }

    private void readBikeTripsFromCVSFile(String filename) throws IOException, URISyntaxException, CsvValidationException {
        Path path = Paths.get(
                ClassLoader.getSystemResource(filename).toURI());
        Reader reader = Files.newBufferedReader(path);
		CSVParser parser = new CSVParserBuilder()
				.withSeparator(',')
				.withIgnoreQuotations(false)
				.build();
		CSVReader csvReader = new CSVReaderBuilder(reader)
				.withSkipLines(1)
				.withCSVParser(parser)
				.build();
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            BikeTrip bikeTrip=BikeTrip.createNewBikeTrip(line);
            if(bikeTrip!=null){
                bikeTripDatabaseHandler.save(bikeTrip);
                addNonUniqueStations(bikeTrip.departureStation);
                addNonUniqueStations(bikeTrip.returnStation);
            }
        }
        for(String s:stations){
            System.out.println(s);
        }
    }

    private void addNonUniqueStations(String station){
        if(!stations.contains(station)){
            stations.add(station);
        }
    }


}
