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
import java.text.Collator;
import java.util.*;

@Controller
public class MyController {
    @Autowired
    BikeTripDatabaseHandler bikeTripDatabaseHandler;

    @Autowired
    BikeStationDatabaseHandler bikeStationDatabaseHandler;
    List<String>stations=new ArrayList<>();
    String [] cvsFilesToImport={"2021-05.csv"};//{"biketrip1.csv","biketrip2.csv","biketrip3.csv","biketrip4.csv","biketrip5.csv"};//{"testi.csv"};//
    String bikeStationFile = "Helsingin_ja_Espoon_kaupunkipyöräasemat_avoin.csv";


    /**
     * Populates the database with some dummy blogs posts and their tags
     */
    @PostConstruct
    private void init(){
        try {
            for(String s:cvsFilesToImport){
                readBikeTripsFromCVSFile(s);
            }
            readBikeStationsFromCVSFile(bikeStationFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        stations=BikeStation.getAllNimisInBikeStations(bikeStationDatabaseHandler.findAll().toList());
        Collections.sort(stations);
        /*
        for(BikeTrip bikeTrip:bikeTripDatabaseHandler.findAll().toList()){
            System.out.println(bikeTrip);
        }
        for(String s:stations){
            for(String ss:stations){
                for(BikeTrip bikeTrip:bikeTripDatabaseHandler.findAllByDepartureStationAndReturnStation(s,ss).toList()){
                    System.out.println(bikeTrip);
                }
            }
        }*/
    }

    @RequestMapping(path="/station",method={RequestMethod.POST, RequestMethod.GET})
    public String station(@RequestParam Optional<String> station, Model model) {
        if(station.isPresent()) {
            BikeStation bikeStation = bikeStationDatabaseHandler.findByNimi(station.get());
            model.addAttribute("name", bikeStation.getNimi());
            model.addAttribute("address", bikeStation.getOsoite());
            model.addAttribute("journeysTo", bikeStation.getTotalJourneysToHere(bikeTripDatabaseHandler));
            model.addAttribute("journeysFrom", bikeStation.getTotalJourneysFromHere(bikeTripDatabaseHandler));
        }
        model.addAttribute("options", stations);
        return "station";
    }

    @RequestMapping(path="/journeys",method={RequestMethod.POST, RequestMethod.GET})
    public String journeys(@RequestParam Optional<String> startStation,@RequestParam Optional<String> stopStation, Model model) {
        BikeTripListHolder bikeTripListHolder=new BikeTripListHolder();
        BikeStationListHolder bikeStationListHolder=new BikeStationListHolder();
        if(startStation.isPresent()&&stopStation.isPresent()){
            bikeTripListHolder.setBikeTrips(bikeTripDatabaseHandler.findAllByDepartureStationAndReturnStation(startStation.get(),stopStation.get()).toList());
            model.addAttribute("form",bikeTripListHolder);
        }else{
            model.addAttribute("form",bikeTripListHolder);
        }
        //model.addAttribute("startStation",stations.get(0));
        //model.addAttribute("stopStation",stations.get(0));
        model.addAttribute("options", stations);
        if(startStation.isPresent()){
            model.addAttribute("startStation",startStation);
        }/*else{
            model.addAttribute("startStation",stations.get(0));
            System.out.println(model.getAttribute("startStation")+"WOLOLOOO");
        }*/
        if(stopStation.isPresent()){
            model.addAttribute("stopStation",stopStation);
        }/*else{
            model.addAttribute("stopStation",stations.get(0));
            System.out.println("WOLOLOOO"+model.getAttribute("stopStation"));
        }*/
        return "journeys";
    }

    private void readBikeStationsFromCVSFile(String filename) throws IOException, URISyntaxException, CsvValidationException {
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
            BikeStation bikeStation=BikeStation.createBikeStation(line);
            if(bikeStation!=null){
                bikeStationDatabaseHandler.save(bikeStation);
            }
        }
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
                //addNonUniqueStations(bikeTrip.departureStation);
                //addNonUniqueStations(bikeTrip.returnStation);
            }
        }
    }

    private void addNonUniqueStations(String station){
        if(!stations.contains(station)){
            stations.add(station);
        }
    }


}
