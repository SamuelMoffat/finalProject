package gis;

import com.opencsv.bean.CsvBindByName;

public class UkLocation {
    @CsvBindByName
    float lat;
    @CsvBindByName
    float longitude;
    //this is the name of location
    @CsvBindByName(column = "wd17nm")
    String name;


    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
