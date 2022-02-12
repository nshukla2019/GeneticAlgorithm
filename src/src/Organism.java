package src;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;


public abstract class Organism {
    double fitness_score;
    List<ArrayList<Float>> bins = new ArrayList<ArrayList<Float>>();

    public Organism(double fitness_score) {
        this.fitness_score = fitness_score;
    }
    

    Organism setFitnessScore(Organism organism, double score, double power) {
    	organism.fitness_score = Math.pow(score, power);
    	return organism;
    }



	public abstract Organism crossover(List<Organism> crossoverOrgos);
    
}
