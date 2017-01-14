package com.example.ballg;

public class Message_user {

	public  String message;
	// public ArrayList<Message> list=new ArrayList<Message>();
	 
	public Message_user (String message){
		this.message=message;
	}
	public String getMessage( ){
		return message;
	}
	public void setMessage(String message){
		this.message=message;
	}
}
