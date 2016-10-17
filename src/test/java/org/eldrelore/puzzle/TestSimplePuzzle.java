package org.eldrelore.puzzle;

import java.util.ArrayList;
import java.util.Collection;

import org.eldrelore.puzzle.array.ArrayRotation;
import org.eldrelore.puzzle.async.PuzzleSolvingExecutor;
import org.eldrelore.puzzle.dto.Board;
import org.eldrelore.puzzle.dto.Piece;
import org.eldrelore.puzzle.dto.Pieces;
import org.eldrelore.puzzle.filter.DuplicateSolutionFilter;
import org.eldrelore.puzzle.types.SolutionType;
import org.junit.Test;

import junit.framework.TestCase;

public class TestSimplePuzzle extends TestCase {

	/**
	 * Test for full puzzle, and solutions
	 */
	@Test
	public void testFullPuzzle() {
		int columns = 4;
		int rows = 4;
		Collection<Piece> pieces = getPieces();
		Puzzle puzzle = new Puzzle(columns, rows);
		puzzle.solvePuzzle(pieces);
		Collection<String> filteredSolutions = filterDuplicateSolutions(columns, rows, puzzle.getSolutions());

		assertTrue(filteredSolutions.size() > 0);
		displaySolutions(filteredSolutions);
	}

	/**
	 * quick test on recursion. 2 separate solutions for these pieces, so 8
	 * total (as it gives 4 for each real solution, just different rotations in
	 * different spots). 6 of these should be filtered out as duplicates,
	 * leaving 2 full solutions.
	 */
	@Test
	public void testSimplePuzzle() {
		int columns = 2;
		int rows = 2;
		Collection<Piece> pieces = new ArrayList<Piece>();
		pieces.add(Pieces.First.getPiece());
		pieces.add(Pieces.Second.getPiece());
		pieces.add(Pieces.Third.getPiece());
		pieces.add(Pieces.Fourth.getPiece());
		Puzzle puzzle = new Puzzle(columns, rows);
		puzzle.solvePuzzle(pieces);
		DuplicateSolutionFilter filter = new DuplicateSolutionFilter(columns, rows);
		Collection<String> solutions = filter.filterSolutions(puzzle.getSolutions());
		displaySolutions(solutions);
		assertTrue(!solutions.isEmpty());
		assertTrue(2 == solutions.size());
	}

	/**
	 * Test rotating a 2x2 board
	 */
	@Test
	public void testRotateArray() {
		Board board = new Board(2, 2);
		board.setPieceAtLocation(Pieces.First.getPiece(), 0, 0);
		board.setPieceAtLocation(Pieces.Second.getPiece(), 1, 0);
		board.setPieceAtLocation(Pieces.Third.getPiece(), 1, 1);
		board.setPieceAtLocation(Pieces.Fourth.getPiece(), 0, 1);
		ArrayRotation rotation = new ArrayRotation();
		Board rotatedBoard = rotation.rotate(board);
		Piece first = rotatedBoard.getPieceAtLocation(1, 0);
		Piece second = rotatedBoard.getPieceAtLocation(1, 1);
		Piece third = rotatedBoard.getPieceAtLocation(0, 1);
		Piece fourth = rotatedBoard.getPieceAtLocation(0, 0);
		assertTrue(first.getIdentifier().equals(Pieces.First.getPiece().getIdentifier()));
		assertTrue(1 == first.getRotations());
		assertTrue(second.getIdentifier().equals(Pieces.Second.getPiece().getIdentifier()));
		assertTrue(1 == second.getRotations());
		assertTrue(third.getIdentifier().equals(Pieces.Third.getPiece().getIdentifier()));
		assertTrue(1 == third.getRotations());
		assertTrue(fourth.getIdentifier().equals(Pieces.Fourth.getPiece().getIdentifier()));
		assertTrue(1 == fourth.getRotations());
	}

	/**
	 * test a 1x1 board rotation, make sure the piece rotates correctly.
	 */
	@Test
	public void testSimpleBoardRotation() {
		Board board = new Board(1, 1);
		Piece piece = Pieces.First.getPiece();
		for (int i = 0; i < 4; i++) {
			piece.rotateClockwise();
		}
		assertTrue(0 == piece.getRotations());

		board.setPieceAtLocation(piece, 0, 0);
		ArrayRotation rotation = new ArrayRotation();
		for (int i = 0; i < 4; i++) {
			board = rotation.rotate(board);
		}
		Piece retrievedPiece = board.getPieceAtLocation(0, 0);
		assertTrue(0 == retrievedPiece.getRotations());
	}

