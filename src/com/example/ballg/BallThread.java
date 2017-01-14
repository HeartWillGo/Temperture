package com.example.ballg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class BallThread extends Thread {
	private ArrayList<Ball> list; 
	private Random ran=new Random();
	private PrintWriter out;
	/* 服务器地址 */
	 
	public Socket socket;
	private OutputStream output;
	public Bitmap bit;
	public Canvas can;
	public Canvas can2=can;
	public Paint paint = new Paint();
	public ImageView image;
	private Paint pan2=new Paint();
	private int width,height;
	private Handler hand = new Handler() {
		public void handleMessage(Message mess) {
			Bitmap bit = (Bitmap) mess.obj;
			image.setImageBitmap(bit);
			//Socket socket=(Socket)mess.obj;
			//creatSocket();
		}
	};
//	private Handler hand2 = new Handler() {
//		public void handleMessage(Message mess) {
////			Bitmap bit = (Bitmap) mess.obj;
////			image.setImageBitmap(bit);
//			Socket socket=(Socket)mess.obj;
//			//creatSocket();
//		}
//	};
 public BallThread(ArrayList<Ball> list, ImageView image) {
		this.list = list;
		this.image = image;
	//	creatSocket();
		 
	}

	public Bitmap getBitmap() {
		return bit;
	}
	 

	@Override
	public void run() {
		

		while (true) {
			
			if (bit == null && image.getWidth() > 0) {
//				if(socket==null){
//					creatSocket();
//				}
				bit = Bitmap.createBitmap(image.getWidth(), image.getHeight(),
						Config.ARGB_8888);
				 width = image.getWidth();
				 height = image.getHeight();
				can = new Canvas(bit);
				 
				pan2.setColor(Color.WHITE);
				can.drawRect(0, 0, width, height, pan2);
				//can.drawLine(50, 3 * height / 4, 50, height / 4, paint);
				 

				//can.drawLine(50, 3 * height / 4, 7 * width / 8, 3 * height / 4,
						//paint);
			}
			long cur = System.currentTimeMillis();
 
			if(list.size()>12){ 
			 for(int i=0;i<13;i++){
				 
				 Ball ball=list.get(list.size()-1-i);
				 
					 ball.setX(7*width/8-i*width/16);
					float cout=ball.getTemp();
					 ball.setY((10-cout+25)*height/12);
					 if(i==0){
						 ball.drawWrite2(width/8-5,height/3-5,7*width/8+5,height*5/6+5,paint, can,Color.WHITE);
						 ball.drawWrite2(width/8-5,5*height/6+3,7*width/8+20,height+40,paint, can,Color.WHITE);
						 ball.drawWrite2(width/8-10,1*height/6-10,7*width/8+10,height/3-10,paint, can,Color.WHITE);
						 ball.drawWrite2(0,1*height/3-10,1*width/8-5,5*height/6+10,paint, can,Color.WHITE);
						 ball.drawWrite2(0,11*height/12-10,width,height,paint, can,Color.GREEN);
						 ball.drawWrite2(0,0,width,height/12,paint, can,Color.GREEN);
					 }
					 else{
						 Ball ball_old=list.get(list.size()-1-i+1);
					
						 ball.drawLine(ball_old.getX(), ball_old.getY(), ball.getX(), ball.getY(), paint, can,Color.RED);
						 
					 }
					 if(i==13-1){
						 paint.setTextSize(20);
						 ball.drawText2("当前温度",width/8+10, height/6+20,paint,can,Color.MAGENTA);
						 ball.drawText2("时间",7*width/8-10, height/6+20,paint,can,Color.MAGENTA);
						 paint.setTextSize(15);
 DecimalFormat df = new DecimalFormat("##.00"); 
						 String temp2= df.format(ball.getTemp());
						 ball.drawText2(temp2, width/8+10, height/3-50,paint,can,Color.GRAY);
						 ball.drawText( list.size(),7*width/8-10, height/3-50,paint,can,Color.GRAY);
					 }
					 for(int i2=0;i2<7;i2++){
						 ball.drawText( i2+25,1*width/8-25, (10-i2)*height/12,paint,can,Color.BLACK);
						 ball.drawLine(1*width/8, (10-i2)*height/12, 7*width/8, (10-i2)*height/12, paint, can,Color.YELLOW);
					 }
					 ball.drawText2(new String().valueOf(i),ball.getX(),5*height/6+20,paint,can,Color.GRAY);
				 ball.drawBall(paint, can);
			 }
			}
			else{
				for(int i=0;i<list.size();i++){
					 Ball ball=list.get(i);
					 ball.setX(7*width/8-(12-i)*width/16);
					 float cout=ball.getTemp();
					 System.out.println("count   ="+cout);
					 ball.setY((10-cout+25)*height/12);
					 if(i==0){
						 ball.drawWrite2(width/8-5,height/3-5,7*width/8+5,height*5/6+5,paint, can,Color.WHITE);
						 ball.drawWrite2(width/8-5,5*height/6+3,7*width/8+20,height+40,paint, can,Color.WHITE);
						 ball.drawWrite2(width/8-10,1*height/6-10,7*width/8+10,height/3-10,paint, can,Color.WHITE);
						 ball.drawWrite2(0,1*height/3-10,1*width/8-5,5*height/6+10,paint, can,Color.WHITE);
						 ball.drawWrite2(0,11*height/12-10,width,height,paint, can,Color.GREEN);
						 ball.drawWrite2(0,0,width,height/12,paint, can,Color.GREEN);
					 }
					 else{
						 Ball ball_old=list.get(i-1);
						 ball.drawLine(ball_old.getX(), ball_old.getY(), ball.getX(), ball.getY(), paint, can,Color.RED);
					 }
					 if(i==list.size()-1){
						 paint.setTextSize(20);
						 ball.drawText2("当前温度",width/8+10, height/6+20,paint,can,Color.MAGENTA);
						 ball.drawText2("时间",7*width/8-10, height/6+20,paint,can,Color.MAGENTA);
						 paint.setTextSize(15);
						 DecimalFormat df = new DecimalFormat("##.00"); 
						 String temp2= df.format(ball.getTemp());
						 ball.drawText2(temp2  , width/8+10, height/3-50,paint,can,Color.GRAY);
						 ball.drawText( i,7*width/8-10, height/3-50,paint,can,Color.GRAY);
						 for(int i2=0;i2<7;i2++){
							 ball.drawText( i2+25,1*width/8-25, (10-i2)*height/12,paint,can,Color.BLACK);
							 ball.drawLine(1*width/8, (10-i2)*height/12, 7*width/8, (10-i2)*height/12, paint, can,Color.YELLOW);
							// ball.drawWrite2(0,1*height/2-10,width,height,paint, can,Color.GREEN);
						 }
					 }
						
					 ball.drawText2(new String().valueOf(i),ball.getX(),5*height/6+20,paint,can,Color.GRAY);
					 ball.drawBall(paint, can);
				 }
			}
//			ArrayList list_msg=new ArrayList();
// 			Ball ball_msg=list.get(list.size()-1);
////			list_msg.add(0);
////			list_msg.add(list.size()-1);
////			list_msg.add(ball_msg.getTemp());
//			String msg=String.valueOf(0)+String.valueOf(list.size()-1)+String.valueOf(ball_msg.getTemp());
//			//String massage="hhhhhhhhhhhhhhhh";
//			byte[] b0 = msg.getBytes();
//		//	os.writeInt(b0.length);
//			try {
//				output.write(b0);
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			} // 发送消息
			// android.os.Message message= new android.os.Message();
//			 try{
//			        System.out.println("== 进入try ==");
//			        creatSocket();
//			        //数据回调给handler处理
//			       
//			        //}
//
//			       // hand.sendMessage(message);
//			            }
//			 catch(Exception e){
//				 e.printStackTrace();
//			 }
			Message mess = Message.obtain();
//			 android.os.Message message= new android.os.Message();
//		        message.what=0x567;
//		        //if(strResult!=null){
//		            android.os.Bundle data=new android.os.Bundle();
//		            //随便发送
//		            data.putString("abc","要发送的数据");
//		            message.setData(data);
			mess.obj = bit;
//message.obj=socket;
//hand2.sendMessage(message);
			hand.sendMessage( mess);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
