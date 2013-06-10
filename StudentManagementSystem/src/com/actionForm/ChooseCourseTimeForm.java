package com.actionForm;

public class ChooseCourseTimeForm {
	private int departmentId;
	private String beginTime;
	private String endTime;
	private boolean state;
	
	public ChooseCourseTimeForm(){
		
	}
	
	public ChooseCourseTimeForm(ChooseCourseTimeForm chooseCourseTimeForm){
		this.departmentId = chooseCourseTimeForm.getDepartmentId();
		this.beginTime = chooseCourseTimeForm.getBeginTime();
		this.endTime = chooseCourseTimeForm.getEndTime();
		this.state = chooseCourseTimeForm.getState();
	}
	
	public int getDepartmentId(){
		return this.departmentId;
	}
	public String getBeginTime(){
		return this.beginTime;
	}
	public String getEndTime(){
		return this.endTime;
	}
	public boolean getState(){
		return this.state;
	}
	
	public void setDepartmentId(int departmentId){
		this.departmentId = departmentId;
	}
	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	public void setState(boolean state){
		this.state = state;
	}
}