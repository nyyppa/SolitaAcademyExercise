package solita.academy.bike.exercise;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface BikeTripDatabaseHandler extends CrudRepository<BikeTrip,Integer> {

    Streamable<BikeTrip> findByDepartureStationContaining(String search);
}
