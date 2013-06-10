package com.actionForm;

public class EvalCourseForm {
	private int id = -1;
	private String nameC = "";
	private String nameE = "";
	private String semester = "";
	private String year = "";
	private String attitude = "";
	
	
	public EvalCourseForm(){
		
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
	public void setSemester(String semester){
		this.semester = semester;
	}
	public void setYear(String year){
		this.year = year;
	}
	public void setAtti(String atti){
		this.attitude = atti;
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
	public String getSemester(){
		return this.semester;
	}
	public String getYear(){
		return this.year;
	}
	public String getAtti(){
		return this.attitude;
	}
}
