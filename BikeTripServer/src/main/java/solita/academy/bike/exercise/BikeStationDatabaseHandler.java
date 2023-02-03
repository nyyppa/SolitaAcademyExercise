package solita.academy.bike.exercise;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface BikeStationDatabaseHandler extends CrudRepository<BikeStation,Integer> {
    Streamable<BikeStation> findAll();
}
