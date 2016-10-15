package org.eldrelore.puzzle.dto;

public class Location {
	private int row;
	private int column;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public int hashCode() {
		int hashCode = 37 * row;
		hashCode = hashCode + (79 * column);
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		boolean response = false;
		if (obj instanceof Location) {
			Location loc = (Location) obj;
			response = (this.row == loc.getRow() && this.column == loc.getColumn());
		}
		return response;
	}
}
