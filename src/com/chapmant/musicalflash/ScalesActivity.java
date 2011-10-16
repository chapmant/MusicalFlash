package com.chapmant.musicalflash;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScalesActivity extends Activity implements OnClickListener {
    // Set up the variables for the layout
	TextView tCard;
	LinearLayout tBack;
	ArrayList<String> scalesList;
	
	// All the variables to do the musical-math
	private String[]  sharpNotes     = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private String[]  flatNotes      = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
	private int[]     sharpMajScales = {0, 7, 2, 9, 4, 11, 6, 1, 8};
	private int[]     flatsMajScales = {0, 5, 10, 3, 8, 1, 6, 11, 4};
	private int[][][] used           = new int[9][7][2];
	private boolean   question       = false;
	private String    answer;
	private int       count          = 0;
	private int       finishedCount  = 126;
	private int		  key            = 0;
	private int       num            = 0;
	private int       accents; // 1 if flat, 0 if sharp
	
	// Since we use this three times per click, probably better to allocate only one, new isn't free!
	private Random randomGen = new Random();	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Makes sure the used array is full of zeroes
        resetUsed();
        
        // Set the window to have no title, and fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        // Select the view, and assign the variables and listeners
        setContentView(R.layout.main);
        System.out.println("Setting view and listener");
        
        tCard = (TextView) findViewById(R.id.tCard);
        tCard.setOnClickListener(this);
        
        tBack = (LinearLayout) findViewById(R.id.tBack);
        tBack.setOnClickListener(this);
        
        System.out.println("Done");
    }
    
    private void resetUsed() {
    	// Dimensions of the array, for the optimized for loop
        int length = 9;
        int height = 7;
        int width  = 2;
        
        // Reset the number of used notes
        count = 0;
        
        // Set the entire 3D array to 0
        for (int i = 0; i < length; ++i) {
        	for (int j = 0; j < height; ++j) {
        		for (int k = 0; k < width; ++k) {
        			used[i][j][k] = 0;
        		}
        	}
        }
    }
    
    private String getEnding(int number) {
    	// Returns the approrpiate ending to a number, ie "1st, 2nd..."
    	if (number == 1)
    		return "st";
    	else if (number == 2)
    		return "nd";
    	else if (number == 3)
    		return "rd";
    	else
    		return "th";
    }

	@Override
	public void onClick(View v) {
		// If all the notes have been tested, reset the test and start over
		if (count == finishedCount) {
			resetUsed();
		}

		System.out.println("On click");
		// If the question has been posed, display the answer on this click
		if (question) {
			question = false;
			// Answer is saved in the else block from earlier
			tCard.setText(answer);
		}
		else {
			// Otherwise, select a new question, and present it to the user
			// Generates a new note selection, checking it against the used
			// notes
			do {
				key     = randomGen.nextInt(9);
				num     = randomGen.nextInt(7);
				accents = randomGen.nextInt(2);
			} while (used[key][num][accents] == 1);
			// Increment count, and mark the note's block as used
			used[key][num][accents] = 1;
			++count;
			
			// Generic variables, assigned to a global later
			int[] scale = {};
			Chord note;
			String[] notes = {};
			
			// Decide if we're doing a sharp or flat scale
			if (accents == 0) {
				scale = sharpMajScales;
				notes = sharpNotes;
			}
			else if (accents == 1) {
				scale = flatsMajScales;
				notes = flatNotes;
			}
			
			// Mark that we're presenting a question card
			question = true;
			
			// Generate the chord, and save the answer for the next click
			note = new Chord(scale[key], num, "maj");
			System.out.println("Setting answer");
			System.out.println(accents + " " + num + " " + scale[key]);
			System.out.println(note.getChord());
			answer = notes[note.getChord()[0]];
	        
			// Display the question on screen
			tCard.setText("Note " + (num+1) + " in key " + notes[scale[key]]);
			
		}
			
	}
}