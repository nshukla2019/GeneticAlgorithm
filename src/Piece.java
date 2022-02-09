package src;
enum TYPE {
	LOOKOUT,WALL,DOOR
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
