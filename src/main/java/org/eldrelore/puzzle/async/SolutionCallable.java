package org.eldrelore.puzzle.async;

import java.util.Collection;
import java.util.concurrent.Callable;

import org.eldrelore.puzzle.Puzzle;

/**
 * Callable implementation for solutions
 */
public class SolutionCallable implements Callable<Collection<String>> {

	private Puzzle puzzle;

	public SolutionCallable(Puzzle puzzle) {
		this.puzzle = puzzle;

	}

	@Override
	public Collection<String> call() throws Exception {
		puzzle.solvePuzzle(puzzle.getPieces());
		return puzzle.getSolutions();
	}
}
