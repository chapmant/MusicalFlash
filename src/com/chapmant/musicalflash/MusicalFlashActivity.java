package com.chapmant.musicalflash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MusicalFlashActivity extends Activity{
    // Set up the variables for the layout
	TextView tChord, tScale;
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
        
        // Set the window to have no title, and fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        // Select the view, and assign the variables and listeners
        setContentView(R.layout.menu);
        System.out.println("Setting view and listener");
        
        tChord = (TextView) findViewById(R.id.tChord);
        tChord.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
				startActivity(new Intent("com.chapmant.musicalflash.CHORDS"));
				finish();
        	}
        });
   
        tScale = (TextView) findViewById(R.id.tScale);
        tScale.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
				startActivity(new Intent("com.chapmant.musicalflash.SCALES"));
        		finish();
        	}
        });
        
        System.out.println("Done");
    }
}