package com.actionForm;

public class GetGradesForm {

	private String stu_num = "";
	private String stu_name = "";
	private String entr_time = "";
	private String college = "";
	private String course_id = "";
	private float min_score = -1;
	private float max_score = -1;
	public static void main(String[] args) {

	}
	
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}

	public String getStu_num() {
		return stu_num;
	}

	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}

	public String getEntr_time() {
		return entr_time;
	}

	public void setEntr_time(String entr_time) {
		this.entr_time = entr_time;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public float getMin_score() {
		return min_score;
	}

	public void setMin_score(float min_score) {
		this.min_score = min_score;
	}

	public float getMax_score() {
		return max_score;
	}

	public void setMax_score(float max_score) {
		this.max_score = max_score;
	}

}
