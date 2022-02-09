package src;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int POPULATION_SIZE = 10;

        createZeroGen zeroGen = new createZeroGen();

        Pool zeroGeneration = new Pool(POPULATION_SIZE, null,0);
        zeroGeneration = zeroGen.readFile("sampleFiles/puzzle2", 2, POPULATION_SIZE);

        System.out.println("hi");

    }

}

