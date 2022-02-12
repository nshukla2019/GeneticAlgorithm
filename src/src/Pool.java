package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pool {
    public List<Organism> generation;
    final int POPULATION_SIZE;
    double population_fitness_total;
    boolean isTowerPuzzle;
    
    public Pool(int POPULATION_SIZE, List<Organism> generation, boolean isTowerPuzzle) {
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.generation = generation;
        this.population_fitness_total = population_fitness_total;
        this.isTowerPuzzle = isTowerPuzzle;
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
    
    public Pool poolCrossover(Pool prevGen, Pool nextGeneration, boolean isTower) {
		
    	Pool nextGen = nextGeneration;
		int numCrossoverParents = -1;
		//if it is bins we need 40 parents, tower we need 3
		if(!isTower) {
			 numCrossoverParents = 40;}
		else {
			 numCrossoverParents = 3;}
		//repeat for the each organism in the next generation - the ones carried over by elitism
    	for(int i = 0; i < prevGen.generation.size() - nextGen.generation.size(); i++) {

    		//create a list that will be the parents
    		List<Organism> crossoverOrgos = new ArrayList<Organism>(); 
    		
    		//Choose parents
    		for(int j = 0; j<numCrossoverParents; j ++) {
    				crossoverOrgos.add(chooseOrganism(prevGen));
    		}
    		//Do the crossover for the chosen parents
    			nextGeneration.generation.add(chooseOrganism(prevGen).crossover(crossoverOrgos));
    		}
    		
   		 
    	
    	return nextGen;
    }
    
    /*
     * ChooseOrganism takes a pool and a random number
     */
    public Organism chooseOrganism(Pool gen) {
		Random random = new Random();
		//choose a random score between 1 and the total score
    	int scoreLeft = random.nextInt( (int) population_fitness_total + 1);
    	int orgNum = -1;
    	
    	//subtract each score from the rnadom score until you reach 0
    	// the score that takes ot to 0 will be the parent organism
    	for(int i = 0; scoreLeft >= 0; i++) {
    		scoreLeft -= gen.generation.get(i).fitness_score;
    		orgNum = i;
    	}
    	//return the organism that took the score left below 0
    	return gen.generation.get(orgNum);
    }

}

