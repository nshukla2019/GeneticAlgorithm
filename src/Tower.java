
import java.util.List;

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
	boolean validTower() {
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



}
