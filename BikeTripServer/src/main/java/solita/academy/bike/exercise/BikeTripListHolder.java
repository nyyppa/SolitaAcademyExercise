package solita.academy.bike.exercise;
import java.util.ArrayList;
import java.util.List;

public class BikeTripListHolder {
    private List <BikeTrip>bikeTrips;

    public BikeTripListHolder() {
        bikeTrips=new ArrayList<>();
    }

    public List<BikeTrip> getBikeTrips() {
        return bikeTrips;
    }

    public void setBikeTrips(List<BikeTrip> bikeTrips) {
        this.bikeTrips = bikeTrips;
    }
}
