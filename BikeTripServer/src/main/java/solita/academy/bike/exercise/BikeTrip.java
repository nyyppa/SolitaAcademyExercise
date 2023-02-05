package solita.academy.bike.exercise;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Class representing single bike trip. Contains all the date and is used to represent it in database
 */
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

    int durationAsMinutes;
    double distanceAsKM;

    static SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public BikeTrip(){

    }
    public BikeTrip(Timestamp departure, Timestamp Return, int departureStationID, String departureStation, String returnStation, int returnStationID, int coveredDistance, int duration) {
        this.departure = departure;
        this.returnTime = Return;
        this.departureStationID = departureStationID;
        this.departureStation = departureStation;
        this.returnStation = returnStation;
        this.returnStationID = returnStationID;
        this.coveredDistance = coveredDistance;
        this.duration = duration;
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

    /**
     * Creates bike trip from string array containing needed data. Also does basic data validation
     * @param strings String array containing data needed to create BikeTrip. in order departure time, return time, departure station ID, departure station ID, return station, covered distance, duration
     * @return BikeTrip constructed from given data
     */
    public static BikeTrip createNewBikeTrip(String [] strings){
        if(strings.length!=8){
            System.out.println(strings.length);
            return null;
        }
        BikeTrip bikeTrip=new BikeTrip();
        bikeTrip.setDeparture(strings[0]);
        if(bikeTrip.getDeparture()==null){
            return null;
        }
        bikeTrip.setReturnTime(strings[1]);
        if(bikeTrip.getReturnTime()==null){
            return null;
        }
        bikeTrip.setDepartureStationID(strings[2]);
        if(bikeTrip.getDepartureStationID()<=0){
            return null;
        }
        bikeTrip.setDepartureStation(strings[3]);
        if(bikeTrip.getDepartureStation()==null||bikeTrip.getDepartureStation().isEmpty()){
            return null;
        }
        bikeTrip.setReturnStationID(strings[4]);
        if(bikeTrip.getReturnStationID()<=0){
            return null;
        }
        bikeTrip.setReturnStation(strings[5]);
        if(bikeTrip.getReturnStation()==null||bikeTrip.getReturnStation().isEmpty()){
            return null;
        }
        bikeTrip.setCoveredDistance(strings[6]);
        if(bikeTrip.getCoveredDistance()<=10){
            return null;
        }
        bikeTrip.setDuration(strings[7]);
        if(bikeTrip.getDuration()<=10){
            return null;
        }
        bikeTrip.durationAsMinutes= bikeTrip.getDurationAsMinutes();
        bikeTrip.distanceAsKM= bikeTrip.getDistanceAsKM();
        return bikeTrip;
    }
    public void setDeparture(String s){
        try {
            departure =new Timestamp (obj.parse(s).getTime());
        } catch (ParseException e) {
            //throw new RuntimeException(e);
        }
    }
    public void setReturnTime(String s){
        try {
            returnTime =new Timestamp(obj.parse(s).getTime());
        } catch (ParseException e) {
            //throw new RuntimeException(e);
        }
    }
    public void setDepartureStationID(String s){
        if(s==null||s.isEmpty()){
            departureStationID=-1;
        }else{
            departureStationID =Integer.parseInt(s);
        }
    }
    public void setReturnStationID(String s){
        if(s==null||s.isEmpty()){
            returnStationID=-1;
        }else{
            returnStationID =Integer.parseInt(s);
        }
    }
    public void setCoveredDistance(String s){
        if(s==null||s.isEmpty()){
            coveredDistance=-1;
        }else{
            coveredDistance =Double.parseDouble(s);
        }
    }
    public void setDuration(String s){
        if(s==null||s.isEmpty()){
            duration=-1;
        }else{
            duration =Integer.parseInt(s);
        }
    }
    public Timestamp getDeparture() {
        return departure;
    }

    public void setDeparture(Timestamp departure) {
        this.departure = departure;
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
        this.departureStationID = departureStationID;
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
        this.returnStationID = returnStationID;
    }

    public double getCoveredDistance() {
        return coveredDistance;
    }

    public void setCoveredDistance(int coveredDistance) {
        this.coveredDistance = coveredDistance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDurationAsMinutes(){
        return Math.round(duration/60);
    }
    public double getDistanceAsKM(){
        return Math.round(coveredDistance/1000d*10d)/10d;
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
