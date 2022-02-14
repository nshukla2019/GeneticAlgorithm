
import java.io.FileNotFoundException;
import java.time.Clock;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Clock clock = Clock.systemDefaultZone();
        int POPULATION_SIZE = 100;
        int puzzle = Integer.parseInt(args[0]);
        String fileName = args[1];
        long timeToRun = Integer.parseInt(args[2]);


        createZeroGen zeroGen = new createZeroGen();
        Pool zeroGeneration = zeroGen.readFile(fileName, puzzle, POPULATION_SIZE);

        if (puzzle == 2) {
            while (Pool.numValidTowers(zeroGeneration) < 2) {
                zeroGeneration = zeroGen.readFile(fileName, puzzle, POPULATION_SIZE);
            }
        }


        long endTime = clock.millis() + timeToRun;
        long timeRemaining = endTime - clock.millis();
        double maxScore = 0;
        int maxScoreGen = 0;
        Pool nextGen = zeroGeneration;
        int numberOfGens = 0;

        while (timeRemaining > 0) {
        	
        	 Pool prevGen = nextGen;
        	 nextGen = zeroGeneration.GeneticAlgorithm(nextGen,1,.2,.3,1,puzzle,timeToRun,0);
        	 
             if (puzzle == 2) {
                 while (Pool.numValidTowers(nextGen) < 2) {
                	 nextGen = zeroGeneration.GeneticAlgorithm(prevGen,1,.2,.3,1,puzzle,timeToRun,numberOfGens-1);  
                	 nextGen.assignFitnessScores(nextGen, 1, puzzle);
         			 nextGen.calcTotalFitness(nextGen);
                 }
             }

             

            timeRemaining = endTime - clock.millis();
            double previousMax =  maxScore;
            for(Organism o : nextGen.generation) {
                if(o.fitness_score > maxScore) {
                    maxScore = o.fitness_score;
                    maxScoreGen = numberOfGens;
                }
            }

            if (maxScore != previousMax) {
                System.out.println("Number of Gens: " + numberOfGens);
                System.out.println("Max Score: " + maxScore);
                System.out.println("Generation of Max Score: " + maxScoreGen);
                System.out.println();
            }

            numberOfGens++;

        }

        System.out.println("Number of Gens: " + numberOfGens);
        System.out.println("Max Score: " + maxScore);
        System.out.println("Generation of Max Score: " + maxScoreGen);

    }

}