	/**
	 * test rotating a 4x4 solution
	 */
	@Test
	public void testSolutionRotation() {
		String solution = SolutionType.AR.getSolution();
		Board b = new Board(4, 4);
		b.fromString(solution);
		ArrayRotation rotation = new ArrayRotation();
		for (int i = 0; i < 4; i++) {
			b = rotation.rotate(b);
			String rotatedSolution = b.toString();
			System.out.println("Rotated Solution: " + rotatedSolution);
		}
		String rotatedSolution = b.toString();
		assertTrue(solution.equals(rotatedSolution));
	}

	/** test a 2x2 puzzle, with pieces out of order */
	@Test
	public void testPiecesOutOfOrder() {
		int columns = 2;
		int rows = 2;
		Collection<Piece> pieces = new ArrayList<Piece>();
		pieces.add(Pieces.Third.getPiece());
		pieces.add(Pieces.Fourth.getPiece());
		pieces.add(Pieces.First.getPiece());
		pieces.add(Pieces.Second.getPiece());
		Puzzle puzzle = new Puzzle(columns, rows);
		puzzle.solvePuzzle(pieces);
		Collection<String> solutions = filterDuplicateSolutions(columns, rows, puzzle.getSolutions());
		displaySolutions(solutions);
		assertTrue(!solutions.isEmpty());
	}

	private Collection<Piece> getPieces() {
		Collection<Piece> pieces = new ArrayList<Piece>();
		for (Pieces pieceEnum : Pieces.values()) {
			pieces.add(pieceEnum.getPiece());
		}
		return pieces;
	}

	/**
	 * test filtering solutions from duplicates (depending only on rotation)
	 */
	@Test
	public void testFilterSolutions() {
		int columns = 4;
		int rows = 4;
		Collection<String> solutions = new ArrayList<String>();
		for (SolutionType solution : SolutionType.values()) {
			solutions.add(solution.getSolution());
		}
		Collection<String> filteredSolutions = filterDuplicateSolutions(columns, rows, solutions);
		displaySolutions(filteredSolutions);
		assertTrue(filteredSolutions.size() == solutions.size() / 4);

	}

	/**
	 * test board creation from string representation
	 */
	@Test
	public void testFromString() {
		String solutionString = SolutionType.AR.getSolution();

		Board b = new Board(4, 4);
		b.fromString(solutionString);
		String importedString = b.toString();
		assertTrue(solutionString.equals(importedString));
	}

	private Collection<String> filterDuplicateSolutions(int columns, int rows, Collection<String> solutions) {
		DuplicateSolutionFilter filter = new DuplicateSolutionFilter(columns, rows);
		Collection<String> filteredSolutions = filter.filterSolutions(solutions);
		return filteredSolutions;

	}

	/**
	 * Lot of work to add executors, especially with the cloning required in the
	 * collection of pieces. Cuts duration in about half (at the cost of
	 * additional memory for all those additional pieces).
	 */
	@Test
	public void testExecutorBasedFullSolution() {
		int columns = 4;
		int rows = 4;
		PuzzleSolvingExecutor puzzleExecutor = new PuzzleSolvingExecutor();
		Collection<Piece> pieces = getPieces();
		Collection<Collection<String>> allSolutions = puzzleExecutor.execute(pieces);

		/*
		 * merge this collection of string collections into a single collection
		 * of strings. If the collection type used for that merge isn't a set,
		 * it will need to be filtered for duplicates. Since this approach
		 * requires filtering for rotational duplicates anyway...
		 */
		Collection<String> solutions = new ArrayList<String>();
		for (Collection<String> incompleteSetOfSolutions : allSolutions) {
			if (null != incompleteSetOfSolutions) {
				for (String possibleDuplicateSolution : incompleteSetOfSolutions) {
					solutions.add(possibleDuplicateSolution);
				}
			}
		}
		Collection<String> filteredSolutions = filterDuplicateSolutions(columns, rows, solutions);
		assertTrue(filteredSolutions.size() > 0);
		/*
		 * Yes, this is cheating, I know how many are in the final solution, and
		 * can cross-check that result.
		 */
		assertTrue(12 == filteredSolutions.size());
		displaySolutions(filteredSolutions);
	}

	/**
	 * display solutions
	 * 
	 * @param solutions
	 */
	private void displaySolutions(Collection<String> solutions) {
		int counter = 0;
		System.out.println(solutions + " solutions found.");
		for (String solution : solutions) {
			counter++;
			System.out.println("Solution " + counter + ": " + solution);
		}
	}
}