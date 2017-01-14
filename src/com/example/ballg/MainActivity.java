package com.example.ballg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ArrayList<Ball> list = new ArrayList<Ball>();

	private ImageView ivImage;
	private Bitmap Bit;
	private Canvas ca;
	private Paint myPaint = new Paint();
	private int width, height;
	private PrintWriter out;
	/* ��������ַ */
	private String SERVER_HOST_IP ;

	/* �������˿� */
	private int SERVER_HOST_PORT = 9999;

	private Socket socket;
	private OutputStream output;
	String data;
 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		ivImage = (ImageView) findViewById(R.id.view);
	//	creatSocket();
		  data=getIntent().getStringExtra("data");
		  SERVER_HOST_IP=getIntent().getStringExtra("IP");
		 BallTimetask BT = new BallTimetask(list, this, output,out,data,SERVER_HOST_IP);
		//  data=getIntent().getStringExtra("data");
//		try {
//			socket = new Socket("192.168.1.101", 8080);
//		} catch (UnknownHostException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		System.out.println("��û��socket�����ɹ�" + socket);
		width = ivImage.getWidth();
		height = ivImage.getHeight();
		Timer timer = new Timer();
		timer.schedule(BT, 1000, 1000);

		BallThread bt = new BallThread(list, ivImage);
		Ball ba = new Ball(width / 2, height / 2, 12, 30, Color.BLUE);

		bt.start();
		
//		try {
//			//socket = new Socket(SERVER_HOST_IP , SERVER_HOST_PORT);
//			socket.setKeepAlive(true);
//           // socket.setSoTimeout(10 * 60 * 1000);
//			System.out.println("��û��socketӴӴӴ" + socket);
//			/* ��ȡ����� */
//			output = socket.getOutputStream();
//			
////			 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
////	                    (socket.getOutputStream())), true);
//	            //��������
////	            out.println(content);
//			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
//			
//			out.println("hhhhhh");
//			try {
//				 
//				/************** ������Ϣ ********************/
//				
////				 
//				 
//			//	os.writeInt(temp );
//				String massage="hhhhhhhhhhhhhhhh";
//				byte[] b0 = massage.getBytes();
//			//	os.writeInt(b0.length);
//				output.write(b0); // ������Ϣ
//				//os.write("hhhhhhhhhhhhhhh"); // ������Ϣ
//				System.out.println("������os"+output);
//				System.out.println("temp�Ƕ���  ==="+b0); 
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	private void creatSocket() {
		try {
			socket = new Socket(SERVER_HOST_IP , SERVER_HOST_PORT);
			socket.setKeepAlive(true);
           // socket.setSoTimeout(10 * 60 * 1000);
			System.out.println("��û��socketӴӴӴ" + socket);
			/* ��ȡ����� */
			output = socket.getOutputStream();
			
//			 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
//	                    (socket.getOutputStream())), true);
	            //��������
//	            out.println(content);
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			out.println("hhhhhh");
			try {
				 
				/************** ������Ϣ ********************/
				
//				 
				 
			//	os.writeInt(temp );
				String massage="hhhhhhhhhhhhhhhh";
				byte[] b0 = massage.getBytes();
			//	os.writeInt(b0.length);
				output.write(b0); // ������Ϣ
				//os.write("hhhhhhhhhhhhhhh"); // ������Ϣ
				System.out.println("������os"+output);
				System.out.println("temp�Ƕ���  ==="+b0); 
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
