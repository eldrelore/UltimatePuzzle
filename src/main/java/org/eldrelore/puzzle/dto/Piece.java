package org.eldrelore.puzzle.dto;

/**
 * Basic puzzle piece Does not have rotating information; so this is mostly used
 * for lookup of which piece has which tabs and holes.
 *
 */
public class Piece {

	public Piece(String identifier, Side top, Side left, Side bottom, Side right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.identifier = identifier;
	}

	private String identifier;

	private Side top;
	private Side bottom;
	private Side left;
	private Side right;

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