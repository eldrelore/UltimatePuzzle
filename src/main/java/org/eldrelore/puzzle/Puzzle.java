package org.eldrelore.puzzle;

import java.util.ArrayList;
import java.util.Collection;

import org.eldrelore.puzzle.dto.Board;
import org.eldrelore.puzzle.dto.Piece;

/**
 * A puzzle. Has a board, and the recursive algorithm to solve puzzle. Currently
 * used to solve a 4x4 (16-piece) puzzle. Could handle more, but would involve
 * changing the available pieces and size of board. (neither of which are in
 * this class)
 */
public class Puzzle {

	/**
	 * puzzle pieces (added for executables)
	 */
	private Collection<Piece> pieces = new ArrayList<Piece>();
	/**
	 * board for puzzle
	 */
	private Board board = null;

	/**
	 * 
	 * @return Board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * 
	 * @return Collection<Piece>
	 */
	public Collection<Piece> getPieces() {
		return pieces;
	}

	/**
	 * 
	 * @param pieces
	 */
	public void setPieces(Collection<Piece> pieces) {
		this.pieces = pieces;
	}

	/**
	 * 
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * public parameterized constructor
	 * 
	 * @param columns
	 * @param rows
	 */
	public Puzzle(int columns, int rows) {
		board = new Board(columns, rows);
	}

	/**
	 * solutions
	 */
	private Collection<String> solutions = new ArrayList<String>();

	/**
	 * retrieve solutions
	 * 
	 * @return Collection<String>
	 */
	public Collection<String> getSolutions() {
		return solutions;
	}

	/**
	 * recursive approach to solving the puzzle, given a list of pieces.
	 * 
	 * @param pieces
	 */
	public void solvePuzzle(Collection<Piece> pieces) {
		/*
		 * For each location in the puzzle
		 * 
		 * cycle through each puzzle piece, to determine if it fits.
		 * 
		 * If it does, mark the piece as used in this path, and proceed to the
		 * next location.
		 * 
		 * If it does not fit, rotate it up to 3 times to see if it fits. Mark
		 * each rotation as used in path.
		 * 
		 * If it does not fit in any rotation, try the next piece.
		 * 
		 * If none of the pieces fit, mark that this branch doesn't work, go
		 * back a step, and try the remaining pieces in that location.
		 */
		String currentPath = board.toString();
		if (null == pieces || pieces.isEmpty()) {
			solutionFound(currentPath);
		} else {
			/*
			 * go through the next location, for each piece. If the piece fits,
			 * check and see if it's in the paths already (with that rotation),
			 * if it is, go on to the next piece.
			 */

			boolean pieceFits = false;
			for (Piece piece : pieces) {
				for (int i = 0; i < 4; i++) {
					pieceFits = doesPieceFit(piece);

					if (pieceFits) {
						pieceFits(pieces, piece);
						/*
						 * if all pieces fit, and we're coming back from a prior
						 * segment, what do we want to do here?
						 * 
						 * Don't rotate the piece again if it's already gone
						 * around.
						 */
						if (0 == piece.getRotations()) {
							board.setPieceAtLocation(null, board.getCurrentColumn(), board.getCurrentRow());
							pieceFits = false;
							break;
						}
					} else {
						pieceDoesNotFit(piece);

					}
				}

			}
			if (!pieceFits) {
				noPieceFits();
				return;
			}
		}
	}

	/**
	 * When a piece fits, go one level deeper recursively
	 * 
	 * @param pieces
	 * @param piece
	 */
	private void pieceFits(Collection<Piece> pieces, Piece piece) {
		board.getNextLocation();
		Collection<Piece> recursivePieces = new ArrayList<Piece>();
		recursivePieces.addAll(pieces);
		recursivePieces.remove(piece);
		solvePuzzle(recursivePieces);
		/*
		 * whether or not the piece fits, rotate after trying to solve its
		 * further solutions.
		 */
		piece.rotateClockwise();
	}

	/**
	 * What to do when a piece does not fit (rotate it)
	 * 
	 * @param piece
	 */
	private void pieceDoesNotFit(Piece piece) {
		piece.rotateClockwise();
	}

	/**
	 * What to do when no pieces fits
	 */
	private void noPieceFits() {
		board.setPieceAtLocation(null, board.getCurrentColumn(), board.getCurrentRow());
		board.getPriorLocation();
		return;
	}

	/**
	 * What to do when a full solution is found
	 * 
	 * @param currentPath
	 */
	private void solutionFound(String currentPath) {
		solutions.add(currentPath);
		board.setPieceAtLocation(null, board.getCurrentColumn(), board.getCurrentRow());
		return;
	}

	/**
	 * determine whether a rotating piece fits into the current location
	 * 
	 * @param piece
	 * @return
	 */
	public boolean doesPieceFit(Piece piece) {
		boolean fitsLeft = false;
		boolean fitsTop = false;
		if (0 == board.getCurrentColumn()) {
			fitsLeft = true;
		} else {
			Piece priorColumPiece = board.getPieceAtLocation(board.getCurrentColumn() - 1, board.getCurrentRow());
			if (piece.getLeft().getType().equals(priorColumPiece.getRight().getType())
					&& !piece.getLeft().getDirection().equals(priorColumPiece.getRight().getDirection())) {
				fitsLeft = true;
			}
		}
		if (0 == board.getCurrentRow()) {
			fitsTop = true;
		} else {
			Piece priorRowPiece = board.getPieceAtLocation(board.getCurrentColumn(), board.getCurrentRow() - 1);
			if (piece.getTop().getType().equals(priorRowPiece.getBottom().getType())
					&& !piece.getTop().getDirection().equals(priorRowPiece.getBottom().getDirection())) {
				fitsTop = true;
			}
		}
		boolean pieceFits = fitsLeft && fitsTop;
		board.setPieceAtLocation(piece, board.getCurrentColumn(), board.getCurrentRow());
		return pieceFits;
	}
}