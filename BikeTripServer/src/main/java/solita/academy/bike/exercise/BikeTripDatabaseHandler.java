package solita.academy.bike.exercise;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface BikeTripDatabaseHandler extends CrudRepository<BikeTrip,Integer> {

    Streamable<BikeTrip> findAllByDepartureStation(String search);

    Streamable<BikeTrip> findAllByDepartureStationAndReturnStation(String search,String s);

    long countByDepartureStation(String name);
    long countByReturnStation(String name);
}
