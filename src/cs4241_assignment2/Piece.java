package cs4241_assignment2;


enum TYPE {
	TOP,MIDDLE,BOTTOM
}

public class Piece {
	public TYPE type;
	public int width;
	public int strength;
	public int cost;
	
	public Piece(TYPE levelType, int levelWidth, int levelStrength, int levelCost) {
		type = levelType;
		width = levelWidth;
		strength = levelStrength;
		cost = levelCost;
		
	}

}