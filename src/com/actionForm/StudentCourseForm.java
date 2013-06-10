package com.actionForm;

public class StudentCourseForm {
	private int studentCourseId;
	private int studentId;
	private int courseId;
	private float score;
	
	public int getStudentCourseId(){
		return studentCourseId;
	}
	public int getStudentId(){
		return studentId;
	}
	public int getCourseId(){
		return courseId;
	}
	public float getScore(){
		return score;
	}
	
	public void setStudentCourseId(int studentCourseId){
		this.studentCourseId = studentCourseId;
	}
	public void setStudentId(int studentId){
		this.studentId = studentId;
	}
	public void setCourseId(int courseId){
		this.courseId = courseId;
	}
	public void setScore(float score){
		this.score = score;
	}
}
