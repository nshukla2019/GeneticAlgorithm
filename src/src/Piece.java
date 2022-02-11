package src;
import java.util.List;
import java.util.Random;

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

    public Piece randomPiece(List<Piece> list) {
    	Random r = new Random();
    	int pieceIndex = r.nextInt(list.size());
    	return list.get(pieceIndex);
    }
}
