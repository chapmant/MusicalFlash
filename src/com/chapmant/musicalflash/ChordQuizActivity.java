package com.chapmant.musicalflash;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChordQuizActivity extends Activity implements OnClickListener {
	TextView tCard;
	LinearLayout tBack;
	
	private String[]  sharpNotes     = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private String[]  flatNotes      = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
	private int[]     sharpMajScales = {0, 7, 2, 9, 4, 11, 6, 1, 8};
	private int[]     flatsMajScales = {0, 5, 10, 3, 8, 1, 6, 11, 4};
	// 9 scales, 4 types of chords, two types of keys.
	// chord 0 = Maj, 1 = min, 2 = dim, 3 = aug
	private int[][][] used			 = new int[9][4][2];
	private boolean   question       = false;
	private String    answer;
	int				  count			 = 0;
	int				  finishedCount	 = 72;
	private int		  key            = 0;
	private int       chord          = 0;
	private int       accent; // 1 if flat, 0 if sharp
	
	// Since we use this three times per click, probably better to allocate only one, new isn't free!
	private Random randomGen = new Random();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.chords);
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
        int height = 4;
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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (count == finishedCount) {
			resetUsed();
		}
		
		if (question) {
			question = false;
			tCard.setText(answer);
		}
		else {
			do {
				key    = randomGen.nextInt(9);
				chord  = randomGen.nextInt(4);
				accent = randomGen.nextInt(2);
			} while (used[key][chord][accent] == 1);
			used[key][chord][accent] = 1;
			++count;
			
			// Generic variables, assigned to a global later
			int[] scale = {};
			Chord triplet;
			String[] notes = {};
			String type;
			
			if (accent == 0) {
				scale = sharpMajScales;
				notes = sharpNotes;
			}
			else {
				scale = flatsMajScales;
				notes = flatNotes;
			}
			
			if (chord == 0)
				type = "maj";
			else if (chord == 1)
				type = "min";
			else if (chord == 2)
				type = "dim";
			else
				type = "aug";
			question = true;
			
			triplet = new Chord(scale[key], type);
			System.out.println("Setting answer");
			System.out.println(accent + " " + scale[key] + " " + type);
			System.out.println(triplet.getChord());
			System.out.println(triplet.getChord()[0] + " " + triplet.getChord()[1] + " " + triplet.getChord()[2]);
			answer = notes[triplet.getChord()[0] % 12] + " " + notes[triplet.getChord()[1] % 12] + " " + notes[triplet.getChord()[2] % 12];
			
			tCard.setText(notes[scale[key]] + " " + type + " chord");
		}
		
	}

}
