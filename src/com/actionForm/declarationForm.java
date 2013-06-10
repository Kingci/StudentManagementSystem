package com.actionForm;

public class declarationForm {
	private int proj_id = -1;
	private String proj_name = "";
	private String outlay = "";
	private String starting_time= "";
	private String terminal_time = "";
	private String stu_name1 = "";
	private String stu_grade1 = "";
	private String stu_major1 = "";
	private String stu_id1 = "";
	private String stu_phonenum1 = "";
	private String stu_email1 = "";
	private String stu_name2 = "";
	private String stu_grade2 = "";
	private String stu_major2 = "";
	private String stu_id2 = "";
	private String stu_phonenum2 = "";
	private String stu_email2 = "";
	private String stu_name3 = "";
	private String stu_grade3 = "";
	private String stu_major3 = "";
	private String stu_id3 = "";
	private String stu_phonenum3 = "";
	private String stu_email3 = "";
	private String stu_name4 = "";
	private String stu_grade4 = "";
	private String stu_major4 = "";
	private String stu_id4 = "";
	private String stu_phonenum4 = "";
	private String stu_email4 = "";
	private String teacher_name = "";
	private String teacher_age = "";
	private String teacher_duties = "";
	private String teacher_phonenum = "";
	private String teacher_email = "";
	private String research_area = "";
	private String brief_intro = "";
	private String proj_alternatives = "";
	
	public int getprojid() {
		return proj_id;
	}
	
	public void setprojid(int proj_id) {
		this.proj_id = proj_id;
	}
	
	public String getprojname() {
		return proj_name;
	}
	
	public void setprojname(String proj_name) {
		this.proj_name = proj_name;
	}
	
	public String getoutlay() {
		return outlay;
	}
	
	public void setoutlay(String outlay) {
		this.outlay = outlay;
	}
	
	public String getstartime() {
		return starting_time;
	}
	
	public void setstartime(String starting_time) {
		this.starting_time = starting_time;
	}
	
	public String getterminaltime() {
		return terminal_time;
	}
	
	public void setterminaltime(String terminal_time) {
		this.terminal_time = terminal_time;
	}
	
	public String getstuname(int i) {
		switch (i){
		case 1:
			return stu_name1;
		case 2:
			return stu_name2;
		case 3:
			return stu_name3;
		case 4:
			return stu_name4;
		default:
			return "no such student!";
		}
	}
	
	public String setstuname(int i, String stu_name) {
		switch (i){
		case 1:
			this.stu_name1 = stu_name;
		case 2:
			this.stu_name2 = stu_name;
		case 3:
			this.stu_name3 = stu_name;
		case 4:
			this.stu_name4 = stu_name;
		default:
			return "no such student!";
		}
	}
	
	public String getstugrade(int i) {
		switch (i){
		case 1:
			return stu_grade1;
		case 2:
			return stu_grade2;
		case 3:
			return stu_grade3;
		case 4:
			return stu_grade4;
		default:
			return "no such student!";
		}
	}
	
	public String setstugrade(int i, String stu_grade) {
		switch (i){
		case 1:
			this.stu_grade1 = stu_grade;
		case 2:
			this.stu_grade2 = stu_grade;
		case 3:
			this.stu_grade3 = stu_grade;
		case 4:
			this.stu_grade4 = stu_grade;
		default:
			return "no such student!";
		}
	}
	
	public String getstumajor(int i) {
		switch (i){
		case 1:
			return stu_major1;
		case 2:
			return stu_major2;
		case 3:
			return stu_major3;
		case 4:
			return stu_major4;
		default:
			return "no such student!";
		}
	}
	
	public String setstumajor(int i, String stu_major) {
		switch (i){
		case 1:
			this.stu_major1 = stu_major;
		case 2:
			this.stu_major2 = stu_major;
		case 3:
			this.stu_major3 = stu_major;
		case 4:
			this.stu_major4 = stu_major;
		default:
			return "no such student!";
		}
	}
	
	public String getstuid(int i) {
		switch (i){
		case 1:
			return stu_id1;
		case 2:
			return stu_id2;
		case 3:
			return stu_id3;
		case 4:
			return stu_id4;
		default:
			return "no such student!";
		}
	}
	
	public String setstuid(int i, String stu_id) {
		switch (i){
		case 1:
			this.stu_id1 = stu_id;
		case 2:
			this.stu_id2 = stu_id;
		case 3:
			this.stu_id3 = stu_id;
		case 4:
			this.stu_id4 = stu_id;
		default:
			return "no such student!";
		}
	}
	
	public String getstuphonenum(int i) {
		switch (i){
		case 1:
			return stu_phonenum1;
		case 2:
			return stu_phonenum2;
		case 3:
			return stu_phonenum3;
		case 4:
			return stu_phonenum4;
		default:
			return "no such student!";
		}
	}
	
	public String setstuphonenum(int i, String stu_phonenum) {
		switch (i){
		case 1:
			this.stu_phonenum1 = stu_phonenum;
		case 2:
			this.stu_phonenum2 = stu_phonenum;
		case 3:
			this.stu_phonenum3 = stu_phonenum;
		case 4:
			this.stu_phonenum4 = stu_phonenum;
		default:
			return "no such student!";
		}
	}
	
	public String getstuemail(int i) {
		switch (i){
		case 1:
			return stu_email1;
		case 2:
			return stu_email2;
		case 3:
			return stu_email3;
		case 4:
			return stu_email4;
		default:
			return "no such student!";
		}
	}
	
	public String setstuemail(int i, String stu_email) {
		switch (i){
		case 1:
			this.stu_email1 = stu_email;
		case 2:
			this.stu_email2 = stu_email;
		case 3:
			this.stu_email3 = stu_email;
		case 4:
			this.stu_email4 = stu_email;
		default:
			return "no such student!";
		}
	}
	
	public String getteachername() {
		return teacher_name;
	}
	
	public void setteachername(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	
	public String getteacherage() {
		return teacher_age;
	}
	
	public void setteacherage(String teacher_age) {
		this.teacher_age = teacher_age;
	}
	
	public String getteacherduties() {
		return teacher_duties;
	}
	
	public void setteacherduties(String teacher_duties) {
		this.teacher_duties = teacher_duties;
	}
	
	public String getteacherphonenum() {
		return teacher_phonenum;
	}
	
	public void setteacherphonenum(String teacher_phonenum) {
		this.teacher_phonenum = teacher_phonenum;
	}
	
	public String getteacheremail() {
		return teacher_email;
	}
	
	public void setteacheremail(String teacher_email) {
		this.teacher_email = teacher_email;
	}
	
	public String getresearcharea() {
		return research_area;
	}
	
	public void setresearcharea(String research_area) {
		this.research_area = research_area;
	}
	
	public String getbriefintro() {
		return brief_intro;
	}
	
	public void setbriefintro(String brief_intro) {
		this.brief_intro = brief_intro;
	}

	public String getprojalter() {
		return proj_alternatives;
	}
	
	public void setprojalter(String proj_alternatives) {
		this.proj_alternatives = proj_alternatives;
	}
}

