package src;
import java.io.FileNotFoundException;
import java.time.Clock;
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
    	Clock clock = Clock.systemDefaultZone();
        int POPULATION_SIZE = 10;

        createZeroGen zeroGen = new createZeroGen();
        Pool zeroGeneration = zeroGen.readFile("sampleFiles/puzzle2", 2, POPULATION_SIZE);

        Pool mutatedGeneration = zeroGeneration.towersMutation(zeroGeneration, 1, 2);

        long duration = 30000;
    	long endTime= clock.millis() + duration;

        System.out.println("hi");

    }

}

