package org.eldrelore.puzzle;

import java.util.ArrayList;
import java.util.List;

import org.eldrelore.puzzle.array.ArrayRotation;
import org.eldrelore.puzzle.dto.Board;
import org.eldrelore.puzzle.dto.Pieces;
import org.eldrelore.puzzle.dto.RotatingPiece;
import org.eldrelore.puzzle.filter.DuplicateSolutionFilter;
import org.eldrelore.puzzle.types.SolutionType;
import org.junit.Test;

import junit.framework.TestCase;

public class TestSimplePuzzle extends TestCase {

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
		List<RotatingPiece> pieces = new ArrayList<RotatingPiece>();
		pieces.add(Pieces.First.getPiece());
		pieces.add(Pieces.Second.getPiece());
		pieces.add(Pieces.Third.getPiece());
		pieces.add(Pieces.Fourth.getPiece());
		Puzzle puzzle = new Puzzle(columns, rows);
		puzzle.solvePuzzle(pieces);
		DuplicateSolutionFilter filter = new DuplicateSolutionFilter(columns, rows);
		List<String> solutions = filter.filterSolutions(puzzle.getSolutions());
		boolean solutionFound = false;
		int fullSolutionCounter = 0;
		System.out.println("Evaluating to see if a solution was found... ");
		for (String solution : solutions) {
			solutionFound = true;
			System.out.println("Solution found: " + solution);
			fullSolutionCounter++;
		}
		assertTrue(solutionFound);
		assertTrue(2 == fullSolutionCounter);
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
		RotatingPiece first = rotatedBoard.getPieceAtLocation(1, 0);
		RotatingPiece second = rotatedBoard.getPieceAtLocation(1, 1);
		RotatingPiece third = rotatedBoard.getPieceAtLocation(0, 1);
		RotatingPiece fourth = rotatedBoard.getPieceAtLocation(0, 0);
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
		RotatingPiece piece = Pieces.First.getPiece();
		for (int i = 0; i < 4; i++) {
			piece.rotateClockwise();
		}
		assertTrue(0 == piece.getRotations());

		board.setPieceAtLocation(piece, 0, 0);
		ArrayRotation rotation = new ArrayRotation();
		for (int i = 0; i < 4; i++) {
			board = rotation.rotate(board);
		}
		RotatingPiece retrievedPiece = board.getPieceAtLocation(0, 0);
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
		List<RotatingPiece> pieces = new ArrayList<RotatingPiece>();
		pieces.add(Pieces.Third.getPiece());
		pieces.add(Pieces.Fourth.getPiece());
		pieces.add(Pieces.First.getPiece());
		pieces.add(Pieces.Second.getPiece());
		Puzzle puzzle = new Puzzle(columns, rows);
		puzzle.solvePuzzle(pieces);
		DuplicateSolutionFilter filter = new DuplicateSolutionFilter(columns, rows);
		List<String> solutions = filter.filterSolutions(puzzle.getSolutions());
		boolean solutionFound = false;
		System.out.println("Evaluating to see if a solution was found... ");
		for (String solution : solutions) {
			solutionFound = true;
			System.out.println("Solution found: " + solution);
		}
		assertTrue(solutionFound);
	}

	/**
	 * Test for full puzzle, and solutions
	 */
	@Test
	public void testFullPuzzle() {
		int columns = 4;
		int rows = 4;
		List<RotatingPiece> pieces = new ArrayList<RotatingPiece>();
		pieces.add(Pieces.First.getPiece());
		pieces.add(Pieces.Second.getPiece());
		pieces.add(Pieces.Third.getPiece());
		pieces.add(Pieces.Fourth.getPiece());
		pieces.add(Pieces.Fifth.getPiece());
		pieces.add(Pieces.Sixth.getPiece());
		pieces.add(Pieces.Seventh.getPiece());
		pieces.add(Pieces.Eighth.getPiece());
		pieces.add(Pieces.Ninth.getPiece());
		pieces.add(Pieces.Tenth.getPiece());
		pieces.add(Pieces.Eleventh.getPiece());
		pieces.add(Pieces.Twelth.getPiece());
		pieces.add(Pieces.Thirteenth.getPiece());
		pieces.add(Pieces.Fourteenth.getPiece());
		pieces.add(Pieces.Fifteenth.getPiece());
		pieces.add(Pieces.Sixteenth.getPiece());
		Puzzle puzzle = new Puzzle(columns, rows);
		puzzle.solvePuzzle(pieces);
		DuplicateSolutionFilter filter = new DuplicateSolutionFilter(columns, rows);
		List<String> solutions = filter.filterSolutions(puzzle.getSolutions());
		boolean solutionFound = false;
		System.out.println(solutions.size() + " solutions found.");
		int counter = 0;
		for (String solution : solutions) {
			counter++;
			solutionFound = true;
			System.out.println("Solution " + counter + ": " + solution);
		}
		assertTrue(solutionFound);
	}

	/**
	 * test filtering solutions from duplicates (depending only on rotation)
	 */
	@Test
	public void testFilterSolutions() {
		int columns = 4;
		int rows = 4;
		List<String> solutions = new ArrayList<String>();
		for (SolutionType solution : SolutionType.values()) {
			solutions.add(solution.getSolution());
		}
		DuplicateSolutionFilter filter = new DuplicateSolutionFilter(columns, rows);
		List<String> filteredSolutions = filter.filterSolutions(solutions);
		System.out.println("Filtering list size after filtering: " + filteredSolutions.size());
		assertTrue(filteredSolutions.size() == solutions.size() / 4);

	}

	/**
	 * test board deserialization
	 */
	@Test
	public void testFromString() {
		String solutionString = SolutionType.AR.getSolution();

		Board b = new Board(4, 4);
		b.fromString(solutionString);
		String importedString = b.toString();
		assertTrue(solutionString.equals(importedString));
	}
}