package org.eldrelore.puzzle.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import org.eldrelore.puzzle.Puzzle;
import org.eldrelore.puzzle.dto.Board;
import org.eldrelore.puzzle.dto.Piece;

/**
 * 
 * Specific executor for solving puzzle. Due to the nature of the problem, this
 * means setting the first piece (to each of the 64 (16 pieces x 4 rotations).
 * The first piece (and the collection of further pieces) will need to be
 * cloned, so that rotating a piece in one puzzle won't affect that piece's
 * rotation in other puzzles.
 *
 */
public class PuzzleSolvingExecutor extends AbstractExecutor<Collection<String>, Piece> {

	/**
	 * given the collection of pieces, return a collection of callable
	 * collection of strings (solutions).
	 */
	@Override
	public Collection<Callable<Collection<String>>> getCallableCollection(Collection<Piece> pieces) {
		int columns = 4;
		int rows = 4;
		Collection<Callable<Collection<String>>> callables = new ArrayList<Callable<Collection<String>>>();
		for (Piece piece : pieces) {
			for (int i = 0; i < 4; i++) {
				/*
				 * because asynchronous, going to need to create a fresh set of
				 * pieces for each executor.
				 */
				Piece clonedPiece = piece.clonePiece();
				Puzzle puzzle = new Puzzle(columns, rows);
				Board board = puzzle.getBoard();
				board.setPieceAtLocation(clonedPiece, 0, 0);
				board.setCurrentColumn(1);
				board.setCurrentRow(0);
				Collection<Piece> executorPieces = clonePieceCollection(pieces, clonedPiece.getIdentifier());
				puzzle.setPieces(executorPieces);
				SolutionCallableRetriever retriever = new SolutionCallableRetriever();
				Callable<Collection<String>> callableSolutions = retriever.getSolutionCallable(puzzle);
				callables.add(callableSolutions);
				piece.rotateClockwise();
			}
		}
		return callables;
	}

	private Collection<Piece> clonePieceCollection(Collection<Piece> pieces, String clonedIdentifier) {
		Collection<Piece> executorPieces = new ArrayList<Piece>();
		for (Piece oldPiece : pieces) {
			Piece newPiece = oldPiece.clonePiece();
			if (!newPiece.getIdentifier().equals(clonedIdentifier)) {
				executorPieces.add(newPiece);
			}
		}
		return executorPieces;
	}
}