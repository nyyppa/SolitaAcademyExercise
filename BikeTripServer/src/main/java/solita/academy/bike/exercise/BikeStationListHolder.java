package solita.academy.bike.exercise;

import java.util.ArrayList;
import java.util.List;

public class BikeStationListHolder {
    private List<BikeStation> BikeStations;

    public BikeStationListHolder() {
        BikeStations=new ArrayList<>();
    }

    public List<BikeStation> getBikeStations() {
        return BikeStations;
    }

    public void setBikeStations(List<BikeStation> BikeStations) {
        this.BikeStations = BikeStations;
    }
}
