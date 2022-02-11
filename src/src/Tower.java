package src;

import java.util.ArrayList;
import java.util.List;

public class Tower extends Organism{
   /* public Piece top;
    public List<Piece> middle_pieces;
    public Piece bottom;*/

    public Tower(Piece top, List<Piece> middle_pieces, Piece bottom, int fitness_score) {
        super(fitness_score);
        //top is Pieces[0].get(0)
        //middle is Pieces[1]
        //bottom is Pieces[2].get(0)
        
        List list0 = new ArrayList<Piece>();
        list0.add(top);
        this.pieces[0] =list0;
        
        this.pieces[1] = middle_pieces;
        
        List list1 = new ArrayList<Piece>();
        list1.add(bottom);
        this.pieces[2].add(bottom);
        
    }
    
    //takes a piece and returns true if it  is of TYPE TOP
  	public boolean checkPieceType(Piece piece, TYPE type) { 
  		if(piece.type == type) {return true; }
  		else {return false; }
  	}
  	
  	//returns true if tower is valid
  	boolean validTower(Tower tower) {
  		//Check if piece types in correct place
  		if(!checkPieceType( (Piece)tower.pieces[0].get(0),TYPE.LOOKOUT) || !checkPieceType((Piece)tower.pieces[0].get(0),TYPE.DOOR)) {return false;}
  		for (Piece middle : tower.pieces[1]) {
  			if(!checkPieceType(middle,TYPE.WALL)) {return false;}
  		}
  		//Check if width order is valid
  		int min = tower.pieces[0].get(0).width;
  		for(Piece middle : tower.pieces[1]) {
  			if(middle.width < min) {return false;}
  			else if(tower.pieces[2].get(0).width < min) {return false;}
  			else {min = middle.width;}	
  			if(tower.pieces[2].get(0).width < min) {return false;}
  		}
  		//Check if strength order is valid
  		for(int i = 0; i < tower.pieces[1].size(); i++) {
  			if(tower.pieces[1].get(i).strength < i) {return false;}
  		}
  		return true;
  	}
  	
  	double calculateScore(Tower tower) {
  		double score;
  		if(!validTower(tower)) {score = 0;}
  		else {
  			int costSum = tower.pieces[0].get(0).cost + tower.pieces[2].get(0).cost;
  			for(Piece middle : tower.pieces[1]) {
  				costSum = costSum + middle.cost;
  			}
  			int towerHeight = tower.pieces[1].size()+2;
  			score = 10 + Math.pow(towerHeight, 2) - costSum;
  		}
  		return score;
  	}
  	
  	public Tower crossover(List<Tower> organisms, List<Piece> pieces) {
    	Tower newTower = new Tower(null, new ArrayList<Piece>(), null, -1);
    	newTower.pieces[1] = organisms.get(1).pieces[1];
    	
    	Piece candidateTop = (Piece) organisms.get(0).pieces[0].get(0);
    	while (newTower.pieces[1].contains(candidateTop)) {
    		candidateTop = candidateTop.randomPiece(pieces);
    	}
    	
    	Piece candidateBottom = (Piece) organisms.get(0).pieces[0].get(1);
    	while (newTower.pieces[1].contains(candidateBottom)) {
    		candidateBottom = candidateBottom.randomPiece(pieces);
    	}
    	
    	return newTower;
    }

	@Override
	public Organism crossover(List<Organism> crossoverOrgos) {
		// TODO Auto-generated method stub
		return null;
	}

}
