


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tower extends Organism{
    public Piece top;
    public List<Piece> middle_pieces;
    public Piece bottom;

    public Tower(Piece top, List<Piece> middle_pieces, Piece bottom, int fitness_score) {
        super(fitness_score);
        this.top = top;
        this.middle_pieces = middle_pieces;
        this.bottom = bottom;
    }
    
    /**
     * 
     * @param piece 
     * @param type LOOKOUT, DOOR, or WALL
     * @return true if the piece is the type of the given piece
     */
    //takes a piece and returns true if it  is of TYPE TOP
  	public boolean checkPieceType(Piece piece, TYPE type) { 
  		if(piece.type == type) {return true; }
  		else {return false; }
  	}


	/**
	 * checks whether the tower is valid or not
	 * @return true if the tower is valid
	 */
	public boolean validTower() {
  		//Check if piece types in correct place
  		if(!checkPieceType(this.top,TYPE.LOOKOUT) || !checkPieceType(this.bottom,TYPE.DOOR)) {return false;}
  		for (Piece middle : this.middle_pieces) {
  			if(!checkPieceType(middle,TYPE.WALL)) {return false;}
  		}
  		//Check if width order is valid
  		int min = this.top.width;
  		for(Piece middle : this.middle_pieces) {
  			if(middle.width < min) {return false;}
  			else if(this.bottom.width < min) {return false;}
  			else {min = middle.width;}	
  			if(this.bottom.width < min) {return false;}
  		}
  		//Check if strength order is valid
  		for(int i = 0; i < this.middle_pieces.size(); i++) {
  			if(this.middle_pieces.get(i).strength < i) {return false;}
  		}
  		return true;
  	}
  	
  	/**
  	 * 
  	 * @return flat score of the given tower
  	 */
  	double calculateScore() {
  		double score;
  		if(!this.validTower()) {score = 0;}
  		else {
  			int costSum = this.top.cost + this.bottom.cost;
  			for(Piece middle : this.middle_pieces) {
  				costSum = costSum + middle.cost;
  			}
  			int towerHeight = this.middle_pieces.size()+2;
  			score = 10 + Math.pow(towerHeight, 2) - costSum;
  		}
  		return score;
  	}
  	
//  	public Tower crossover(List<Tower> organisms, List<Piece> pieces) {
//    	Tower newTower = new Tower(null, new ArrayList<Piece>(), null, -1);
//    	newTower.pieces[1] = organisms.get(1).pieces[1];
//
//    	Piece candidateTop = (Piece) organisms.get(0).pieces[0].get(0);
//    	while (newTower.pieces[1].contains(candidateTop)) {
//    		candidateTop = candidateTop.randomPiece(pieces);
//    	}
//
//    	Piece candidateBottom = (Piece) organisms.get(0).pieces[0].get(1);
//    	while (newTower.pieces[1].contains(candidateBottom)) {
//    		candidateBottom = candidateBottom.randomPiece(pieces);
//    	}
//
//    	return newTower;
//    }

	/**
	 * this function creates a new organism from the top 3 elite parents from the last generation by copy the middle portion to the new organism
	 * then randomly choosing top and bottom pieces if those pieces are already in use in the current organism
	 * @param topParents are the most elite parents from the last generation
	 * @return the new "crossovered" organism
	 */
	@Override
	public Organism crossover(List<Organism> topParents) {

		Tower newTower = new Tower(null, new ArrayList<Piece>(), null, -1); //create new organism that will be the child of the crossover

		List<Tower> topTowerParents = new ArrayList<>();

		for (Organism organism : topParents) {
			topTowerParents.add((Tower)organism);
		}
		Tower firstTowerParent = topTowerParents.get(0);
		Tower secondTowerParent = topTowerParents.get(1);
		Tower thirdTowerParent = topTowerParents.get(2);

		// MIDDLE PIECE CROSSOVER
		newTower.middle_pieces = secondTowerParent.middle_pieces; // set middle of currentTower to secondTowerParent's middle


		// TOP PIECE CROSSOVER
		if (!newTower.middle_pieces.contains(firstTowerParent.top)) { // if first parent has a UNIQUE top piece
			newTower.top = firstTowerParent.top; // set newTower's top to firstTowerParent's top
		}
		else {

			while (newTower.middle_pieces.contains(firstTowerParent.top)) { // if first parent has a top piece that is already in the middle of the new tower
				Random ran = new Random();
				int randomIndex = ran.nextInt(Piece.piecesNotInUse(newTower).size());  // choose a randomPiece that is not in use

				newTower.top = Piece.piecesNotInUse(newTower).get(randomIndex);
			}
		}


		// BOTTOM PIECE CROSSOVER
		if (!newTower.middle_pieces.contains(thirdTowerParent.bottom)) { // if thirdTowerParent has a UNIQUE bottom piece
			newTower.bottom = thirdTowerParent.bottom; // set newTower's bottom to thirdTowerParent's bottom
		}
		else {

			while (newTower.middle_pieces.contains(thirdTowerParent.bottom)) { // if third parent has a bottom piece that is already in the middle of the new tower
				Random ran = new Random();
				int randomIndex = ran.nextInt(Piece.piecesNotInUse(newTower).size());  // choose a randomPiece that is not in use

				newTower.bottom = Piece.piecesNotInUse(newTower).get(randomIndex);
			}
		}

		return newTower;
	}


}
