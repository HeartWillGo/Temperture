package com.example.ballg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

 

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegestActivity extends Activity {
	public  List<Message_user> list2 ;
	private InputStream is;
	private OutputStream os;
 
	private TextView tex;
	//public Socket socket;
	public String user_name;
	private TextView tv;
	private DataOutputStream output;// 数据输出流对象
	 private DataInputStream input;
	private PrintWriter out;
	private Random rand = new Random();
 
 
	/* 服务器地址 */
	private String SERVER_HOST_IP ;

	/* 服务器端口 */
	private int SERVER_HOST_PORT = 9999; 
	public Socket socket;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regest);
		Resources res = getResources();  
		Drawable drawable = res.getDrawable(R.drawable.under1);  
		this.getWindow().setBackgroundDrawable(drawable); 
		tv=(TextView)findViewById(R.id.textView4);
//		 
		}
	 private Handler handler = new Handler() {  
	        
	        // 当有消息发送出来的时候就执行Handler的这个方法  
	        public void handleMessage(Message msg) {  
	            super.handleMessage(msg);  
	           
	            
	        }  
	 };
	
	public void denglu(View v) {
		EditText user=(EditText)RegestActivity.this.findViewById(R.id.editText1);
		EditText password=(EditText)RegestActivity.this.findViewById(R.id.editText2);
		EditText IP=(EditText)RegestActivity.this.findViewById(R.id.editText3);
		final String Euser=user.getText().toString();
		final String Epassword=password.getText().toString();
		final String IP_add=IP.getText().toString();
		SERVER_HOST_IP=IP_add;
  
		//if(((user.equals(111))&&(password.equals(222)))|| ((user.equals(333))&&(password.equals(444)))||((user.equals(555))&&(password.equals(666)))){
		
            new Thread() {  
                  
                public void run() {  
                    // 执行完毕后给handler发送一个空消息  
                    try {  
                        // 实例化Socket  
                        Socket socket = new Socket(SERVER_HOST_IP, SERVER_HOST_PORT);  
                        // 获得输入流  
                      
                        os=socket.getOutputStream();
                        is=socket.getInputStream();
                        output=new DataOutputStream(os);
                        input=new DataInputStream(is);
                        String msg= 1+Euser+"/"+Epassword;
                        byte[] m=msg.getBytes();
                        output.writeInt(m.length);
                        output.write(m); 
                        
                        
                        
                        try {
            				Thread.sleep(500);
            			} catch (InterruptedException e1) {
            				// TODO Auto-generated catch block
            				e1.printStackTrace();
            			}
            		
                        System.out.println("有没有开始读数据read");
                		String Massage_back = null ;
                		 
                		try {
                			ArrayList Arr = new ArrayList();
                			  
                			int  length = input.readInt(); 
                			  System.out.println("length  =" + length);
                			for (int i = 0; i < length; i++) {

                				/***************** 服务器读取消息 ****************************/
                 	int 	r = input.read(); 
                				 
                				Arr.add((byte) r); 
                			
                			} 
                			 
                			byte[] b = new byte[Arr.size()];
                			/*************************** 将字节信息存到byte数组 ****************************/
                			for (int j = 0; j < b.length; j++) {
                				b[j] = (Byte) Arr.get(j);

                				//System.out.println("b[j]=" + b[j]);

                			}

                			/*************************** 将byte数组转化为字符串 **************************/
                			 
                			 
                			 System.out.println("message="+Massage_back);
                		  Massage_back = new String(b);
                		  tv.setText("message="+Massage_back);
                		  
                		} catch (UnknownHostException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        } catch (IOException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                		 
                        
                    } catch (UnknownHostException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    } catch (IOException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    }  
                    handler.sendEmptyMessage(0); 
                }
                 
            }.start(); 
            

		
	 
////		 
//			Intent intent=new Intent(RegestActivity.this,MainActivity.class);
//			 
//			intent.putExtra("data", Euser);
//			intent.putExtra("IP", IP_add);
//			startActivity(intent);
//		}
//		else
//		{
//			Toast.makeText(RegestActivity.this, "账号或密码输入错误,请重试", Toast.LENGTH_SHORT).show();
//		}
//		}
//		else
//		{
//			Toast.makeText(RegisterActivity.this, "账号或密码输入错误", Toast.LENGTH_SHORT).show();
//		}
		
		// TODO Auto-generated method stub
		
	}

	 
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.regest, menu);
		return true;
	}

	 
}
