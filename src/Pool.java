

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	


}

