package com.actionForm;

public class DepartmentStudentCourseForm {
	private int studentId;
	private String studentName;
	private int courseId;
	private String courseName;
	private String courseCollegeName;
	
	public int getStudentId(){
		return this.studentId;
	}
	public String getStudentName(){
		return this.studentName;
	}
	public int getCourseId(){
		return this.courseId;
	}
	public String getCourseName(){
		return this.courseName;
	}
	public String getCourseCollegeName(){
		return this.courseCollegeName;
	}
	
	public void setStudentId(int studentId){
		this.studentId = studentId;
	}
	public void setStudentName(String studentName){
		this.studentName = studentName;
	}
	public void setCourseId(int courseId){
		this.courseId = courseId;
	}
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	public void setCourseCollegeName(String courseCollegeName){
		this.courseCollegeName = courseCollegeName;
	}
}
