package src;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int POPULATION_SIZE = 10;

        createZeroGen zeroGen = new createZeroGen();
        Pool zeroGeneration = zeroGen.readFile("sampleFiles/puzzle1", 1, POPULATION_SIZE);

        Pool mutatedGeneration = zeroGeneration.binsMutation(zeroGeneration, 1, 2);

        System.out.println("hi");

    }

}

