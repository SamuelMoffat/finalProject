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
    @CsvBindByPosition(position = 5)
    private boolean isError;

    @CsvBindByPosition(position = 4)
    private LocalDate date;

    //this will be filled in for supervised training data and blank for test data
    @CsvBindByPosition(position = 6)
    private String pressureLabel;

    //when performing analysis
    public DataPoint(){
    }
    // when generating dummy data
    DataPoint(List<UkLocation> ukLocationsList){
        generatePressure(false);
        generateLatLong(ukLocationsList);
        generateDate();
    }
    //when generating training data
    DataPoint(List<UkLocation> ukLocationsList, boolean training){
        generatePressure(true);
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

    private void generatePressure(boolean training){
        setPressure(randomGeneration.generatePressure());

        //generate a random error sometimes
        if(randomGeneration.determineIfError()){
            // fluctuate the value if an error
            setPressure(randomGeneration.generateFluctuation(pressure));
            isError = true;
            // if a training point explicitly state if error
            if(training) {
                setPressureLabel("High");
            }
        }
        else{
            isError = false;
            if(training) {
                setPressureLabel("Normal");
            }
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

    public boolean getisError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPressureLabel() {
        return pressureLabel;
    }

    public void setPressureLabel(String pressureLabel) {
        this.pressureLabel = pressureLabel;
    }
}
