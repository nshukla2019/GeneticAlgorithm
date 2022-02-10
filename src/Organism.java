

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

    /**
     * 
     * @param organism
     * @param score the calculated score of the organism
     * @param power the power that score is being raised to
     */
    void setFitnessScore(Organism organism, double score, double power) {
    	organism.fitness_score = Math.pow(score, power);
    }
    
    




	
    



}
