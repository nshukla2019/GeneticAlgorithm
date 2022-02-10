package src;

import com.sun.tools.corba.se.idl.InterfaceGen;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pool {
    public List<Organism> generation;
    final int POPULATION_SIZE;
    double population_fitness_total;

    public Pool(int POPULATION_SIZE, List<Organism> generation, int population_fitness_total) {
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.generation = generation;
        this.population_fitness_total = population_fitness_total;
    }



    //takes a Pool and returns the sum of the fitness score of the whole population
    //I thought total fitness score may be more useful
    double calcTotalFitness(Pool population) {
    	double total_fitness = 0;
    	for (Organism organism : population.generation) {
    		total_fitness += organism.fitness_score;
    	}
    	return total_fitness;
    }

    //Takes a population and returns its generation sorted from highest to lowest fitness score;
    List<Organism> sortByFitness(Pool population) {
    	List<Organism> highToLow = new ArrayList<>();
    	highToLow.addAll(population.generation);
    	Collections.sort(highToLow);
    	return highToLow;
    }

    //Takes in a Population and returns a Population with the kept elite as the new generation
    Pool elitism(Pool population, double percentKeep) {

    	List<Organism> highToLow = new ArrayList<>();
    	highToLow.addAll(sortByFitness(population));
    	int amountToKeep = (int) (Math.ceil(population.POPULATION_SIZE*percentKeep));

    	List<Organism> elite = new ArrayList<>();
    	for(int i = 0; i < amountToKeep; i++) {
    		elite.add(highToLow.get(i));
    	}

    	Pool nextGen = new Pool(population.POPULATION_SIZE,elite,0);
    	return nextGen;
    }

    Pool culling(Pool population, double percentYeet) {

    	List<Organism> highToLow = new ArrayList<>();
    	highToLow.addAll(sortByFitness(population));
    	double calcYeet = Math.floor(population.POPULATION_SIZE*(1-percentYeet));
    	int amountToKeep = (int) calcYeet;

    	List<Organism> culled = new ArrayList<>();
    	for(int i = 0; i < amountToKeep; i++) {
    		culled.add(highToLow.get(i));
    	}

    	population.generation = culled;
    	return population;
    }

    // @Josh made these lists global static variables, so you can access them like this
    List<Float> listOfFloatsProvided = createZeroGen.integersProvided;
    List<Piece> listOfPiecesProvided = createZeroGen.listOfPieces;



    /**
     * mutates a generation's organisms on a certain probability
     * @param generationAfterCulling generation produced after culling
     * @param numberToInitiateMutation the integer that must be randomly chosen to allow mutation to occur
     * @param maxOfRandomRange the integer that sets the maximum num to randomly choose rand(0-maxOfRandomRange)
     * @return the mutated generation
     */
    public Pool binsMutation(Pool generationAfterCulling, int numberToInitiateMutation, int maxOfRandomRange) {

        Bins binsNotBeingUsed;
        Pool generationAfterMutated = generationAfterCulling;  // creating new variable to mutate on and return
        Random ran = new Random();

        for (Organism organism : generationAfterCulling.generation) {
            Bins currentFourBins = (Bins) organism; // each organism is 4 bins

            // for each set of Bins (4 bins)
            for(int binIndex = 0; binIndex < currentFourBins.bins.size()-1; binIndex++) {
                ArrayList<Float> currentBin = currentFourBins.bins.get(binIndex);

                // get bins not in the currentBin
                binsNotBeingUsed = Bins.binsNotInUse(binIndex, currentFourBins);

                // for each number in the currentBin
                for(int currentBinIndex = 0; currentBinIndex < currentBin.size(); currentBinIndex++) {
                    int randomNum = ran.nextInt(maxOfRandomRange);  // get random number between 0 and parameter passed

                    if (randomNum == numberToInitiateMutation) {  // randomNum chosen matches the parameter given

                        int randomIndex = -1;
                        float randomNumber = -1;
                        int adjustedIndex = -1;

                        randomIndex = ran.nextInt(39);  // randomly choose an index between 0 and 39

                        // generate random indexes until we get an index that is not in the currentBin
                        while (!Bins.isRandIndexInCurrentBin(randomIndex, binIndex)) {
                            randomIndex = ran.nextInt(39);
                        }

                        int binOfRandomIndex = Bins.returnBinIndexNumber(randomIndex);

                        // index adjustment
                        adjustedIndex = Bins.adjustedIndex(binOfRandomIndex, randomIndex);

                        // get the number at the randomIndex
                        randomNumber = binsNotBeingUsed.bins.get(binOfRandomIndex).get(adjustedIndex);

                        // save current float
                        float currentFloat = currentBin.get(currentBinIndex);

                        // set currentBin's index to randomNumber chosen
                        currentBin.set(currentBinIndex, randomNumber);

                        // set randomNumber's index to current float
                        currentFourBins.bins.get(binOfRandomIndex).set(adjustedIndex, currentFloat);

                    }
                }
            }

        }

        return generationAfterMutated;
    }


}

