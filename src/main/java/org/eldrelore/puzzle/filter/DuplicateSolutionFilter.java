package org.eldrelore.puzzle.filter;

import java.util.ArrayList;
import java.util.List;

import org.eldrelore.puzzle.array.ArrayRotation;
import org.eldrelore.puzzle.dto.Board;

/**
 * Filter out solutions that are duplicates (rotated) within the List of
 * solutions.
 * 
 */
public class DuplicateSolutionFilter {

	private int columns;
	private int rows;

	public DuplicateSolutionFilter(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
	}

	public List<String> filterSolutions(List<String> puzzleSolutions) {
		List<String> filteringSolutions = new ArrayList<String>();
		filteringSolutions.addAll(puzzleSolutions);
		ArrayRotation rotation = new ArrayRotation();
		List<String> rotationRemovals = new ArrayList<String>();
		/* filter out solutions that are the same except for rotation. */
		Board replicaBoard = new Board(columns, rows);
		for (String solution : filteringSolutions) {
			String rotatedSolution = "";
			/* only proceed if this solution isn't marked as duplicate. */
			if (!rotationRemovals.contains(solution)) {
				/*
				 * rotate each solution up to 3 times, to avoid removing all
				 * solutions from list.
				 */
				replicaBoard.fromString(solution);
				for (int i = 0; i < 3; i++) {
					replicaBoard = rotation.rotate(replicaBoard);
					rotatedSolution = replicaBoard.toString();
					if (filteringSolutions.contains(rotatedSolution)) {
						/* mark for removal */
						rotationRemovals.add(rotatedSolution);
					}
				}
			}
		}
		/* remove all solutions marked as duplicates */
		filteringSolutions.removeAll(rotationRemovals);
		return filteringSolutions;
	}
}
