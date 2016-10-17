package org.eldrelore.puzzle.dto;

/**
 * Basic puzzle piece Does not have rotating information; so this is mostly used
 * for lookup of which piece has which tabs and holes.
 *
 */
public class Piece implements Comparable<Piece> {

	public Piece(String identifier, Side top, Side left, Side bottom, Side right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.identifier = identifier;
	}

	private String identifier;
	private int rotations;
	private Side top;
	private Side bottom;
	private Side left;
	private Side right;

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

	public int compareTo(Piece o) {
		return this.getIdentifier().compareTo(o.getIdentifier());
	}

	@Override
	public String toString() {
		return this.getIdentifier() + this.getRotations();
	}

	public Piece clonePiece() {
		Piece p = new Piece(this.getIdentifier(), this.getTop(), this.getLeft(), this.getBottom(), this.getRight());
		p.setRotations(this.getRotations());
		return p;

	}

	@Override
	public boolean equals(Object obj) {
		boolean response = false;
		if (obj instanceof Piece) {
			Piece p = (Piece) obj;
			response = this.getIdentifier().equals(p.getIdentifier()) && this.getRotations() == p.getRotations()
					&& this.getTop().equals(p.getTop()) && this.getLeft().equals(p.getLeft())
					&& this.getBottom().equals(p.getBottom()) && this.getRight().equals(p.getRight());
		}
		return response;
	}

	public Side getTop() {
		return top;
	}

	public void setTop(Side top) {
		this.top = top;
	}

	public Side getBottom() {
		return bottom;
	}

	public void setBottom(Side bottom) {
		this.bottom = bottom;
	}

	public Side getLeft() {
		return left;
	}

	public void setLeft(Side left) {
		this.left = left;
	}

	public Side getRight() {
		return right;
	}

	public void setRight(Side right) {
		this.right = right;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}