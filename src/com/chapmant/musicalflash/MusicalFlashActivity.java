package com.chapmant.musicalflash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

public class MusicalFlashActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	TextView tCard;
	ArrayList<String> scalesList;
	
	private String[] sharpNotes     = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private String[] flatNotes      = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
	private int[]    sharpMajScales = {0, 7, 2, 9, 4, 11, 6, 1, 8};
	private int[]    flatsMajScales = {0, 5, 10, 3, 8, 1, 6, 11, 4};
	private boolean  question = false;
	private String   answer;
	private int      location;
	private int		 key;
	private int      num;
	private int      accents; // 1 if flat, 0 if sharp
	
	private Random randomGen = new Random();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main);
        //System.out.println("Setting scale list");
        //scalesList = setScalesList();
        System.out.println("Setting view and listener");
        
        tCard = (TextView) findViewById(R.id.tCard);
        tCard.setOnClickListener(this);
        System.out.println("Done");
    }
    
    protected ArrayList<String> setScalesList() {
    	ArrayList<String> list = new ArrayList<String>();
    	
    	String[] notes      = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    	String[] majorSharp = {"C", "G", "D", "A", "E", "B", "F#", "C#", "G#"};
    	int[]    steps      = {0, 2, 2, 1, 2, 2, 2};
    	
    	for (String scale : majorSharp) {
    		int count =  0;
    		int loc   = -1;
    		int len   = notes.length;
    		
    		for (int i = 0; i < len; ++i) {
    			if (scale.compareTo(notes[i]) > 0) {
    				loc = i;
    				break;
    			}
    		}
    		
    		for (int i = 0; i < 7; ++i) {
    			if (count == steps[i]) {
    				list.add("Note " + i+1 + " of " + scale);
    				list.add(notes[loc % len]);
    				count = 0;
    			}
    			++count;
    			++loc;
    		}
    	}
    	
    	return list;
    }

	@Override
	public void onClick(View v) {
		key     = randomGen.nextInt(9);
		num     = randomGen.nextInt(7);
		accents = randomGen.nextInt(2);
		
		int[] scale = {};
		Chord note;
		String[] notes = {};
		
		if (accents == 0) {
			scale = sharpMajScales;
			notes = sharpNotes;
		}
		else if (accents == 1) {
			scale = flatsMajScales;
			notes = flatNotes;
		}
		
		System.out.println("On click");
		if (question) {
			question = false;
			tCard.setText(answer);
		}
		else {
			question = true;
			note = new Chord(scale[key], num, "maj");
			System.out.println("Setting answer");
			System.out.println(accents + " " + num + " " + scale[key]);
			System.out.println(note.getChord());
			answer = notes[note.getChord()[0]];
			tCard.setText("Note " + (num+1) + " in key " + notes[scale[key]]);
			
		}
			
	}
}