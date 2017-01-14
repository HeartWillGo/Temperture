package com.example.ballg;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

 

public class MessageLab {
	private static MessageLab messageLab;
	private List<Message_user> myMessage;
	public static MessageLab get(Context context){
		if (messageLab==null){
			messageLab=new MessageLab(context);
		}
		return messageLab;
	}
	
	private MessageLab(Context context){
		myMessage=new ArrayList<Message_user > ();
		
	}
	public List<Message_user> getMeaaage(){
		return myMessage;
	}
	

}