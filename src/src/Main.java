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
        ArrayList numsForBin = new ArrayList<Float>();
        
        for(Float i = 1.0f; i < 41; i ++) {
        	numsForBin.add(i);
        }

        //create bins for first organism
        ArrayList O1b2 = new ArrayList<Float>();
        for(Float i = 11f; i < 21; i ++) {
        	O1b2.add(i);
        }
        
        ArrayList O1b1 = new ArrayList<Float>();
        for(Float i = 1f; i < 11; i ++) {
        	O1b1.add(i);
        }
        
        ArrayList O1b3 = new ArrayList<Float>();
        for(Float i = 21f; i < 31; i ++) {
        	O1b3.add(i);
        }
        
        ArrayList O1b4 = new ArrayList<Float>();
        for(Float i = 21f; i < 31; i ++) {
        	O1b4.add(i);
        }
        ArrayList binArray = new ArrayList<ArrayList<Float>>();
        binArray.add(O1b1);
        binArray.add(O1b2);
        binArray.add(O1b3);
        binArray.add(O1b4);
        
        Bins bin1 = new Bins(binArray, 30);
        

        //create bins for second organism
        ArrayList O2b2 = new ArrayList<Float>();
        for(Float i = 11f; i < 21; i ++) {
        	O2b2.add(i);
        }
        
        ArrayList O2b1 = new ArrayList<Float>();
        for(Float i = 1f; i < 11; i ++) {
        	O2b1.add(i);
        }
        
        ArrayList O2b3 = new ArrayList<Float>();
        for(Float i = 21f; i < 31; i ++) {
        	O2b3.add(i);
        }
        
        ArrayList O2b4 = new ArrayList<Float>();
        for(Float i = 21f; i < 31; i ++) {
        	O2b4.add(i);
        }
        
        ArrayList binArray2 = new ArrayList<ArrayList<Float>>();
        binArray2.add(O2b1);
        binArray2.add(O2b2);
        binArray2.add(O2b3);
        binArray2.add(O2b4);
        
        Bins bin2 = new Bins(binArray2, 30);
        
        ArrayList<Bins> parents = new ArrayList<Bins>();

        for(int i = 0; i < 20; i ++) {
        	parents.add(bin1);
        	parents.add(bin2);
        }
        
        bin1.crossover(parents, numsForBin);
        System.out.println("done");
    }

}

