package com.chapmant.musicalflash;

public class Chord {
	// Numerical representations, where C = 0, C# = 1, ... B = 11.
	private int[] notes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
	// The half-steps to the nth note in the scale or chord
	private int[] minorCount = {0, 2, 3, 5, 7, 8, 10};
	private int[] majorCount = {0, 2, 4, 5, 7, 9, 11};
	private int[] majorChord = {0, 4, 7};
	private int[] minorChord = {0, 3, 7};
	private int[] diminChord = {0, 3, 6};
	private int[] agmntChord = {0, 4, 8};
	// Return variables for later
	private int[] finalChord = {0, 0, 0};
	
	public Chord(int key, String chord) {
		// Generic variable to be assigned a global value later
		int[] chordType = {0, 0, 0};
		
		// Decide what kind of chord we're returning based on the input string
		if (chord.equals("maj"))
			chordType = majorChord;
		else if (chord.equals("min"))
			chordType = minorChord;
		else if (chord.equals("dim"))
			chordType = diminChord;
		else if (chord.equals("aug"))
			chordType = agmntChord;
		else
			return; // Probably a better way to handle this FIXIT
		
		// Assign the proper values to the return chord
		finalChord[0] = chordType[0] + key;
		finalChord[1] = chordType[1] + key;
		finalChord[2] = chordType[2] + key;
			
	}
	
	public Chord(int key, int note, String type) {
		// Global variable to be assigned later
		int[] scaleType = {};
		
		// Are we doing a major or minor scale?
		if (type.equals("maj"))
			scaleType = majorCount;
		else if (type.equals("min"))
			scaleType = minorCount;
		
		// Sets the final chord all equal to the same note
		finalChord[0] = finalChord[1] = finalChord[2] = (key + scaleType[note]) % 12;
		System.out.println((key+scaleType[note]));
		System.out.println(finalChord[0]);
	}
	
	public int[] getChord() {
		// Returns the chord generated in the constructor
		return finalChord;
	}
}
