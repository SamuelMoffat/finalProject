package datageneration;

import com.opencsv.bean.CsvBindByPosition;
import gis.UkLocation;

import java.time.LocalDate;
import java.util.List;

public class DataPoint {


    @CsvBindByPosition(position = 0)
    private String locationName;

    //measured in psi
    @CsvBindByPosition(position = 1)
    private double pressure;

    @CsvBindByPosition(position = 2)
    private float latitude;

    @CsvBindByPosition(position = 3)
    private float longitude;

    private boolean isError;

    @CsvBindByPosition(position = 4)
    private LocalDate date;

    DataPoint(List<UkLocation> ukLocationsList){
        generatePressure();
        generateLatLong(ukLocationsList);
        generateDate();
    }

    private void generateDate() {
        // set bounds for random dates
        LocalDate from = LocalDate.of(2016, 1, 1);
        LocalDate to = LocalDate.of(2021, 1, 1);
        setDate(randomGeneration.getDate(to,from));
    }

    private void generateLatLong(List<UkLocation> ukLocationsList) {
        int listSize = ukLocationsList.size();
        UkLocation randomLocation = ukLocationsList.get(randomGeneration.getInt(0,listSize));

        setLatitude(randomLocation.getLat());
        setLongitude(randomLocation.getLongitude());
        setLocationName(randomLocation.getName());
    }

    private void generatePressure(){
        setPressure(randomGeneration.generatePressure());

        //generate a random error sometimes
        if(randomGeneration.determineIfError()){
            setPressure(randomGeneration.generateFluctuation(pressure));
            isError = true;
        }
        else{
            isError = false;
        }
    }


    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getPressure() {
        return pressure;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public boolean isError() {
        return isError;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
