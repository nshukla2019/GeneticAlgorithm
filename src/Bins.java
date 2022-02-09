package src;
import java.util.ArrayList;
import java.util.List;

public class Bins extends Organism{
    public List<ArrayList<Float>> bins;


   public Bins(List<ArrayList<Float>> bins, int fitness_score) {
       super(fitness_score);
       this.bins = bins;
   }

 //takes a set of bins and returns its score
//   double calculateScore(Bins bins) {
//   	int binOneScore = 1;
//   	for(int i : bins.binOne) {binOneScore = binOneScore*i;}
//   	int binTwoScore = 0;
//   	for(int i : bins.binTwo) {binTwoScore += i;}
//   	int binScoreThree = Collections.max(bins.binThree) - Collections.min(bins.binThree);
//   	double score = binOneScore + binTwoScore + binScoreThree;
//   	return score;
//   }



}
