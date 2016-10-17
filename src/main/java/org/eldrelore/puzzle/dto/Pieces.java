package org.eldrelore.puzzle.dto;

import java.util.HashMap;
import java.util.Map;

import org.eldrelore.puzzle.types.SideDirectionType;
import org.eldrelore.puzzle.types.SideType;

/**
 * 
 * Enumeration of pieces (no rotation), contains identifiers, and which pieces
 * are holes/tabs. All pieces are currently tab-top and tab-left, with
 * hole-bottom and hole-right. This is for convenience. The algorithms used to
 * solve the puzzle will work if the pieces are not in this order.
 */
public enum Pieces {

	First("A", new Side(SideType.Cross, SideDirectionType.TAB), new Side(SideType.Arrow, SideDirectionType.TAB),
			new Side(SideType.Arrow, SideDirectionType.HOLE), new Side(SideType.Circle, SideDirectionType.HOLE)),

	Second("B", new Side(SideType.Circle, SideDirectionType.TAB), new Side(SideType.Circle, SideDirectionType.TAB),
			new Side(SideType.Circle, SideDirectionType.HOLE), new Side(SideType.Arrow, SideDirectionType.HOLE)),

	Third("C", new Side(SideType.Arrow, SideDirectionType.TAB), new Side(SideType.Arrow, SideDirectionType.TAB),
			new Side(SideType.Circle, SideDirectionType.HOLE), new Side(SideType.ReverseArrow, SideDirectionType.HOLE)),

	Fourth("D", new Side(SideType.Circle, SideDirectionType.TAB),
			new Side(SideType.ReverseArrow, SideDirectionType.TAB),
			new Side(SideType.ReverseArrow, SideDirectionType.HOLE), new Side(SideType.Arrow, SideDirectionType.HOLE)),

	Fifth("E", new Side(SideType.Cross, SideDirectionType.TAB), new Side(SideType.Arrow, SideDirectionType.TAB),
			new Side(SideType.Cross, SideDirectionType.HOLE), new Side(SideType.ReverseArrow, SideDirectionType.HOLE)),

	Sixth("F", new Side(SideType.Circle, SideDirectionType.TAB), new Side(SideType.Cross, SideDirectionType.TAB),
			new Side(SideType.ReverseArrow, SideDirectionType.HOLE), new Side(SideType.Circle, SideDirectionType.HOLE)),

	Seventh("G", new Side(SideType.ReverseArrow, SideDirectionType.TAB),
			new Side(SideType.Arrow, SideDirectionType.TAB), new Side(SideType.Arrow, SideDirectionType.HOLE),
			new Side(SideType.Circle, SideDirectionType.HOLE)),

	Eighth("H", new Side(SideType.Circle, SideDirectionType.TAB),
			new Side(SideType.ReverseArrow, SideDirectionType.TAB), new Side(SideType.Circle, SideDirectionType.HOLE),
			new Side(SideType.Cross, SideDirectionType.HOLE)),

	Ninth("I", new Side(SideType.ReverseArrow, SideDirectionType.TAB),
			new Side(SideType.ReverseArrow, SideDirectionType.TAB), new Side(SideType.Arrow, SideDirectionType.HOLE),
			new Side(SideType.Cross, SideDirectionType.HOLE)),

	Tenth("J", new Side(SideType.Circle, SideDirectionType.TAB), new Side(SideType.Cross, SideDirectionType.TAB),
			new Side(SideType.Cross, SideDirectionType.HOLE), new Side(SideType.Circle, SideDirectionType.HOLE)),

	Eleventh("K", new Side(SideType.ReverseArrow, SideDirectionType.TAB),
			new Side(SideType.Cross, SideDirectionType.TAB), new Side(SideType.Arrow, SideDirectionType.HOLE),
			new Side(SideType.ReverseArrow, SideDirectionType.HOLE)),

	Twelth("L", new Side(SideType.Circle, SideDirectionType.TAB),
			new Side(SideType.ReverseArrow, SideDirectionType.TAB), new Side(SideType.Cross, SideDirectionType.HOLE),
			new Side(SideType.ReverseArrow, SideDirectionType.HOLE)),

	Thirteenth("M", new Side(SideType.Arrow, SideDirectionType.TAB), new Side(SideType.Circle, SideDirectionType.TAB),
			new Side(SideType.Circle, SideDirectionType.HOLE), new Side(SideType.Arrow, SideDirectionType.HOLE)),

	Fourteenth("N", new Side(SideType.Arrow, SideDirectionType.TAB), new Side(SideType.Circle, SideDirectionType.TAB),
			new Side(SideType.Circle, SideDirectionType.HOLE), new Side(SideType.Cross, SideDirectionType.HOLE)),

	Fifteenth("O", new Side(SideType.Circle, SideDirectionType.TAB), new Side(SideType.Cross, SideDirectionType.TAB),
			new Side(SideType.Arrow, SideDirectionType.HOLE), new Side(SideType.Arrow, SideDirectionType.HOLE)),

	Sixteenth("P", new Side(SideType.ReverseArrow, SideDirectionType.TAB),
			new Side(SideType.ReverseArrow, SideDirectionType.TAB), new Side(SideType.Circle, SideDirectionType.HOLE),
			new Side(SideType.Cross, SideDirectionType.HOLE)),

	;

	static {
		buildLookup();
	}
	private Piece piece;

	/**
	 * private constructor
	 * 
	 * @param identifier
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 */
	private Pieces(String identifier, Side top, Side left, Side bottom, Side right) {
		Piece innerPiece = new Piece(identifier, top, left, bottom, right);
		this.setPiece(innerPiece);
	}

	/**
	 * lookup map and the logic to build it.
	 */
	private static Map<String, Piece> lookup;

	private static void buildLookup() {
		if (null == lookup) {
			lookup = new HashMap<String, Piece>();
		}
		for (Pieces pieces : Pieces.values()) {
			Piece piece = pieces.getPiece();
			lookup.put(piece.getIdentifier(), piece);
		}
	}

	/**
	 * Convenience method, we always want a rotating piece, though if we track
	 * them in the lookup map, it can start to have stateful rotation
	 * information if we're not careful.
	 * 
	 * @param identifier
	 * @return
	 */
	public static Piece getPieceByIdentifier(String identifier) {
		Piece piece = null;
		if (lookup.containsKey(identifier)) {
			piece = lookup.get(identifier);
		}

		return piece.clonePiece();
	}

	public Piece getPiece() {
		return piece.clonePiece();
	}

	private void setPiece(Piece piece) {
		this.piece = piece;
	}
}