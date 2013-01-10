package com.grappsmobile.tentouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

// found here: http://android-er.blogspot.com/2011/12/detect-multi-touch-for-10-pointers.html

public class MultiTouchGridView extends View {
	 
	public boolean AllPointsUnlocked = false;
	
	public int canvasOffsetX = 0;
	public int canvasOffsetY = 150;
	public int RECT_DIAMETER = 60;
	
	public MultiTouchPattern currentPattern;
	
	MediaPlayer mediaPlayer;
	
	 private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	 private Paint paintTargets = new Paint(Paint.ANTI_ALIAS_FLAG);
	 private Paint paintTargetHit = new Paint(Paint.ANTI_ALIAS_FLAG);
	 
	 final int MAX_NUMBER_OF_POINT = 10;
	 float[] x = new float[MAX_NUMBER_OF_POINT];
	 float[] y = new float[MAX_NUMBER_OF_POINT];
	 boolean[] touching = new boolean[MAX_NUMBER_OF_POINT];
	 
	 final float targetRadius = 55f;
	 final float targetSensitivity = 25f;
	 
	 TextView txtHelloWorld;
	 
	 public MultiTouchGridView(Context context, AttributeSet attrs, int defStyle) {
	  super(context, attrs, defStyle);
	  init();
	 }

	 public MultiTouchGridView(Context context, AttributeSet attrs) {
	  super(context, attrs);
	  init();
	 }

	 public MultiTouchGridView(Context context) {
	  super(context);
	  init();
	 }

	 void init() {
	  paint.setStyle(Paint.Style.STROKE);
	  paint.setStrokeWidth(1);
	  paint.setColor(Color.RED);
	  
	  paintTargets.setStyle(Paint.Style.STROKE);
	  paintTargets.setStrokeWidth(2);
	  paintTargets.setColor(Color.GREEN);
	  
	  paintTargetHit.setStyle(Paint.Style.FILL);	  
	  paintTargetHit.setColor(Color.DKGRAY);
	  
	  //txtActivePoints = (TextView)this.findViewById(R.id.txtActivePoints);
//	  MultiTouchPattern alligatorPattern = new MultiTouchPattern();
//	  alligatorPattern.TargetRadius = 60f;
//	  //alligatorPattern.Points.add( new Point(canvasOffsetX + 300, canvasOffsetY + 100) );
//	  alligatorPattern.Points.add( new Point(canvasOffsetX + 280, canvasOffsetY + 199) );
//	  alligatorPattern.Points.add( new Point(canvasOffsetX + 459, canvasOffsetY + 187) );
//	  alligatorPattern.Points.add( new Point(canvasOffsetX + 149, canvasOffsetY + 440) );
//	  alligatorPattern.Points.add( new Point(canvasOffsetX + 568, canvasOffsetY + 427) );
//	  alligatorPattern.Points.add( new Point(canvasOffsetX + 365, canvasOffsetY + 740) );
//	  alligatorPattern.Points.add( new Point(canvasOffsetX + 70, canvasOffsetY + 902) );
//	  alligatorPattern.Points.add( new Point(canvasOffsetX + 648, canvasOffsetY + 885) );
	  	  
	  //currentPattern = alligatorPattern;
	  
	  MultiTouchPattern gridPattern = new MultiTouchPattern();
	  gridPattern.TargetRadius = 80f;
	  //alligatorPattern.Points.add( new Point(canvasOffsetX + 300, canvasOffsetY + 100) );
	  int rows = 14;
	  for(int i = 0; i <= rows; i+=2)
	  {
		  gridPattern.Points.add( new Point(canvasOffsetX + RECT_DIAMETER * 1, canvasOffsetY + (RECT_DIAMETER * i)) );
		  gridPattern.Points.add( new Point(canvasOffsetX + RECT_DIAMETER * 3, canvasOffsetY + (RECT_DIAMETER * i)) );
		  gridPattern.Points.add( new Point(canvasOffsetX + RECT_DIAMETER * 5, canvasOffsetY + (RECT_DIAMETER * i)) );
		  gridPattern.Points.add( new Point(canvasOffsetX + RECT_DIAMETER * 7, canvasOffsetY + (RECT_DIAMETER * i)) );
		  gridPattern.Points.add( new Point(canvasOffsetX + RECT_DIAMETER * 9, canvasOffsetY + (RECT_DIAMETER * i)) );
		  gridPattern.Points.add( new Point(canvasOffsetX + RECT_DIAMETER * 11, canvasOffsetY + (RECT_DIAMETER * i)) );
		  gridPattern.Points.add( new Point(canvasOffsetX + RECT_DIAMETER * 13, canvasOffsetY + (RECT_DIAMETER * i)) );
		  //gridPattern.Points.add( new Point(canvasOffsetX + RECT_DIAMETER * 15, canvasOffsetY + (RECT_DIAMETER * i)) );
		  //gridPattern.Points.add( new Point(canvasOffsetX + RECT_DIAMETER * 17, canvasOffsetY + (RECT_DIAMETER * i)) );  
	  }
	  
	  currentPattern = gridPattern;
	  
	  //mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.alligator);
	 }

