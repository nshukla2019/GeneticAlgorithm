package src;

import java.util.List;

public class Pool {
    public List<Organism> generation;
    final int POPULATION_SIZE;
    double population_fitness_total;
    public Pool(int POPULATION_SIZE, List<Organism> generation, double population_fitness_total) {
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

}

