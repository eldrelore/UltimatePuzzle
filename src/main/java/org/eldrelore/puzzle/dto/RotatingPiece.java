package org.eldrelore.puzzle.dto;

/**
 * a piece that can rotate
 *
 */
public class RotatingPiece extends Piece {

	/**
	 * create a rotating piece from the notation of a piece
	 * 
	 * @param identifier
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 */
	public RotatingPiece(String identifier, Side top, Side left, Side bottom, Side right) {
		super(identifier, top, left, bottom, right);
	}

	public RotatingPiece(Piece piece) {
		super(piece.getIdentifier(), piece.getTop(), piece.getLeft(), piece.getBottom(), piece.getRight());
	}

	private int rotations;

	public int getRotations() {
		return rotations;
	}

	public void setRotations(int rotations) {
		this.rotations = (this.rotations + rotations) % 4;
	}

	public void rotateClockwise() {
		Side s = getTop();
		setTop(getLeft());
		setLeft(getBottom());
		setBottom(getRight());
		setRight(s);
		setRotations(1);
	}

	public void rotateCounterClockwise() {
		Side s = getTop();
		setTop(getRight());
		setRight(getBottom());
		setBottom(getLeft());
		setLeft(s);
		setRotations(-1);
	}

}
