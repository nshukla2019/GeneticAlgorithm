package src;
import java.util.List;

public class Bins extends Subpart{
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
}
