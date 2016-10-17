package org.eldrelore.puzzle.async;

import java.util.Collection;
import java.util.concurrent.Callable;

import org.eldrelore.puzzle.Puzzle;

public class SolutionCallableRetriever {
	public Callable<Collection<String>> getSolutionCallable(Puzzle puzzle) {
		return new SolutionCallable(puzzle);
	}
}
