package com.actionForm;

public class ChooseCourseTimeForm {
	private int departmentId;
	private String beginTime;
	private String endTime;
	
	public ChooseCourseTimeForm(){
		
	}
	
	public ChooseCourseTimeForm(ChooseCourseTimeForm chooseCourseTimeForm){
		this.departmentId = chooseCourseTimeForm.getDepartmentId();
		this.beginTime = chooseCourseTimeForm.getBeginTime();
		this.endTime = chooseCourseTimeForm.getEndTime();
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
	
	public void setDepartmentId(int departmentId){
		this.departmentId = departmentId;
	}
	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
}