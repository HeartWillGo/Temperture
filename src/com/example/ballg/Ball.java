package com.example.ballg;

import java.util.Random;

import android.R.string;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
	private float x, y;
	private int r;
	private int color;
	private float temp;
	private Random Ran; 

	public Ball() {
	}

	public Ball(float  x, float y, int r, float temp,int color) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
		this.temp=temp;
	}

	public void move() {
		 
		x+=25;
	}

	public float getX() {
		return x;
	}

	public void setX(float  x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float  y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void drawBall(Paint paint, Canvas can) {
		
		paint.setColor(color);
		can.drawCircle(x, y, r, paint);
	}
public void drawWrite(Paint paint, Canvas can,int color2) {
		
		paint.setColor(color2);
		can.drawRect(100, 100, 600, 300, paint);
	}
public void drawWrite2(int x1,int y1,int x2 ,int y2,Paint paint, Canvas can,int color2) {
	
	paint.setColor(color2);
	can.drawRect(x1, y1, x2, y2, paint);
}
public void drawLine(float x1,float y1,float x2,float y2,Paint paint, Canvas can,int color2) {
	
	paint.setColor(color2);
	can.drawLine(x1, y1, x2, y2, paint); 
}
 public void drawText(int size,int x,int y,Paint paint, Canvas can,int color2){
	 String str=String.valueOf(size);
	 paint.setColor(color2);
	 
	 can.drawText(str, x, y, paint);
 }
 public void drawText2(String str,float x,float y,Paint paint, Canvas can,int color2){
	  
	 paint.setColor(color2);
	 can.drawText(str, x, y, paint);
 }
public float getTemp() {
	return temp;
}

public void setTemp(float temp) {
	this.temp = temp;
}

 

}
