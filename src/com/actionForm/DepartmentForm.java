package com.actionForm;

public class DepartmentForm {
	private int collegeId;
	private String collegeName;
	private String sciArts;
	private String collegeEn;
	
	public DepartmentForm(){
		
	}
	
	public int getCollegeId(){
		return collegeId;
	}
	public String getCollegeName(){
		return collegeName;
	}
	public String getSciArts(){
		return sciArts;
	}
	public String getCollegeEn(){
		return collegeEn;
	}
	
	public void setCollegeId(int collegeId){
		this.collegeId = collegeId;
	}
	public void setCollegeName(String collegeName){
		this.collegeName = collegeName;
	}
	public void setSciArts(String sciArts){
		this.sciArts = sciArts;
	}
	public void setCollegeEn(String collegeEn){
		this.collegeEn = collegeEn;
	}
}
