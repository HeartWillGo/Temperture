package com.example.ballg;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

 

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

public class BallTimetask extends TimerTask{
	public  List<Message_user> list2 ;
	public ArrayList<Ball> list;
	private DataOutputStream os;// �������������
	private OutputStream output;
	private PrintWriter out;
	private Random rand = new Random();
	public DataOutputStream Os;
	String data;
 
	/* ��������ַ */
	private String SERVER_HOST_IP ;

	/* �������˿� */
	private int SERVER_HOST_PORT = 9999; 
	public Socket socket;
	 
	private MainActivity mainActivity;
	int[] color={Color.BLACK,Color.BLUE,Color.CYAN,Color.DKGRAY,Color.GRAY,Color.GREEN,Color.LTGRAY,Color.MAGENTA,Color.RED,Color.TRANSPARENT,Color.YELLOW};
	public BallTimetask(ArrayList<Ball> list, MainActivity mainActivity,OutputStream output,PrintWriter out,String data,String SERVER_HOST_IP) {
		this.list = list;
		this.mainActivity = mainActivity;
	 os=new DataOutputStream(output);
	 this.output=output;
		this.out=out;
		this.data=data;
		this.SERVER_HOST_IP=SERVER_HOST_IP;
	}
	private Handler hand = new Handler() {
		public void handleMessage(Message mess) {
			 
			Socket socket=(Socket)mess.obj;
			try {
				output = socket.getOutputStream();
				Os=new DataOutputStream(output);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//creatSocket();
		}
	};
	private void creatSocket() {
		try {
		socket = new Socket(SERVER_HOST_IP , SERVER_HOST_PORT);
		 
			System.out.println("��û��socketӴӴӴ" + socket);
			/* ��ȡ����� */
			output = socket.getOutputStream();
			Os=new DataOutputStream(output);
//			 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
//	                    (socket.getOutputStream())), true);
	            //��������
//	            out.println(content);
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			 
//			try {
//				 
//				/************** ������Ϣ ********************/
//				
////				 
//				 
//			//	os.writeInt(temp );
//				String massage="hhhhhhhhhhhhhhhh";
//				byte[] b0 = massage.getBytes();
//				Os.writeInt(b0.length);
//				Os.write(b0); // ������Ϣ
//				//os.write("hhhhhhhhhhhhhhh"); // ������Ϣ
//				System.out.println("������os"+output);
//				System.out.println("temp�Ƕ���  ==="+b0); 
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		ImageView ivBall = (ImageView) mainActivity.findViewById(R.id.view);
	
			int r = 4;
			float width = ivBall.getWidth();
			float height=ivBall.getHeight();
			DecimalFormat df = new DecimalFormat("##.00");
			 
			 
			 float temp=rand.nextFloat()+25+rand.nextInt(6);
			 String temp2= df.format(temp);
			float x = width/8; 
float y=height/2;
			 
				Ball ball = new Ball(x, y, r,temp,Color.RED);
				list.add(ball); 
				 MessageLab  message2=MessageLab.get(this.mainActivity);
		         List<Message_user> masg=message2.getMeaaage(); 
		         list2 =masg;
		         String str=null;
		         if(list.size()>=12&&((list.size()%12)==0)){
		         for(int i=0;i<12;i++){
		        	 Ball ball_msg=list.get(list.size()-i-1);
		        	 if(str==null){
		        	 str=new String().valueOf( ball_msg.getTemp());
		        	 }else{
		        		 String temp3=df.format(ball_msg.getTemp());
		        		 str=str+"/"+temp3;
		        	 }
		         }
		 		 
		         
		 		 
 
			String msg=String.valueOf(0)+ data +"/"+str;
			//String massage="hhhhhhhhhhhhhhhh";
			byte[] b0 = msg.getBytes();
		 
			try {
				creatSocket();
				Os.writeInt(b0.length);
				Os.write(b0);

				 android.os.Message message= new android.os.Message();
//			       
				message.obj = socket;
	 
				hand.sendMessage( message);
			 
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} // ������Ϣ
				
		         } 
					
					
		

	}

}
