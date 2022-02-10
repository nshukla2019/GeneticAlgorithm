package src;

import java.lang.Math;


public class Organism implements Comparable<Organism>{
    double fitness_score;

    public Organism(double fitness_score) {
        this.fitness_score = fitness_score;
    }
    
    @Override
    public int compareTo(Organism o) {      
        return (int) (o.fitness_score - this.fitness_score);
    }	

    
    Organism setFitnessScore(Organism organism, double score, double power) {
    	organism.fitness_score = Math.pow(score, power);
    	return organism;
    }
    
    double getFitnessScore() {
    	return this.fitness_score;
    }




	
    



}
