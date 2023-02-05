package solita.academy.bike.exercise;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing single bike station. It holds all of needed data and also is used to represent it in database
 *
 */
@Entity
public class BikeStation {

    @Column
    int FID;
    @Id
    int ID;

    @Column
    String nimi;
    @Column
    String namn;
    @Column
    String name;
    @Column
    String osoite;
    @Column
    String adress;
    @Column
    String kaupunki;
    @Column
    String stad;
    @Column
    String operaattor;
    @Column
    int kapasiteet;
    @Column
    String x;
    @Column
    String y;

    public BikeStation() {
    }

    /**
     * Creates BikeStation from given strings and does some basic validation of given information
     * @param strings Array of Strings containing all Bike Stations information. In order FID, ID, Nimi, Namn, Name, Osoite, Adress, Kaupunki, Stad, Operaattor, Kapasiteet, X, Y
     * @return BikeStation created from given information
     */
    public static BikeStation createBikeStation(String [] strings){
        BikeStation bikeStation=new BikeStation();
        if(strings.length!=13){
            return null;
        }
        bikeStation.setFID(strings [0]);
        if(bikeStation.getFID()<=0){
            return null;
        }
        bikeStation.setID(strings[1]);
        if (bikeStation.getID()<=0){
            return null;
        }
        bikeStation.setNimi(strings[2]);
        if(bikeStation.getNimi()==null||bikeStation.getNimi().isEmpty()){
            return null;
        }
        bikeStation.setNamn(strings[3]);
        if(bikeStation.getNamn()==null||bikeStation.getNamn().isEmpty()){
            return null;
        }
        bikeStation.setName(strings[4]);
        if(bikeStation.getName()==null||bikeStation.getName().isEmpty()){
            return null;
        }
        bikeStation.setOsoite(strings[5]);
        if(bikeStation.getOsoite()==null||bikeStation.getOsoite().isEmpty()){
            return null;
        }
        bikeStation.setAdress(strings[6]);
        if(bikeStation.getAdress()==null||bikeStation.getAdress().isEmpty()){
            return null;
        }
        bikeStation.setKaupunki(strings[7]);
        if(bikeStation.getKaupunki()==null||bikeStation.getKaupunki().isEmpty()){
            return null;
        }
        bikeStation.setStad(strings[8]);
        if(bikeStation.getStad()==null||bikeStation.getStad().isEmpty()){
            return null;
        }
        bikeStation.setOperaattor(strings[9]);
        if(bikeStation.getOperaattor()==null||bikeStation.getOperaattor().isEmpty()){
            return null;
        }
        bikeStation.setKapasiteet(strings[10]);
        if(bikeStation.getKapasiteet()<=0){
            return null;
        }
        bikeStation.setX(strings[11]);
        if(bikeStation.getX()==null||bikeStation.getX().isEmpty()){
            return null;
        }
        bikeStation.setY(strings[12]);
        if(bikeStation.getY()==null||bikeStation.getY().isEmpty()){
            return null;
        }
        return bikeStation;
    }

    public void setFID(String s){
        if(s==null||s.isEmpty()){
            FID=-1;
        }else{
            this.FID=Integer.parseInt(s);
        }
    }

    public void setID(String s){
        if(s==null||s.isEmpty()){
            ID=-1;
        }else{
            this.ID=Integer.parseInt(s);
        }
    }

    public void setKapasiteet(String s){
        if(s==null||s.isEmpty()){
            kapasiteet=-1;
        }else{
            this.kapasiteet=Integer.parseInt(s);
        }
    }

    public int getFID() {
        return FID;
    }

    public void setFID(int FID) {
        this.FID = FID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getOperaattor() {
        return operaattor;
    }

    public void setOperaattor(String operaattor) {
        this.operaattor = operaattor;
    }

    public int getKapasiteet() {
        return kapasiteet;
    }

    public void setKapasiteet(int kapasiteet) {
        this.kapasiteet = kapasiteet;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    /**
     * finds all Nimis in given list of bikeStations
     * @param bikeStations List containing BikeStations you want to find Nimis from
     * @return list containing all Nimis
     */
    public static List<String> getAllNimisInBikeStations(List<BikeStation>bikeStations){
        List<String> stationNimis=new ArrayList<>(bikeStations.size());
        for(BikeStation bikeStation:bikeStations){
            stationNimis.add(bikeStation.getNimi());
        }
        return stationNimis;
    }

    /**
     * gets the number of bike trips originating from this bike station
     * @param bikeTripDatabaseHandler Database containing bike trips
     * @return number of bike trips originating from here
     */
    public long getTotalJourneysFromHere(BikeTripDatabaseHandler bikeTripDatabaseHandler){
        return bikeTripDatabaseHandler.countByDepartureStation(getNimi());
    }

    /**
     * gets the number of bike trips ending to this bike station
     * @param bikeTripDatabaseHandler Database containing bike trips
     * @return number of bike trips to here
     */
    public long getTotalJourneysToHere(BikeTripDatabaseHandler bikeTripDatabaseHandler){
        return bikeTripDatabaseHandler.countByReturnStation(getNimi());
    }

    /**
     * counts average distance travelled here
     * @param bikeTripDatabaseHandler Database containing bike trips
     * @return average distance travelled to here
     */
    public double getAvarageDistanseToHere(BikeTripDatabaseHandler bikeTripDatabaseHandler){
        List<BikeTrip> bikeTrips=bikeTripDatabaseHandler.findAllByReturnStation(getNimi()).toList();
        double distance=0;
        for (BikeTrip bikeTrip:bikeTrips){
            distance+=bikeTrip.getCoveredDistance();
        }
        distance=distance/bikeTrips.size();
        return Math.round(distance/1000d*10d)/10d;
    }

    /**
     * counts average distance travelled from here
     * @param bikeTripDatabaseHandler Database containing bike trips
     * @return average distance travelled from here
     */
    public double getAvarageDistanseFromHere(BikeTripDatabaseHandler bikeTripDatabaseHandler){
        List<BikeTrip> bikeTrips=bikeTripDatabaseHandler.findAllByDepartureStation(getNimi()).toList();
        double distance=0;
        for (BikeTrip bikeTrip:bikeTrips){
            distance+=bikeTrip.getCoveredDistance();
        }
        distance=distance/bikeTrips.size();
        return Math.round(distance/1000d*10d)/10d;
    }




    @Override
    public String toString() {
        return "BikeStation{" +
                "FID=" + FID +
                ", ID=" + ID +
                ", nimi='" + nimi + '\'' +
                ", namn='" + namn + '\'' +
                ", name='" + name + '\'' +
                ", osoite='" + osoite + '\'' +
                ", adress='" + adress + '\'' +
                ", kaupunki='" + kaupunki + '\'' +
                ", stad='" + stad + '\'' +
                ", operaattor='" + operaattor + '\'' +
                ", kapasiteet=" + kapasiteet +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}
