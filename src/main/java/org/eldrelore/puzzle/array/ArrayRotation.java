package org.eldrelore.puzzle.array;

import org.eldrelore.puzzle.dto.Board;
import org.eldrelore.puzzle.dto.RotatingPiece;

/**
 * Handles the matrix algebra necessary to rotate an n dimensional matrix
 * clockwise.
 * 
 */
public class ArrayRotation {

	/**
	 * rotate the board once, clockwise
	 * 
	 * @param board
	 * @return
	 */
	public Board rotate(Board board) {
		Board rotatedBoard = new Board(board.getColumns(), board.getRows());
		/*
		 * For each column and row, rotate the piece therein. There are ways to
		 * do this in space, or with less complexity. Right now straightforward
		 * is preferred.
		 */
		for (int column = 0; column < board.getColumns(); column++) {
			for (int row = 0; row < board.getRows(); row++) {
				RotatingPiece piece = board.getPieceAtLocation(column, row);
				piece.rotateClockwise();
				int newColumn = (board.getRows() - row) - 1;
				int newRow = column;
				rotatedBoard.setPieceAtLocation(piece, newColumn, newRow);
			}
		}
		return rotatedBoard;
	}
}
