

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int POPULATION_SIZE = 10;

        createZeroGen zeroGen = new createZeroGen();

        Pool zeroGeneration = new Pool(POPULATION_SIZE, null,0);
        zeroGeneration = zeroGen.readFile("sampleFiles/puzzle1", 1, POPULATION_SIZE);

        System.out.println("hi");

    }

}

