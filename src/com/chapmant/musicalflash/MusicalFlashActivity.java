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
    	
    	list.add("1st of C");
    	list.add("C");
    	list.add("2nd of C");
    	list.add("D");
    	list.add("3rd of C");
    	list.add("E");
    	list.add("4th of C");
    	list.add("F");
    	list.add("5th of C");
    	list.add("G");
    	list.add("6th of C");
    	list.add("A");
    	list.add("7th of C");
    	list.add("B");
    	
    	
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