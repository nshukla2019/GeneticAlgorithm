

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pool {
    public List<Organism> generation;
    final int POPULATION_SIZE;
    double population_fitness_total;
    double maxScore;
    int maxScoreGen;

    public Pool(int POPULATION_SIZE, List<Organism> generation, int population_fitness_total) {
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.generation = generation;
        this.population_fitness_total = population_fitness_total;
    }
    
    /**
     * Takes a pool and assigns a score
     * @param population
     * @param power to raise score to
     * @param puzzle 1 is bins, other is tower
     */
    void assignFitnessScores(Pool population, double power, int puzzle) {
    	for(Organism o : population.generation) {
    		if (puzzle == 1) {
    			o.setFitnessScore(((Bins) o).calculateScore(),power);
    		}
    		else {
    			o.setFitnessScore(((Tower) o).calculateScore(),power);
    		}
    	}
    }


    /**
     * 
     * @param population
     * updates total fitness score
     */
    //I thought total fitness score may be more useful
    void calcTotalFitness(Pool population) {
    	double total_fitness = 0;
    	for (Organism organism : population.generation) {
    		total_fitness += organism.fitness_score;
    	}
    	population.population_fitness_total = total_fitness;
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

    /**
     *
     * @param population
     */
    void cullingNegative(Pool population) {
        //removes any organisms with negative fitness score
        List<Organism> orgs = new ArrayList<>();

        for (Organism currentOrg : population.generation) {
            if (currentOrg.fitness_score > 0) {
                orgs.add(currentOrg);
            }
        }

        //makes the culled list the generation of the current pool
        population.generation = orgs;
    }

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
            for(int binIndex = 0; binIndex < currentFourBins.bins.size(); binIndex++) {
                ArrayList<Float> currentBin = currentFourBins.bins.get(binIndex);

                // get bins not in the currentBin
                binsNotBeingUsed = Bins.binsNotInUse(binIndex, currentFourBins);

                // for each number in the currentBin
                for(int currentBinIndex = 0; currentBinIndex < currentBin.size(); currentBinIndex++) {
                    int randomNum = ran.nextInt(maxOfRandomRange);  // get random number between 0 and parameter passed

                    if (randomNum == numberToInitiateMutation) {  // randomNum chosen matches the parameter given

                        int randomIndex = ran.nextInt(40);  // choose number between 0 and 40

                        int binOfRandomIndex = Bins.returnBinIndexNumber(randomIndex); // get the binIndex of the randomIndex generated

                        int adjustedIndex = Bins.adjustedIndex(binOfRandomIndex, randomIndex); // index adjustment (modulo by 10)

                        float randomNumberAtRandomIndex = binsNotBeingUsed.bins.get(binOfRandomIndex).get(adjustedIndex); // save the float at the randomIndex

                        float currentFloat = binsNotBeingUsed.bins.get(binIndex).get(currentBinIndex); // save current float

                        currentFourBins.bins.get(binIndex).set(currentBinIndex, randomNumberAtRandomIndex); // set currentBin's index to randomNumber chosen

                        currentFourBins.bins.get(binOfRandomIndex).set(adjustedIndex, currentFloat); // set current float at randomNumber's index

                        boolean firstCheck = checkMutation(currentFourBins);

                        if (!firstCheck) {
                            //System.out.println("FALSE after MUTATION CALLED");
                            findIndexOfAccidentallyRemovedInOriginalList(currentFourBins, binsNotBeingUsed);
                        }

                        boolean secondCheck = checkMutation(currentFourBins);

                        if (!secondCheck) {
                           // System.out.println("stillNotGood");
                            findIndexOfAccidentallyRemovedInOriginalList(currentFourBins, binsNotBeingUsed);
                        }

                        boolean thirdCheck = checkMutation(currentFourBins);
                        if (!thirdCheck) {
                            //System.out.println("oops");
                            findIndexOfAccidentallyRemovedInOriginalList(currentFourBins, binsNotBeingUsed);
                        }

                    }
                }
            }

        }

        return generationAfterMutated;
    }



    // check that all 4 buckets have the same numbers distributed along them
    public boolean checkMutation(Bins setOfFourBuckets) {
        List<Float> copy = new ArrayList<>();

        copy.addAll(createZeroGen.integersProvided);

        for (List<Float> bin : setOfFourBuckets.bins) {
            for (Float f : bin) {
                copy.remove(f);
            }
        }
        if (copy.size() != 0) {
            for (Float f : copy) {
                //System.out.println("Number REMOVED: " + f);
            }

        }
        return copy.size() == 0;
    }

    // check that all 4 buckets have the same numbers distributed along them
    public float returnNumAccidentallyRemoved(Bins setOfFourBuckets) {
        List<Float> copy = new ArrayList<>();
        float floatAccidentallyRemoved = -1;
        copy.addAll(createZeroGen.integersProvided);

        for (List<Float> bin : setOfFourBuckets.bins) {
            for (Float f : bin) {
                copy.remove(f);
            }
        }
        if (copy.size() != 0) {
            for (Float f : copy) {
                floatAccidentallyRemoved = f;
            }

        }

        return floatAccidentallyRemoved;
    }

    // check that all 4 buckets have the same numbers distributed along them
    public float findIndexOfAccidentallyRemovedInOriginalList(Bins currentFourBuckets, Bins binsNotBeingUsed) {
        float accidentallyRemoved = returnNumAccidentallyRemoved(currentFourBuckets);
        int indexOfAccidentallyRemoved = -1;
        int binIndexOfAccidentallyRemoved = -1;

        for (List<Float> bin : binsNotBeingUsed.bins) {
            for (Float f : bin) {
                if (f == accidentallyRemoved) {
                    indexOfAccidentallyRemoved = bin.indexOf(f);
                    binIndexOfAccidentallyRemoved = binsNotBeingUsed.bins.indexOf(bin);
                    break;
                }
            }
        }

        if (indexOfAccidentallyRemoved == -1 || binIndexOfAccidentallyRemoved == -1) {
            //System.out.println("did't find indexes of accidentally removed");
        }
        float numAtAccidentallyRemoved = currentFourBuckets.bins.get(binIndexOfAccidentallyRemoved).get(indexOfAccidentallyRemoved);

        if (numAtAccidentallyRemoved != accidentallyRemoved) {
            currentFourBuckets.bins.get(binIndexOfAccidentallyRemoved).set(indexOfAccidentallyRemoved, accidentallyRemoved);
        }
        else {
            // when there are duplicates, but professor said number would be unique?
            for (List<Float> bin : binsNotBeingUsed.bins) {
                for (Float f : bin) {
                    if (f == accidentallyRemoved) {
                        if (bin.indexOf(f) == indexOfAccidentallyRemoved && binsNotBeingUsed.bins.indexOf(bin) == binIndexOfAccidentallyRemoved) {
                            continue;
                        }
                        else {
                            indexOfAccidentallyRemoved = bin.indexOf(f);
                            binIndexOfAccidentallyRemoved = binsNotBeingUsed.bins.indexOf(bin);
                            break;
                        }
                    }
                }
            }
            currentFourBuckets.bins.get(binIndexOfAccidentallyRemoved).set(indexOfAccidentallyRemoved, accidentallyRemoved);
        }

        return -1;
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

    public Pool poolCrossover(Pool prevGen, Pool nextGeneration, boolean isTower) {

        Pool nextGen = nextGeneration;
        int numCrossoverParents = -1;
        //if it is bins we need 40 parents, tower we need 3
        if(!isTower) {
            numCrossoverParents = 40;}
        else {
            numCrossoverParents = 3;}

        int currentPopSize = POPULATION_SIZE - nextGen.generation.size();
        //repeat for the each organism in the next generation - the ones carried over by elitism
        for(int i = 0; i < currentPopSize; i++) {

            if (i == 0) {
                //System.out.println();
            }
            //create a list that will be the parents
            List<Organism> crossoverOrgos = new ArrayList<Organism>();

            //Choose parents
            for(int j = 0; j<numCrossoverParents; j ++) {
                crossoverOrgos.add(chooseOrganism(prevGen));
            }
            //Do the crossover for the chosen parents
            nextGeneration.generation.add(crossoverOrgos.get(0).crossover(crossoverOrgos));
            //chooseOrganism(prevGen)
        }

        return nextGen;
    }

    /*
     * ChooseOrganism takes a pool
     */
    public Organism chooseOrganism(Pool gen) {
    	
        Random random = new Random();

        // choose a random score between 1 and the total score

        double randomDouble = random.nextDouble();
        double scoreLeft = randomDouble * gen.population_fitness_total;
        int orgNum = -1;

        // subtract each score from the random score until you reach 0
        // the score that takes to to 0 will be the parent organism
        for(int i = 0; scoreLeft > 0; i++) {
            scoreLeft -= gen.generation.get(i).fitness_score;
            orgNum = i;
        }
//        if (orgNum < 0) {
//        	orgNum = 0;
//        }

        //return the organism that took the score left below 0
        return gen.generation.get(orgNum);
    }

    public static int numValidTowers(Pool population) {
    	int numValid = 0;
    	for (Organism o : population.generation) {
    		if (((Tower) o).validTower()) {
    			numValid++;
    			//System.out.println("FOUND TOWER: " + numValid);
    		}
    	}
    	return numValid;
    }


    
    Pool GeneticAlgorithm(Pool population, double fitnessPower, double perElite, double perCull, int perMutate, int puzzle, long timeToRun, int genNum) throws InterruptedException {
        final boolean isTower = puzzle != 1;  // if puzzle is not 1, isTower is false

			//calc scores and assign fitness scored scaled with a power
			assignFitnessScores(population, fitnessPower, puzzle);
			calcTotalFitness(population);

			//elitism creates a new pool with the top organisms to start generation
			Pool elitismGen = elitism(population,perElite);

			if (!isTower) {
                //culling removes bottom percent of organisms from pool
                cullingNegative(population);
            }
			else {
                culling(population, perCull);
            }

			calcTotalFitness(population);

            //mutation
			Pool mutatedGeneration;

			if (!isTower) { // not towers
                mutatedGeneration = binsMutation(population,perMutate,30); //change last parameter
            }
			else {
                mutatedGeneration = towersMutation(population,perMutate,100); //change last parameter
            }

        return poolCrossover(mutatedGeneration, elitismGen, isTower); //crossover with the mutated population and the next generation which is elitism

    }


}

