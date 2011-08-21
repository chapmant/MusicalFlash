package com.chapmant.musicalflash;

public class Chord {
	private int[] notes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
	private int[] minorCount = {0, 2, 3, 5, 7, 8, 10};
	private int[] majorCount = {0, 2, 4, 5, 7, 9, 11};
	private int[] majorChord = {0, 4, 7};
	private int[] minorChord = {0, 3, 7};
	private int[] diminChord = {0, 3, 6};
	private int[] agmntChord = {0, 4, 8};
	private int[] finalChord = {0, 0, 0};
	private int[] singleNote = {0, 0, 0};
	
	public Chord(int key, String chord) {
		int[] chordType = {0, 0, 0};
		
		if (chord.equals("maj"))
			chordType = majorChord;
		else if (chord.equals("min"))
			chordType = minorChord;
		else if (chord.equals("dim"))
			chordType = diminChord;
		else if (chord.equals("aug"))
			chordType = agmntChord;
		else
			return;
		
		finalChord[0] = chordType[0] + key;
		finalChord[1] = chordType[1] + key;
		finalChord[2] = chordType[2] + key;
			
	}
	
	public Chord(int key, int note, String type) {
		int[] scaleType;
		if (type.equals("maj"))
			scaleType = majorCount;
		else if (type.equals("min"))
			scaleType = minorCount;
		else
			return;
		
		finalChord[0] = finalChord[1] = finalChord[2] = key + scaleType[note] % 12;
	}
	
	public int[] getChord() {
		return finalChord;
	}
}
