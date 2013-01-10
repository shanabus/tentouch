package com.grappsmobile.tentouch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /*@Override
	public boolean onTouchEvent(MotionEvent event) {
       	
    	MultiTouchView mv = (MultiTouchView)this.findViewById(R.id.mtView);
    	TextView tv = (TextView) this.findViewById(R.id.txtHelloWorld);
    	
    	if (mv.AllPointsUnlocked) {
    		tv.setText("All points unlocked!");
    	}
    	
    	return true;
    }*/
}
