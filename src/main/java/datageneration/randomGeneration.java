package datageneration;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class randomGeneration {
    private static int seed;
    private static Random rand;

    randomGeneration(int seed){
        rand = new Random();
        setSeed(seed);
        rand.setSeed(seed);

}

    public void setSeed(int seed) {
        randomGeneration.seed = seed;
    }

    public int getSeed() {
        return seed;
    }

    static int getInt(int min, int max){
        int randInt = rand.nextInt(max-min)+min;
        return randInt;
    }

    static LocalDate getDate(LocalDate to, LocalDate from){
        long days = from.until(to, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = from.plusDays(randomDays);
        return randomDate;
    }

    static double getDouble(){
        double randDouble = rand.nextDouble();
        //round to 2 decimal points
        randDouble = Math.round(randDouble * 100);
        randDouble = randDouble/100;
        return randDouble;
    }

    static double generatePressure(){
        int normalPressureRange = getInt(45,50);
        double pressureFluxuation = getDouble() + normalPressureRange;
        return(pressureFluxuation);
    }

    static double generateFluctuation(double existingData){
        existingData += 20;

        //round to 2 decimal points
        existingData = Math.round(existingData * 100.0);
        existingData = existingData/100;

        return existingData;
    }

    static boolean determineIfError(){
        //generate random errors in data
        int rand = getInt(0,100);
        //percentage chance to happen
        return rand <= 2;
    }

}
