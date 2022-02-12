package src;
import java.io.FileNotFoundException;
import java.time.Clock;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
		Clock clock = Clock.systemDefaultZone();
        int POPULATION_SIZE = 10;

        createZeroGen zeroGen = new createZeroGen();
        Pool zeroGeneration = zeroGen.readFile("sampleFiles/puzzle1", 1, POPULATION_SIZE);

        long timeToRun = 30000;
//        long endTime = clock.millis() + timeToRun;
//        long timeRemaining = endTime - clock.millis();

		Pool finalGen = finalGen = zeroGeneration.GeneticAlgorithm(zeroGeneration,2,.2,.3,1,1,timeToRun,0);


		System.out.println("first gen total fitness");
		System.out.println(zeroGeneration.population_fitness_total);
		System.out.println("newest gen total fitness");
		System.out.println(finalGen.population_fitness_total);

    }

}

