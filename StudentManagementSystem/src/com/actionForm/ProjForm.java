package com.actionForm;

public class ProjForm {
	private boolean selected = false;
	private String name = "";
	private String teacher = "";
	private String intro = "";

	public void setSelected(boolean bool){
		this.selected = bool;
	}
	
	public boolean getSelected(){
		return selected;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTeacher(String teacher){
		this.teacher = teacher;
	}
	
	public String getTeacher(){
		return teacher;
	}
	
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public String getIntro(){
		return intro;
	}
}