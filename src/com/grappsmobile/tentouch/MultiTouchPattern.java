package com.grappsmobile.tentouch;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

public class MultiTouchPattern {
	
	public float TargetRadius = 50f;
	
	public float HitRadius = Math.round( TargetRadius / 2 );
	
	public List<Point> Points = new ArrayList<Point>();
	
}
