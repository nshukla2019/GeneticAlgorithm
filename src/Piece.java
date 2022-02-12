package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

enum TYPE {
	LOOKOUT,WALL,DOOR
}

public class Piece {
    public TYPE type;
    public int width;
    public int strength;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return width == piece.width &&
                strength == piece.strength &&
                cost == piece.cost &&
                type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, width, strength, cost);
    }

    public int cost;

    public Piece(TYPE levelType, int levelWidth, int levelStrength, int levelCost) {
        type = levelType;
        width = levelWidth;
        strength = levelStrength;
        cost = levelCost;
    }

    /**
     * creates a new list of original list of pieces
     * (so we aren't removing from the same list each time we remove pieces being used)
     * @param originalListOfPieces is the original list of pieces created when reading in the file
     * @return a list of Piece
     */
    public static List<Piece> copyOfListOfPieces(List<Piece> originalListOfPieces) {
        List<Piece> copyOfListOfPieces = new ArrayList<>();

        for (Piece currentPiece : originalListOfPieces) {
            Piece newPiece = new Piece(null, -1,  -1, -1);

            newPiece.type = currentPiece.type;
            newPiece.width = currentPiece.width;
            newPiece.strength = currentPiece.strength;
            newPiece.cost = currentPiece.cost;

            copyOfListOfPieces.add(newPiece);
        }

        return copyOfListOfPieces;
    }

    /**
     * removes the pieces that the currentTower is using
     * @param currentTower the current tower
     * @return list of pieces not in the currentTower
     */
    public static List<Piece> piecesNotInUse(Tower currentTower) {
        boolean topPieceInUse = false;
        boolean bottomPieceInUse = false;

        List<Piece> copyOfPieces;
        copyOfPieces = copyOfListOfPieces(createZeroGen.listOfPieces);

        for (Piece currentPiece : copyOfPieces) {
            if (currentPiece.equals(currentTower.top)) {
                topPieceInUse = currentPiece.equals(currentTower.top); // set to true if the currentPiece matches the topPiece in currentTower
            }
            if (currentPiece.equals(currentTower.bottom)) {
                bottomPieceInUse = currentPiece.equals(currentTower.bottom); // set to true if the currentPiece matches the bottomPiece in currentT
            }
        }

        if (topPieceInUse) {
            copyOfPieces.remove(currentTower.top); // remove the top piece from the listOfPiece since it is in use
        }
        if (bottomPieceInUse) {
            copyOfPieces.remove(currentTower.bottom); // remove the bottom piece from the listOfPiece since it is in use
        }

        for (Piece currentMiddlePiece : currentTower.middle_pieces) {
            // if the currentPiece matches the currentMiddlePiece in currentTower
            // remove the currentPiece from the listOfPiece since it is in use as one of the bottom pieces
            copyOfPieces.removeIf(currentPiece -> currentPiece.equals(currentMiddlePiece));
        }

        return copyOfPieces;
    }

    public Piece randomPiece(List<Piece> list) {
        Random r = new Random();
        int pieceIndex = r.nextInt(list.size());
        return list.get(pieceIndex);
    }


}
