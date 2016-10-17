# UltimatePuzzle
This repository contains a recursive solution to the "Ultimate Puzzle", http://theultimatepuzzle.com/ .
My dad gave me the puzzle several years ago; I'd made some rough attempts to solve it recursively, but not sat down and made a serious attempt until recently.

I decided to approach it with simple recursion, though some friends suggested filtering the pices by known holes/tabs.  Some improvements might be achievable via that approch.  The runtime on my workstation is about 2 seconds, so I don't see too much need to make those improvements.

This algorithm would work for larger puzzles (though the additional pieces would need to be added to the Pieces enumeration).

Example code (Extracted from test class):

	 	int columns = 4;
		int rows = 4;
		Collection<Piece> pieces = getPieces();
		Puzzle puzzle = new Puzzle(columns, rows);
		puzzle.solvePuzzle(pieces);
		Collection<String> filteredSolutions = filterDuplicateSolutions(columns, rows, puzzle.getSolutions());
		assertTrue(filteredSolutions.size() > 0);
		int counter = 0;
		System.out.println(filteredSolutions.size() + " solutions found.");
		for (String solution : solutions) {
			counter++;
			System.out.println("Solution " + counter + ": " + solution);
		}
