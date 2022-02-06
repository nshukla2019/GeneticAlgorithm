package cs4241_assignment2;
import java.util.List;

public class Tower extends Subpart{
	public Piece top;
	public List<Piece> middle_pieces;
	public Piece bottom;
	
	public Tower(Piece top, List<Piece> middle_pieces, Piece bottom, int fitness_score) {
		super(fitness_score);
		this.top = top;
		this.middle_pieces = middle_pieces;
		this.bottom = bottom;
	}

}
