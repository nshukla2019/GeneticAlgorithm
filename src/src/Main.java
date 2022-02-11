package src;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int POPULATION_SIZE = 10;

      //  createZeroGen zeroGen = new createZeroGen();

        //Pool zeroGeneration = new Pool(POPULATION_SIZE, null);
        //zeroGeneration = zeroGen.readFile("sampleFiles/puzzle2", 2, POPULATION_SIZE);
        
        //Create a test crossover with nums 1-40
        ArrayList numsForBin = new ArrayList<Integer>();
        
        for(int i = 1; i < 41; i ++) {
        	numsForBin.add(i);
        }

        //create bins for first organism
        ArrayList O1b2 = new ArrayList<Integer>();
        for(int i = 11; i < 21; i ++) {
        	O1b2.add(i);
        }
        
        ArrayList O1b1 = new ArrayList<Integer>();
        for(int i = 1; i < 11; i ++) {
        	O1b1.add(i);
        }
        
        ArrayList O1b3 = new ArrayList<Integer>();
        for(int i = 21; i < 31; i ++) {
        	O1b3.add(i);
        }
        
        ArrayList O1b4 = new ArrayList<Integer>();
        for(int i = 21; i < 31; i ++) {
        	O1b4.add(i);
        }
        
        Bins bin1 = new Bins(01b1, binTwo, binThree, binFour, fitness_score)
    }

}

