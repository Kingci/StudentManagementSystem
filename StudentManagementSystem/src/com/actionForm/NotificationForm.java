package com.actionForm;

public class NotificationForm {
	private int notificationId;
	private String authorId;
	private int collegeId;
	private String title;
	private String content;
	
	public int getNotificationId(){
		return this.notificationId;
	}
	public String getAuthorId(){
		return this.authorId;
	}
	public int getCollegeId(){
		return this.collegeId;
	}
	public String getTitle(){
		return this.title;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setNotificationId(int notificationId){
		this.notificationId = notificationId;
	}
	public void setAuthorId(String authorId){
		this.authorId = authorId;
	}
	public void setCollegeId(int collegeId){
		this.collegeId = collegeId;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setContent(String content){
		this.content = content;
	}
}
