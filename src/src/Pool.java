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
		
		if(!isTower) {
			 numCrossoverParents = 40;}
		else {
			 numCrossoverParents = 3;}
		//repeat for the each organism in the next generation - the ones carried over by elitism
    	for(int i = 0; i < prevGen.generation.size() - nextGen.generation.size(); i++) {

    		List<Organism> crossoverOrgos = new ArrayList<Organism>(); 
    		
    		for(int j = 0; j<numCrossoverParents; j ++) {
    				crossoverOrgos.add(chooseOrganism(prevGen));
    		}
    			nextGeneration.generation.add(chooseOrganism(prevGen).crossover(crossoverOrgos));
    		}
    		
   		 
    	
    	return nextGen;
    }
    
    /*
     * ChooseOrganism takes a pool and a random number
     */
    public Organism chooseOrganism(Pool gen) {
		Random random = new Random();

    	int scoreLeft = random.nextInt( (int) population_fitness_total + 1);
    	int orgNum = -1;
    	
    	for(int i = 0; scoreLeft >= 0; i++) {
    		scoreLeft -= gen.generation.get(i).fitness_score;
    		orgNum = i;
    	}
    	return gen.generation.get(orgNum);
    }

}

