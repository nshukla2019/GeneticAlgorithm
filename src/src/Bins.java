package src;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Bins extends Organism{
    /*public  List<Integer> binOne;
    public  List<Integer> binTwo;
    public  List<Integer> binThree;
    public  List<Integer> binFour;*/

    private int i;

    public Bins(List<ArrayList<Float>> bins, int fitness_score) {
        super(fitness_score);
        this.bins = bins;
    }

    
    /*
     * organisms: all the organisms we are crossing over
     * Numbers: all the numbers given to us by the TA
     */
    
    public Bins crossover(ArrayList<Bins> organisms, List<Float> numbers) {
    	Bins newOrganism = new Bins(new ArrayList<ArrayList<Float>>(), -1);
    	for(i = 0; i < 4; i ++) {
			newOrganism.bins.add(new ArrayList<Float>());
		}
    	//Distribute the 40 numbers to bins
    	for(int i = 0; i < organisms.size(); i ++) {
    		Float target = (Float) numbers.get(i);
    		
    		
    		Bins currentOrganism = organisms.get(i);
    			if(currentOrganism.bins.get(0).contains(target)) {
    				newOrganism.bins.get(0).add(target);
    			}
    			else if(currentOrganism.bins.get(1).contains(target)) {
    				newOrganism.bins.get(1).add(target);
    			}
    			else if(currentOrganism.bins.get(2).contains(target)) {
    				newOrganism.bins.get(2).add(target);
    			}
    			else if(currentOrganism.bins.get(3).contains(target)) {
    				newOrganism.bins.get(3).add(target);
    			}
    			else {
    				System.out.println("ERROR! Target not found in any bin.");
    			}
    		}
    	
    	
    	//Move all of List 1 overflows to a random other list
    	/* Can make into a function if we change lists to be in an array*/
    	
    	
    	Random r = new Random();
    	//Start with cutting down bucket 1 to 10
    	int chooseRandomNumbertToMove;
    	Float numberToMove;
    	
    	ArrayList chooseNumToMoveExcessToo = new ArrayList();
	    
	    for(int j = 0; j < 4; j++) {
	    	if(newOrganism.bins.get(j).size() < 10) {
	    		chooseNumToMoveExcessToo.add(j);
	    	}
	    }
    	
	    //go through the buckets and ensure that there are 10 in each bucket
    	for(int i = 0; i < 4; i++) {
    		while(newOrganism.bins.get(i).size() > 10) {
    			
    			//choose which piece out of bucket i to move
		    	chooseRandomNumbertToMove = r.nextInt(newOrganism.bins.get(i).size());
		    	numberToMove = newOrganism.bins.get(i).get(chooseRandomNumbertToMove);
		    	newOrganism.bins.get(i).remove(chooseRandomNumbertToMove);
		    	
		    	//Choose a bucket to move to
		    	int bucketToMoveTo = r.nextInt(chooseNumToMoveExcessToo.size());
		    	int moveToThisBucket = (int)chooseNumToMoveExcessToo.get(bucketToMoveTo);
		    	
		    	//move that element to the chosen bucket
		    	newOrganism.bins.get(moveToThisBucket).add(numberToMove);
		    	
		    	
		    	if(newOrganism.bins.get(moveToThisBucket).size() == 10) {
		    		chooseNumToMoveExcessToo.remove(bucketToMoveTo);
		    	}
		    
		    
		    
    		}
    	
    	}
    	
	    	
		return newOrganism;
    }

	@Override
	public Organism crossover(List<Organism> crossoverOrgos) {
		// TODO Auto-generated method stub
		return null;
	}
}
