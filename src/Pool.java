package src;

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


    /**
     * 
     * @param population
     * @return the sum of the fitness score of the whole population
     */
    //I thought total fitness score may be more useful
    double calcTotalFitness(Pool population) {
    	double total_fitness = 0;
    	for (Organism organism : population.generation) {
    		total_fitness += organism.fitness_score;
    	}
    	return total_fitness;
    }
    
    /**
     * 
     * @param population
     * @return its generation sorted from highest to lowest fitness score;
     */
    List<Organism> sortByFitness(Pool population) {
    	List<Organism> highToLow = new ArrayList<>();
    	highToLow.addAll(population.generation);
    	//used a comparator to sort the given pool generation from largest to smallest fitness score
    	Collections.sort(highToLow);
    	return highToLow;
    }
    
    /**
     * 
     * @param population
     * @param percentKeep a double from 0-1 representing the percent of population that is elite
     * @return a Population with the kept elite as the new generation
     */
    Pool elitism(Pool population, double percentKeep) {
    	//creates a new list from highest to lowest fitness
    	List<Organism> highToLow = new ArrayList<>();
    	highToLow.addAll(sortByFitness(population));
    	int amountToKeep = (int) (Math.ceil(population.POPULATION_SIZE*percentKeep));
    	//takes top percent of population and uses that to create a new Pool
    	List<Organism> elite = new ArrayList<>();
    	for(int i = 0; i < amountToKeep; i++) {
    		elite.add(highToLow.get(i));
    	}

    	Pool nextGen = new Pool(population.POPULATION_SIZE,elite,0);
    	return nextGen;
    }
    
    /**
     * 
     * @param population
     * @param percentYeet a double from 0-1 representing the percent of population that will be removed
     */
    void culling(Pool population, double percentYeet) {
    	//orders to current generation high to low and calculates how many to yeet
    	List<Organism> highToLow = new ArrayList<>();
    	highToLow.addAll(sortByFitness(population));
    	double calcYeet = Math.floor(population.POPULATION_SIZE*(1-percentYeet));
    	int amountToKeep = (int) calcYeet;
    	
    	//adds the number or organisms we want to keep to a new culled list
    	List<Organism> culled = new ArrayList<>();
    	for(int i = 0; i < amountToKeep; i++) {
    		culled.add(highToLow.get(i));
    	}
    	//makes the culled list the generation of the current pool
    	population.generation = culled;
    }

//    // @Josh made these lists global static variables, so you can access them like this
//    List<Float> listOfFloatsProvided = createZeroGen.integersProvided;
//    List<Piece> listOfPiecesProvided = createZeroGen.listOfPieces;



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

                        adjustedIndex = Bins.adjustedIndex(binOfRandomIndex, randomIndex); // index adjustment

                        randomNumber = binsNotBeingUsed.bins.get(binOfRandomIndex).get(adjustedIndex); // get the number at the randomIndex

                        float currentFloat = currentBin.get(currentBinIndex); // save current float

                        currentBin.set(currentBinIndex, randomNumber); // set currentBin's index to randomNumber chosen

                        currentFourBins.bins.get(binOfRandomIndex).set(adjustedIndex, currentFloat); // set randomNumber's index to current float

                    }
                }
            }

        }

        return generationAfterMutated;
    }


    /**
     * mutates a generation's organisms on a certain probability
     * @param generationAfterCulling generation produced after culling
     * @param numberToInitiateMutation the integer that must be randomly chosen to allow mutation to occur
     * @param maxOfRandomRange the integer that sets the maximum num to randomly choose rand(0-maxOfRandomRange)
     * @return the mutated generation
     */
    public Pool towersMutation(Pool generationAfterCulling, int numberToInitiateMutation, int maxOfRandomRange) {
        Pool generationAfterMutated = generationAfterCulling;  // creating new variable to mutate on and return
        Random ran = new Random();

        for (Organism organism : generationAfterCulling.generation) {
            Tower currentTower = (Tower) organism; // each organism is 1 tower

            // probability roll for top piece
            int randomNumForTopPiece = ran.nextInt(maxOfRandomRange);
            if (randomNumForTopPiece == numberToInitiateMutation) {  // randomNum chosen matches the parameter given

                List<Piece> piecesNotInUse = Piece.piecesNotInUse(currentTower); // get the listOfPieces that are not in the currentTower
                int randomIndex = ran.nextInt(piecesNotInUse.size());  // randomly choose an index between 0 and the size of listOfPieceNotInUse
                currentTower.top = piecesNotInUse.get(randomIndex); // replace the top piece with the piece at the randomIndex
            }

            // probability roll for bottom piece
            int randomNumForBottomPiece = ran.nextInt(maxOfRandomRange);
            if (randomNumForBottomPiece == numberToInitiateMutation) {  // randomNum chosen matches the parameter given

                List<Piece> piecesNotInUse = Piece.piecesNotInUse(currentTower); // get the listOfPieces that are not in the currentTower
                int randomIndex = ran.nextInt(piecesNotInUse.size());  // randomly choose an index between 0 and the size of listOfPieceNotInUse
                currentTower.bottom = piecesNotInUse.get(randomIndex); // replace the bottom piece with the piece at the randomIndex
            }

            // probability roll for each middle piece in currentTower
            for (Piece currentMiddlePiece : currentTower.middle_pieces) {
                int randomNumForCurrentMiddlePiece = ran.nextInt(maxOfRandomRange);
                if (randomNumForCurrentMiddlePiece == numberToInitiateMutation) {  // randomNum chosen matches the parameter given

                    List<Piece> piecesNotInUse = Piece.piecesNotInUse(currentTower); // get the listOfPieces that are not in the currentTower
                    int randomIndex = ran.nextInt(piecesNotInUse.size());  // randomly choose an index between 0 and the size of listOfPieceNotInUse
                    int indexOfCurrentPiece = currentTower.middle_pieces.indexOf(currentMiddlePiece);
                    currentTower.middle_pieces.set(indexOfCurrentPiece, piecesNotInUse.get(randomIndex)); // set the currentMiddlePiece piece with the piece at the randomIndex
                }
            }

        }

        return generationAfterMutated;
    }


    Pool GeneticAlgorithm(Pool population, double perElite, double perCull, long timeRemaining) {
    	while(timeRemaining > 0) {
    		//elitism
    		//culling
    		//mutation
    		//crossover
    		//recursive
    	}
    	return population; //place holder
    }


}

