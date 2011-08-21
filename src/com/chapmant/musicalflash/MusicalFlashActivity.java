package com.chapmant.musicalflash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MusicalFlashActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	TextView tCard;
	ArrayList<String> scalesList;
	boolean question = false;
	int location;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        System.out.println("Setting scale list");
        scalesList = setScalesList();
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
		int size;
		System.out.println("On click");
		if (question) {
			question = false;
			System.out.println("Question true");
			tCard.setText(scalesList.remove(location * 2));
		}
		else {
			System.out.println("Question false");
			question = true;
			size = scalesList.size();
			System.out.println(scalesList);
			if (size == 0) {
				tCard.setText("Press to reset!");
				question = false;
				scalesList = setScalesList();
				return;
			}
			System.out.println("Getting new location");
			location = new Random().nextInt(size / 2);
			System.out.println("Removing from list");
			tCard.setText(scalesList.remove(location * 2));
			System.out.println("Done");
		}
			
	}
}