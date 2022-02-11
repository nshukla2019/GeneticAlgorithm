package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Bins extends Organism{
   public List<ArrayList<Float>> bins;


   public Bins(List<ArrayList<Float>> bins, int fitness_score) {
       super(fitness_score);
       this.bins = bins;
   }

   /**
    * 
    * @return bin score
    */
   double calculateScore() {
   	float binOneScore = 1;
   	ArrayList<Float> binOne = this.bins.get(0);
   	for(float i : binOne) {binOneScore = binOneScore*i;}
   	
   	float binTwoScore = 0;
   	ArrayList<Float> binTwo = this.bins.get(1);
   	for(float i : binTwo) {binTwoScore += i;}
   	
   	ArrayList<Float> binThree = this.bins.get(2);
   	float binScoreThree = Collections.max(binThree) - Collections.min(binThree);
   	double score = binOneScore + binTwoScore + binScoreThree;
   	
   	return score;
   }



    /**
     * given the currentBinIndex and a Bin Object, return a Bins object (the currentBin is turned null)
     * @param indexOfBinToCheckAgainst index of the currentBin
     * @param allBins is the original Bins Object
     * @return a Bin object where the currentBin is null (meaning we cannot use those numbers)
     */
    public static Bins binsNotInUse(int indexOfBinToCheckAgainst, Bins allBins) {
       List<ArrayList<Float>> copyOfBins = new ArrayList<>();
       ArrayList<Float> newBinOne = new ArrayList<>();
       ArrayList<Float> newBinTwo = new ArrayList<>();
       ArrayList<Float> newBinThree = new ArrayList<>();
       ArrayList<Float> newBinFour = new ArrayList<>();
       int curIndex = 0;
       for (ArrayList<Float> listOfFloats : allBins.bins) {
           for (int index = 0; index < listOfFloats.size(); index++) {
               if (curIndex <= 9) {
                   newBinOne.add(listOfFloats.get(index));
               }
               else if (curIndex <= 19) {
                   newBinTwo.add(listOfFloats.get(index));
               }
               else if (curIndex <= 29) {
                   newBinThree.add(listOfFloats.get(index));
               }
               else if (curIndex <= 39) {
                   newBinFour.add(listOfFloats.get(index));
               }
           }
           curIndex = curIndex + 10;
       }


        copyOfBins.add(newBinOne);
        copyOfBins.add(newBinTwo);
        copyOfBins.add(newBinThree);
        copyOfBins.add(newBinFour);

        Bins copyOfAllBins = new Bins(copyOfBins, -1);

        copyOfAllBins.bins.set(indexOfBinToCheckAgainst, null); // set bin we are checking against to null

        return copyOfAllBins;
    }


    /**
     * given an index, output the corresponding binIndex
     * @param index is the index of the Bin object
     * @return the binIndex
     */
    public static int returnBinIndexNumber(int index) {
       int binIndex = -1;
       if (index <= 9) {
           binIndex = 0;
       }
       else if (index <= 19) {
           binIndex = 1;
       }
       else if (index <= 29) {
           binIndex = 2;
       }
       else if (index <= 39) {
           binIndex = 3;
       }

       return binIndex;
    }

    /**
     * checks whether the given index corresponds to the provided binIndex
     * @param index is the index of the float
     * @param currentBinIndex is the binIndex of the currentBin
     * @return true if the index is NOT corresponding to the currentBinIndex, false otherwise
     */
    public static boolean isRandIndexInCurrentBin(int index, int currentBinIndex) {
        if (index <= 9 && currentBinIndex == 0) {
            return false;
        }
        else if (index <= 19 && currentBinIndex == 1) {
            return false;
        }
        else if (index <= 29 && currentBinIndex == 2) {
            return false;
        }
        else if (index <= 39 && currentBinIndex == 3) {
            return false;
        }

       return true;
    }

    /**
     * calculates the index of the float according to which bin it belongs in
     * @param binIndex is the index of the currentBin
     * @param index is the index of the float
     * @return the adjusted index
     */
    public static int adjustedIndex(int binIndex, int index) {
       int adjustedIndex = -1;
       switch(binIndex) {
           case 0:
               adjustedIndex = Math.abs(0-index);
               break;
           case 1:
               adjustedIndex = Math.abs(10-index);
               break;
           case 2:
               adjustedIndex = Math.abs(20-index);
               break;
           case 3:
               adjustedIndex = Math.abs(30-index);
               break;
       }

       return adjustedIndex;
    }

}
