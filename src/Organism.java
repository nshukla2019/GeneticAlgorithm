package src;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;


public abstract class Organism implements Comparable<Organism>{
    double fitness_score;
    List[] pieces = new List[4];

    public Organism(double fitness_score) {
        this.fitness_score = fitness_score;
    }
    
    @Override
    public int compareTo(Organism o) {      
        return (int) (o.fitness_score - this.fitness_score);
    }	

    /**
     * @param score the calculated score of the organism
     * @param power the power that score is being raised to
     */
    void setFitnessScore(double score, double power) {
    	this.fitness_score = Math.pow(score, power);
    }

    public abstract Organism crossover(List<Organism> crossoverOrgos);

}
