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

	public Bins(List<Integer> binOne, List<Integer> binTwo, List<Integer> binThree, List<Integer> binFour, int fitness_score) {
        super(fitness_score);
        this.pieces[0] = binOne;
        this.pieces[1] = binTwo;
        this.pieces[2] = binThree;
        this.pieces[3] = binFour;
    }
    
    /*
     * organisms: all the organisms we are crossing over
     * Numbers: all the numbers given to us by the TA
     */
    
    public Bins crossover(List<Bins> organisms, List<Integer> numbers) {
    	Bins newOrganism = new Bins(new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>(), -1);
    	
    	//Distribute the 40 numbers to bins
    	for(int i = 0; i < organisms.size(); i ++) {
    		int target = numbers.get(i);
    		Bins currentOrganism = organisms.get(i);
    			if(currentOrganism.pieces[0].contains(target)) {
    				newOrganism.pieces[0].add(target);
    			}
    			else if(currentOrganism.pieces[1].contains(target)) {
    				newOrganism.pieces[1].add(target);
    			}
    			else if(currentOrganism.pieces[2].contains(target)) {
    				newOrganism.pieces[2].add(target);
    			}
    			else if(currentOrganism.pieces[3].contains(target)) {
    				newOrganism.pieces[3].add(target);
    			}
    			else {
    				System.out.println("ERROR! Target not found in any bin.");
    			}
    		}
    	
    	
    	//Move all of List 1 overflows to a random other list
    	/* Can make into a function if we change lists to be in an array*/
    	
    	
    	Random r = new Random();
    	//Start with cutting down bucket 1 to 10
    	int chooseRandomNumbertToMove = -100;
    	Integer numberToMove = -1000;
    	
    	while(newOrganism.pieces[0].size() > 10) {
	    	chooseRandomNumbertToMove = r.nextInt(newOrganism.pieces[0].size()) + 1;
	    	numberToMove = (int) newOrganism.pieces[0].get(chooseRandomNumbertToMove);
	    	newOrganism.pieces[0].remove(chooseRandomNumbertToMove);
	    	
	    	if(newOrganism.pieces[1].size() < 10) {
	    		newOrganism.pieces[1].add(numberToMove);
	    	}
	    	else if(newOrganism.pieces[2].size() < 10) {
	    		newOrganism.pieces[2].add(numberToMove);
	    	}
	    	else if(newOrganism.pieces[3].size() < 10) {
	    		newOrganism.pieces[3].add(numberToMove);
	    	}
    	}
    	
    	
    	ArrayList chooseNumToMoveExcessToo = new ArrayList();
	    
	    for(int j = 0; j < 3; j++) {
	    	if(pieces[j].size() <= 10) {
	    		chooseNumToMoveExcessToo.add(j);
	    	}
	    }
    	
	    //go through the buckets and ensure that there are 10 in each bucket
    	for(int i = 0; i < 4; i++) {
    		while(newOrganism.pieces[i].size() > 10) {
    			
    			//choose which piece out of bucket i to move
		    	chooseRandomNumbertToMove = r.nextInt(newOrganism.pieces[i].size()) + 1;
		    	numberToMove = (int) newOrganism.pieces[i].get(chooseRandomNumbertToMove);
		    	newOrganism.pieces[i].remove(chooseRandomNumbertToMove);
		    	
		    	//Choose a bucket to move to
		    	int bucketToMoveTo = r.nextInt(3);
		    	chooseNumToMoveExcessToo.get(bucketToMoveTo);
		    	
		    	//move that element to the chosen bucket
		    	newOrganism.pieces[bucketToMoveTo].add(numberToMove);
		    
		    
		    
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
