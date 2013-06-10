package com.actionForm;

public class CourseForm {
	private int id = -1;
	private String nameC = "";
	private String nameE = "";
	private float credit = -1;
	private int weekHour = -1;
	private String semester = "";
	private String teacherMode = "";
	private int collegeId = -1;
	private String collegeName = "";
	private String year = "";
	
	public CourseForm(){
		
	}
	
	public void setId(int id){
		this.id = id;
	}
	public void setNameC(String nameC){
		this.nameC = nameC;
	}
	public void setNameE(String nameE){
		this.nameE = nameE;
	}
	public void setCredit(float credit){
		this.credit = credit;
	}
	public void setWeekHour(int weekHour){
		this.weekHour = weekHour;
	}
	public void setSemester(String semester){
		this.semester = semester;
	}
	public void setTeacherMode(String teacherMode){
		this.teacherMode = teacherMode;
	}
	public void setCollegeId(int collegeId){
		this.collegeId = collegeId;
	}
	public void setCollegeName(String collegeName){
		this.collegeName = collegeName;
	}
	public void setYear(String year){
		this.year = year;
	}
	public int getId(){
		return this.id;
	}
	public String getNameC(){
		return this.nameC;
	}
	public String getNameE(){
		return this.nameE;
	}
	public float getCredit(){
		return this.credit;
	}
	public int getWeekHour(){
		return this.weekHour;
	}
	public String getSemester(){
		return this.semester;
	}
	public String getTeacherMode(){
		return this.teacherMode;
	}
	public int getCollegeId(){
		return this.collegeId;
	}
	public String getCollegeName(){
		return this.collegeName;
	}
	public String getYear(){
		return this.year;
	}
}
