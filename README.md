# UltimatePuzzle
This repository contains a recursive solution to the "Ultimate Puzzle", http://theultimatepuzzle.com/ .
My dad gave me the puzzle several years ago; I'd made some rough attempts to solve it recursively, but not sat down and made a serious attempt until recently.

I decided to approach it with simple recursion, though some friends suggested filtering the pices by known holes/tabs.  Some improvements might be achievable via that approch.  The runtime on my workstation is about 2 seconds, so I don't see too much need to make those improvements.

The test class references how to call the recursize algorithm present in the Puzzle class.  This algorithm would work for larger puzzles (though the additional pieces would need to be added to the Pieces enumeration).
