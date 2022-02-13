package src;
import java.io.FileNotFoundException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
//        List<Piece> middle = new ArrayList<Piece> ();
//        Piece middlePiece = new Piece(TYPE.WALL, 3, 3, 1);
//
//        middle.add(middlePiece);
//		Tower tower = new Tower(new Piece(TYPE.LOOKOUT, 2, 3, 3), middle, new Piece(TYPE.DOOR, 4, 3, 1), -1);
//
//        boolean checkTower = tower.validTower();

        Clock clock = Clock.systemDefaultZone();
        int POPULATION_SIZE = 100;
        int puzzle = 2;

        createZeroGen zeroGen = new createZeroGen();
        Pool zeroGeneration = zeroGen.readFile("sampleFiles/puzzle2", puzzle, POPULATION_SIZE);

        while (Pool.numValidTowers(zeroGeneration) < 2) {
            zeroGeneration = zeroGen.readFile("sampleFiles/puzzle2", puzzle, POPULATION_SIZE);
        }

        long timeToRun = 30000;
        long endTime = clock.millis() + timeToRun;
        long timeRemaining = endTime - clock.millis();
        double maxScore = 0;
        int maxScoreGen = 0;
        Pool nextGen = zeroGeneration;
        int numberOfGens = 0;


        while (timeRemaining > 0) {

            if (numberOfGens == 1) {
                System.out.println();
            }
            nextGen = zeroGeneration.GeneticAlgorithm(nextGen,1,.2,.3,1,puzzle,timeToRun,0);

            while (Pool.numValidTowers(nextGen) < 2) {
                nextGen = zeroGeneration.GeneticAlgorithm(nextGen,1,.2,.3,1,puzzle,timeToRun,0);
            }

            timeRemaining = endTime - clock.millis();

            for(Organism o : nextGen.generation) {
                if(o.fitness_score > maxScore) {
                    maxScore = o.fitness_score;
                    maxScoreGen = numberOfGens;
                }
            }

            System.out.println("Number of Gens: " + numberOfGens);
            System.out.println("Max Score: " + maxScore);
            System.out.println("Generation of Max Score: " + maxScoreGen);
            numberOfGens++;

            System.out.println();
        }

//        System.out.println("first gen total fitness");
//		System.out.println(zeroGeneration.population_fitness_total);
//		System.out.println("newest gen total fitness");
//		System.out.println(nextGen.population_fitness_total);

    }

}

