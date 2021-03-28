package main.java.datageneration;

public class DataPoint {

    //measured in psi
    private double pressure;
    private String date;
    private int latitude;
    private int longitude;
    private boolean isError;

    DataPoint(){
        generatePressure();
    }

    private void generatePressure(){
        setPressure(randomGeneration.generatePressure());

        //generate a random error sometimes
        if(randomGeneration.determineIfError()){
            setPressure(randomGeneration.generateFluctuation(pressure));
            isError = true;
        }
    }


    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getPressure() {
        return pressure;
    }

    public boolean isError() {
        return isError;
    }
}
