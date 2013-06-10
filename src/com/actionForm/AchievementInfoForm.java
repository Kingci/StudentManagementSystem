package com.actionForm;

public class AchievementInfoForm {
	private String stu_id;
	private String course_id;
	private String course_year;
	private String course_term;
	private float course_credit;
	private String course_type;
	
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getCourse_year() {
		return course_year;
	}
	public void setCourse_year(String course_year) {
		this.course_year = course_year;
	}
	public String getCourse_term() {
		return course_term;
	}
	public void setCourse_term(String course_term) {
		this.course_term = course_term;
	}
	public float getCourse_credit() {
		return course_credit;
	}
	public void setCourse_credit(float course_credit) {
		this.course_credit = course_credit;
	}
	public String getCourse_type() {
		return course_type;
	}
	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}
}