	 @Override
	 protected void onDraw(Canvas canvas) {
  
	  int pointsUnlocked = 0;
	  
	  for(int i = 0; i < MAX_NUMBER_OF_POINT; i++){
	   if(touching[i]){
	    canvas.drawCircle(x[i], y[i], 50f, paint);
   	
	    	for(Point p : currentPattern.Points)
	    	{
	    		if (x[i] > p.x - RECT_DIAMETER && x[i] < p.x + RECT_DIAMETER && y[i] > p.y - RECT_DIAMETER && y[i] < p.y + RECT_DIAMETER)
	    		{
	    			//canvas.drawCircle(p.x, p.y, currentPattern.TargetRadius, paintTargetHit);
	    			canvas.drawRect(p.x - RECT_DIAMETER, p.y - RECT_DIAMETER, p.x + RECT_DIAMETER, p.y + RECT_DIAMETER, paintTargetHit); // left, top, right, bottom
	    			pointsUnlocked++;
	    		}
	    	}
	   }
	  }
	  
	  //txtActivePoints.setText(pointsUnlocked);
	  if (pointsUnlocked >= 4 && AllPointsUnlocked == false)
	  {
		  AllPointsUnlocked = true;
		  /*TextView txtHelloWorld = (TextView)this.findViewById(R.id.txtHelloWorld);
		  txtHelloWorld.setText("You did it! Now something new...");*/
		  //mediaPlayer.start();
		  Toast.makeText(this.getContext(), "You have unlocked a pattern!", Toast.LENGTH_LONG).show();
	  }
	  
	  // show waypoints	  
	  /*canvas.drawCircle(canvasOffsetX + 70, canvasOffsetY + 100, targetRadius, paintTargets);
	  canvas.drawCircle(canvasOffsetX + 310, canvasOffsetY + 280, targetRadius, paintTargets);	 
	  canvas.drawCircle(canvasOffsetX + 120, canvasOffsetY + 470, targetRadius, paintTargets);	*/

//	  for(int i = 0; i < currentPattern.Points.size(); i++){
//		  canvas.drawCircle(currentPattern.Points.get(i).x, currentPattern.Points.get(i).y, currentPattern.TargetRadius, paintTargets);
//	  }
	  for(Point p : currentPattern.Points)
	  {
		//canvas.drawCircle(p.x, p.y, currentPattern.TargetRadius, paintTargets);
		canvas.drawRect(p.x - RECT_DIAMETER, p.y - RECT_DIAMETER, p.x + RECT_DIAMETER, p.y + RECT_DIAMETER, paintTargets); // left, top, right, bottom
	  }
	 }

	 @Override
	 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	  // TODO Auto-generated method stub
	  setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
	 }

	 @Override
	 public boolean onTouchEvent(MotionEvent event) {
	  int action = (event.getAction() & MotionEvent.ACTION_MASK);
	  int pointCount = event.getPointerCount();
	  
	  for (int i = 0; i < pointCount; i++) {
	    int id = event.getPointerId(i);
	    
	    //Ignore pointer higher than our max.
	    if(id < MAX_NUMBER_OF_POINT){
	     x[id] = (int)event.getX(i);
	     y[id] = (int)event.getY(i);
	     
	     if((action == MotionEvent.ACTION_DOWN)
	       ||(action == MotionEvent.ACTION_POINTER_DOWN)
	       ||(action == MotionEvent.ACTION_MOVE)){
	      touching[id] = true;
	     }else{
	      touching[id] = false;
	     }
	    } 
	   }

	  invalidate(); 
	  return true;
	  
	 }

	}