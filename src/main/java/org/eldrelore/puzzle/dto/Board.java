package org.eldrelore.puzzle.dto;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public Board(int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		board = new Piece[columns][rows];
	}

	private int rows = 0;
	private int columns = 0;
	private int currentRow = 0;
	private int currentColumn = 0;

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public void setCurrentColumn(int currentColumn) {
		this.currentColumn = currentColumn;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public int getCurrentColumn() {
		return currentColumn;
	}

	private Piece[][] board;

	private List<Piece> puzzlePieces = new ArrayList<Piece>();

	private static final String DELIMITER = "->";

	/**
	 * Transform the board into a string representation
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				Piece boardPiece = getPieceAtLocation(column, row);
				if (null != boardPiece) {
					sb.append(boardPiece.getIdentifier()).append(boardPiece.getRotations());
					sb.append(DELIMITER);
				} else {
					sb.append("Empty Location");
					sb.append(DELIMITER);
				}

			}
		}
		/* trim off trailing delimiter -> */
		sb.setLength(sb.length() - DELIMITER.length());
		return sb.toString();
	}

	/**
	 * deserialize a board from string input
	 * 
	 * @param inputBoard
	 */
	public void fromString(String inputBoard) {
		/*
		 * split based on delimiter, then you have the piece identifier, and the
		 * number of rotations.
		 */
		String[] pieceSummaries = inputBoard.split(DELIMITER);
		int counter = 0;
		for (String pieceSummary : pieceSummaries) {
			int row = counter / columns;
			int column = counter % columns;
			String pieceIdentifier = String.valueOf(pieceSummary.charAt(0));
			Integer rotations = Integer.valueOf(String.valueOf(pieceSummary.charAt(1)));
			Piece piece = Pieces.getPieceByIdentifier(pieceIdentifier);
			piece.setRotations(rotations);
			counter++;
			setPieceAtLocation(piece, column, row);
		}
	}

	/**
	 * get the next location
	 * 
	 * @return Location
	 */
	public Location getNextLocation() {
		Location location = new Location();
		if (currentColumn < (columns - 1)) {
			currentColumn++;
		} else if (currentRow < (rows - 1)) {
			currentRow++;
			currentColumn = 0;
		}

		location.setColumn(currentColumn);
		location.setRow(currentRow);
		return location;
	}

	/**
	 * get the immediately prior location
	 * 
	 * @return Location
	 */
	public Location getPriorLocation() {
		Location location = new Location();
		if (currentColumn >= 1) {
			currentColumn--;
		} else if (currentRow >= 1) {
			currentRow--;
			currentColumn = columns - 1;
		}
		location.setColumn(currentColumn);
		location.setRow(currentRow);
		return location;
	}

	public Piece getPieceAtLocation(int column, int row) {
		return board[column][row];

	}

	public Piece[][] getBoard() {
		return board;
	}

	public void setPieceAtLocation(Piece piece, int column, int row) {
		board[column][row] = piece;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public List<Piece> getPuzzlePieces() {
		return puzzlePieces;
	}

	public void setPuzzlePieces(List<Piece> puzzlePieces) {
		this.puzzlePieces = puzzlePieces;
	}
}