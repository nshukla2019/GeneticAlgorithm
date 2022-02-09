
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bins extends Organism{
    public List<ArrayList<Float>> bins;


   public Bins(List<ArrayList<Float>> bins, int fitness_score) {
       super(fitness_score);
       this.bins = bins;
   }

 //takes a set of bins and returns its score
   double calculateScore(Bins binSet) {
   	float binOneScore = 1;
   	ArrayList<Float> binOne = binSet.bins.get(0);
   	for(float i : binOne) {binOneScore = binOneScore*i;}
   	
   	float binTwoScore = 0;
   	ArrayList<Float> binTwo = binSet.bins.get(1);
   	for(float i : binTwo) {binTwoScore += i;}
   	
   	ArrayList<Float> binThree = binSet.bins.get(2);
   	float binScoreThree = Collections.max(binThree) - Collections.min(binThree);
   	double score = binOneScore + binTwoScore + binScoreThree;
   	
   	return score;
   }



}
