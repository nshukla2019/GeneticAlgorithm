package src;
import java.util.List;
import java.util.Collections;



public class Bins extends Organism{
   public  List<Integer> binOne;
   public  List<Integer> binTwo;
   public  List<Integer> binThree;
   public  List<Integer> binFour;
   
   public Bins(List<Integer> binOne, List<Integer> binTwo, List<Integer> binThree, List<Integer> binFour, int fitness_score) {
       super(fitness_score);
       this.binOne = binOne;
       this.binTwo = binTwo;
       this.binThree = binThree;
       this.binFour = binFour;
   }
   
 //takes a set of bins and returns its score
   double calculateScore(Bins bins) {
   	int binOneScore = 1;
   	for(int i : bins.binOne) {binOneScore = binOneScore*i;}
   	int binTwoScore = 0;
   	for(int i : bins.binTwo) {binTwoScore += i;}
   	int binScoreThree = Collections.max(bins.binThree) - Collections.min(bins.binThree);
   	double score = binOneScore + binTwoScore + binScoreThree;
   	return score;
   }
   
   
   
}
