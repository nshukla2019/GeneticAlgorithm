
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
    
    //takes a piece and returns true if it  is of TYPE TOP
  	public boolean checkPieceType(Piece piece, TYPE type) { 
  		if(piece.type == type) {return true; }
  		else {return false; }
  	}
  	
  	//returns true if tower is valid
  	boolean validTower(Tower tower) {
  		//Check if piece types in correct place
  		if(!checkPieceType(tower.top,TYPE.LOOKOUT) || !checkPieceType(tower.bottom,TYPE.DOOR)) {return false;}
  		for (Piece middle : tower.middle_pieces) {
  			if(!checkPieceType(middle,TYPE.WALL)) {return false;}
  		}
  		//Check if width order is valid
  		int min = tower.top.width;
  		for(Piece middle : tower.middle_pieces) {
  			if(middle.width < min) {return false;}
  			else if(tower.bottom.width < min) {return false;}
  			else {min = middle.width;}	
  			if(tower.bottom.width < min) {return false;}
  		}
  		//Check if strength order is valid
  		for(int i = 0; i < tower.middle_pieces.size(); i++) {
  			if(tower.middle_pieces.get(i).strength < i) {return false;}
  		}
  		return true;
  	}
  	
  	double calculateScore(Tower tower) {
  		double score;
  		if(!validTower(tower)) {score = 0;}
  		else {
  			int costSum = tower.top.cost + tower.bottom.cost;
  			for(Piece middle : tower.middle_pieces) {
  				costSum = costSum + middle.cost;
  			}
  			int towerHeight = tower.middle_pieces.size()+2;
  			score = 10 + Math.pow(towerHeight, 2) - costSum;
  		}
  		return score;
  	}

}
