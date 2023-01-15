package solita.academy.bike.exercise;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class BikeTrip implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;

    @Column
    Timestamp departure;
    @Column
    Timestamp returnTime;
    @Column
    int departureStationID;
    @Column
    String departureStation;
    @Column
    String returnStation;
    @Column
    int returnStationID;
    @Column
    double coveredDistance;
    @Column
    int duration;

    static SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public BikeTrip(){

    }
    public BikeTrip(Timestamp departure, Timestamp Return, int departureStationID, String departureStation, String returnStation, int returnStationID, int coveredDistance, int duration) {
        departure = departure;
        this.returnTime = Return;
        departureStationID = departureStationID;
        departureStation = departureStation;
        returnStation = returnStation;
        returnStationID = returnStationID;
        coveredDistance = coveredDistance;
        duration = duration;
    }

    public BikeTrip(String[]strings){
        setDeparture(strings[0]);
        setReturnTime(strings[1]);
        setDepartureStationID(strings[2]);
        setDepartureStation(strings[3]);
        setReturnStationID(strings[4]);
        setReturnStation(strings[5]);
        setCoveredDistance(strings[6]);
        setDuration(strings[7]);
    }
    public void setDeparture(String s){
        try {
            departure =new Timestamp (obj.parse(s).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void setReturnTime(String s){
        try {
            returnTime =new Timestamp(obj.parse(s).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void setDepartureStationID(String s){
        departureStationID =Integer.parseInt(s);
    }
    public void setReturnStationID(String s){
        returnStationID =Integer.parseInt(s);
    }
    public void setCoveredDistance(String s){
        coveredDistance =Double.parseDouble(s);
    }
    public void setDuration(String s){
        duration =Integer.parseInt(s);
    }
    public Timestamp getDeparture() {
        return departure;
    }

    public void setDeparture(Timestamp departure) {
        departure = departure;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }

    public void setReturn(Timestamp aReturn) {
        returnTime = aReturn;
    }

    public int getDepartureStationID() {
        return departureStationID;
    }

    public void setDepartureStationID(int departureStationID) {
        departureStationID = departureStationID;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getReturnStation() {
        return returnStation;
    }

    public void setReturnStation(String returnStation) {
        this.returnStation = returnStation;
    }

    public int getReturnStationID() {
        return returnStationID;
    }

    public void setReturnStationID(int returnStationID) {
        returnStationID = returnStationID;
    }

    public double getCoveredDistance() {
        return coveredDistance;
    }

    public void setCoveredDistance(int coveredDistance) {
        coveredDistance = coveredDistance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        duration = duration;
    }

    @Override
    public String toString() {
        return "BikeTrip{" +
                "Departure=" + departure +
                ", Return=" + returnTime +
                ", DepartureStationID=" + departureStationID +
                ", DepartureStation='" + departureStation + '\'' +
                ", ReturnStation='" + returnStation + '\'' +
                ", ReturnStationID=" + returnStationID +
                ", CoveredDistance=" + coveredDistance +
                ", Duration=" + duration +
                '}';
    }
}
