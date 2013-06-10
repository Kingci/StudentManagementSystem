package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import com.actionForm.AchievementInfoForm;
import com.actionForm.ChooseCourseTimeForm;
import com.actionForm.CourseForm;
import com.actionForm.DepartmentForm;
import com.actionForm.EvalCourseForm;
import com.actionForm.NotificationForm;
import com.actionForm.StudentCourseForm;
import com.actionForm.StudentForm;
import com.actionForm.declarationForm;
import com.core.ConnDB;

public class StudentDAO {
	ConnDB conn = new ConnDB();
	
	/*   查询当前学生信息     */
	 public Collection studentInfo(String str){
	      String sql="select * from tb_student where stu_num = " + str;
	      ResultSet rs=conn.executeQuery(sql);
	      Collection coll=new ArrayList();
	      StudentForm form=null;
	      try {
	          while (rs.next()) {
	              form = new StudentForm();
	              form.setStuId(rs.getInt(1));
	              form.setStuNum(rs.getString(2));
	              form.setNameCh(rs.getString(3));
	              form.setNameEn(rs.getString(4));
	              form.setBirthTime(rs.getString(5));
	              form.setGender(rs.getString(6));
	              form.setCollegeNum(rs.getString(7));
	              form.setMajorNum(rs.getString(8));
	              form.setSchLength(rs.getString(9));
	              form.setIdNum(rs.getString(10));
	              form.setEntrTime(rs.getString(11));
	              form.setStuStatus(rs.getString(12));
	              form.setGraduSch(rs.getString(13));
	              form.setEmail(rs.getString(14));
	              form.setTelephone(rs.getString(15));
	              form.setHomeAddr(rs.getString(16));
	              form.setPosCode(rs.getString(17));
	              form.setCitizenship(rs.getString(18));
	              form.setNation(rs.getString(19));
	              coll.add(form);
	          }
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	      conn.close();
	      return coll;
	      }
	 
	 public int updateStudentInfo(StudentForm studentForm) {
		 String sql = "update tb_student set name_en='" + studentForm.getNameEn()
		 +"', gradu_sch='" + studentForm.getGraduSch() + "', email='" + studentForm.getEmail()
		 +"', telephone='" + studentForm.getTelephone() + "', pos_code='" + studentForm.getPosCode()
		  + "', home_addr='" + studentForm.getHomeAddr() + "' where stu_num = '" + studentForm.getStuNum() + "'";
		 System.out.println(sql);
		 int rt = conn.executeUpdate(sql);
		 return rt;
	 }
	 
	 public Collection queryCourse(String strif){//search course info
		CourseForm courseForm=null;
		Collection courseColl=new ArrayList();
		String sql="";
		if(strif!="all" && strif!=null && strif!=""){
		    sql="select * from tb_course_info left join tb_student_course_info on tb_course_info.course_id = tb_student_course_info.course_id where tb_student_course_info.student_id = " + strif +" order by tb_course_info.course_id";
		}else{
		    sql="select * from tb_course_info order by course_id";
		}
		System.out.println("课程查询时的SQL："+sql);
		ResultSet rs=conn.executeQuery(sql);
		try {
		    while (rs.next()) {
		        courseForm=new CourseForm();
		        courseForm.setId(rs.getInt(1));       
		        courseForm.setNameC(rs.getString(2));
		        courseForm.setNameE(rs.getString(3));
		        courseForm.setCredit(rs.getFloat(4));
		        courseForm.setWeekHour(rs.getInt(5));
		        courseForm.setSemester(rs.getString(6));
		        courseForm.setTeacherMode(rs.getString(7));
		        courseForm.setCollegeId(rs.getInt(8));
		    	courseForm.setYear(rs.getString(9));
		       
		        courseColl.add(courseForm);
		    }
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return courseColl;
	}
	 
	 public int EvalCourse(EvalCourseForm eCF){//search course info
		String sql = "";
		int flag = 0;
		if(eCF.getNameE().equals(""))
	    sql ="insert into tb_course_eval_info (student_id,course_name_chs,course_name_egh,semester,course_year,attitude) values(" +
	    	eCF.getId()+",'"+eCF.getNameC()+"','" + eCF.getNameE() + "','" + eCF.getSemester() + "','" + eCF.getYear() + "','" + eCF.getAtti() + "')";
	    flag = conn.executeUpdate(sql);
	    System.out.println("评价课程的SQL：" + sql);
	    conn.close();
	    System.out.println("flag:"+flag);
		
		return flag;
	 }
	 
	 public Collection queryStudentCourse(String strif){//search student course info
		StudentCourseForm studentCourseForm=null;
		Collection courseColl=new ArrayList();
		String sql="";
		if(strif!="all" && strif!=null && strif!=""){
		    sql="select * from tb_student_course_info left join tb_course_info on tb_course_info.course_id = tb_student_course_info.course_id order by tb_course_info.course_id where tb_student_course_info.student_id = " + strif;
		}else{
		    sql="select * from tb_course_info order by course_id";
		}
		System.out.println("学生选课信息查询时的SQL："+sql);
		ResultSet rs=conn.executeQuery(sql);
		try {
		    while (rs.next()) {
		    	studentCourseForm = new StudentCourseForm();
		    	studentCourseForm.setStudentCourseId(rs.getInt(1));
		    	studentCourseForm.setStudentId(rs.getInt(2));
		    	studentCourseForm.setCourseId(rs.getInt(3));
		    	studentCourseForm.setScore(rs.getFloat(4));
		       
		        courseColl.add(studentCourseForm);
		    }
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return courseColl;
	}
	
	public int chooseCourse(StudentCourseForm studentCourseForm){
		String sql1="SELECT * FROM tb_student_course_info WHERE student_id="+studentCourseForm.getStudentId()+" AND course_id=" + studentCourseForm.getCourseId();
		ResultSet rs = conn.executeQuery(sql1);
		String sql = "";
		int falg = 0;
		try {
		    if (rs.next()) {
		        falg = 2;
		    } else {
		        sql ="Insert into tb_student_course_info (student_id,course_id) values("+studentCourseForm.getStudentId()+","+studentCourseForm.getCourseId()+")";
		        falg = conn.executeUpdate(sql);
		        System.out.println("添加课程的SQL：" + sql);
		        conn.close();
		    }
		} catch (SQLException ex) {
		    falg = 0;
		}
		System.out.println("falg:"+falg);
		return falg;
	}
	
	public int dropCourse(StudentCourseForm studentCourseForm){
		String sql = "";
		int flag = 0;
	    sql ="delete from tb_student_course_info where student_id="+studentCourseForm.getStudentId()+" AND course_id="+studentCourseForm.getCourseId();
	    flag = conn.executeUpdate(sql);
	    System.out.println("退选课程的SQL：" + sql);
	    conn.close();
	    System.out.println("flag:"+flag);
		
		return flag;
	}
	
	public Collection queryDepartment(String strif){//search course info
		DepartmentForm departmentForm=null;
		Collection courseColl=new ArrayList();
		String sql="";
		if(strif!="all" && strif!=null && strif!=""){
		    sql="select * from tb_college_info where college_id = " + strif +" order by college_id";
		}else{
		    sql="select * from tb_college_info order by college_id";
		}
		System.out.println("院系查询时的SQL："+sql);
		ResultSet rs=conn.executeQuery(sql);
		try {
		    while (rs.next()) {
		    	departmentForm = new DepartmentForm();
		    	departmentForm.setCollegeId(rs.getInt(1));
		    	departmentForm.setCollegeName(rs.getString(2));
		    	departmentForm.setSciArts(rs.getString(3));
		    	departmentForm.setCollegeEn(rs.getString(4));
		    	
		        courseColl.add(departmentForm);
		    }
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		conn.close();
		return courseColl;
	}
	
	public int DeleteProj(String proj_name){
		String sql = "DELETE FROM decForm WHERE proj_name = '" + proj_name + "'";
		int falg;
		falg = conn.executeUpdate(sql);
		System.out.println("SQL ：" + sql);
		conn.close();
		System.out.println("flag : " + falg);
		return falg;
	}
	
	 public Collection declarationInfo(String str){
	      String sql="select * from decform where proj_id = " + str;
	      ResultSet rs=conn.executeQuery(sql);
	      Collection coll=new ArrayList();
	      declarationForm form=null;
	      try {
	          while (rs.next()) {
	              form = new declarationForm();
	              form.setprojid(rs.getInt(1));
	              form.setprojname(rs.getString(2));
	              form.setoutlay(rs.getString(3));
	              form.setstartime(rs.getString(4));
	              form.setterminaltime(rs.getString(5));
	              form.setstuname(1, rs.getString(6));
	              form.setstugrade(1, rs.getString(7));
	              form.setstumajor(1, rs.getString(8));
	              form.setstuid(1, rs.getString(9));
	              form.setstuphonenum(1, rs.getString(10));
	              form.setstuemail(1, rs.getString(11));
	              form.setstuname(2, rs.getString(12));
	              form.setstugrade(2, rs.getString(13));
	              form.setstumajor(2, rs.getString(14));
	              form.setstuid(2, rs.getString(15));
	              form.setstuphonenum(2, rs.getString(16));
	              form.setstuemail(2, rs.getString(17));
	              form.setstuname(3, rs.getString(18));
	              form.setstugrade(3, rs.getString(19));
	              form.setstumajor(3, rs.getString(20));
	              form.setstuid(3, rs.getString(21));
	              form.setstuphonenum(3, rs.getString(22));
	              form.setstuemail(3, rs.getString(23));
	              form.setstuname(4, rs.getString(24));
	              form.setstugrade(4, rs.getString(25));
	              form.setstumajor(4, rs.getString(26));
	              form.setstuid(4, rs.getString(27));
	              form.setstuphonenum(4, rs.getString(28));
	              form.setstuemail(4, rs.getString(29));
	              form.setteachername(rs.getString(30));
	              form.setteacherage(rs.getString(31));
	              form.setteacherduties(rs.getString(32));
	              form.setteacherphonenum(rs.getString(33));
	              form.setteacheremail(rs.getString(34));
	              form.setresearcharea(rs.getString(35));
	              form.setbriefintro(rs.getString(36));
	              form.setprojalter(rs.getString(37));
	              coll.add(form);
	          }
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	      conn.close();
	      return coll;
	 }
	 
	 public ChooseCourseTimeForm queryChooseCourseTime(String strif){
		 ChooseCourseTimeForm chooseCourseTimeForm = null;
		 String sql="select * from tb_choose_course_time where college_id ="+strif;
		 System.out.println(sql);
		 ResultSet rs = conn.executeQuery(sql);
		 try{
			 if(rs.next()){
				 chooseCourseTimeForm = new ChooseCourseTimeForm();
				 chooseCourseTimeForm.setDepartmentId(rs.getInt(1));
				 chooseCourseTimeForm.setBeginTime(rs.getString(2));
				 chooseCourseTimeForm.setEndTime(rs.getString(3));
				 chooseCourseTimeForm.setState(rs.getBoolean(4));
			 }
			 else{
				 chooseCourseTimeForm = null;
				 System.out.println("选课还未开始！");
			 }
		 }
		 catch(SQLException e){
			 chooseCourseTimeForm = null;
		 }
		 conn.close();
		 return chooseCourseTimeForm;
	 }
	 
	 public ChooseCourseTimeForm queryEvalCourseTime(String account){
		 ChooseCourseTimeForm chooseCourseTimeForm = null;
		 String sql="select * from tb_eval_course_time where college_id in (select college_id from tb_college_info where" +
		 		" college_name in (select college_num from tb_student where stu_num='" + account + "'))";
		 //System.out.println(sql);
		 ResultSet rs = conn.executeQuery(sql);
		 try{
			 if(rs.next()){
				 chooseCourseTimeForm = new ChooseCourseTimeForm();
				 chooseCourseTimeForm.setDepartmentId(rs.getInt(1));
				 chooseCourseTimeForm.setBeginTime(rs.getString(2));
				 chooseCourseTimeForm.setEndTime(rs.getString(3));
				 chooseCourseTimeForm.setState(rs.getBoolean(4));
			 }
			 else{
				 chooseCourseTimeForm = null;
				 System.out.println("选课还未开始！");
			 }
		 }
		 catch(SQLException e){
			 chooseCourseTimeForm = null;
		 }
		 conn.close();
		 return chooseCourseTimeForm;
	 }
	 
	 public int updatedeclarationInfo(declarationForm decForm) {
		 String sql = "insert into decform(proj_id, proj_name, outlay, starting_time, terminal_time, " +
		 		"stu_name1, stu_grade1, stu_major1, stu_id1, stu_phonenum1, stu_email1, " +
		 		"stu_name2, stu_grade2, stu_major2, stu_id2, stu_phonenum2, stu_email2, " +
		 		"stu_name3, stu_grade3, stu_major3, stu_id3, stu_phonenum3, stu_email3, " +
		 		"stu_name4, stu_grade4, stu_major4, stu_id4, stu_phonenum4, stu_email4, " +
		 		"teacher_name, teacher_age, teacher_duties, teacher_phonenum, teacher_email, research_area, " +
		 		"brief_intro, proj_alternatives)" +
		 		" values('" + decForm.getprojid() + "','" + decForm.getprojname() + "','" + decForm.getoutlay() + "','" + decForm.getstartime()
		 + "','" + decForm.getterminaltime() + "','" + decForm.getstuname(1) + "','" + decForm.getstugrade(1) 
		 + "','" + decForm.getstumajor(1)
		  + "','" + decForm.getstuid(1) +  "','" + decForm.getstuphonenum(1)
		  + "','" + decForm.getstuemail(1)
		  + "','" + decForm.getstuname(2)
		  + "','" + decForm.getstugrade(2) + "','" + decForm.getstumajor(2)
		  + "','" + decForm.getstuid(2) +  "','" + decForm.getstuphonenum(2)
		  + "','" + decForm.getstuemail(2)
		  + "','" + decForm.getstuname(3)
		  + "','" + decForm.getstugrade(3) +  "','" + decForm.getstumajor(3)
		  + "','" + decForm.getstuid(3) +  "','" + decForm.getstuphonenum(3)
		  + "','" + decForm.getstuemail(3)
		  + "','" + decForm.getstuname(4)
		  + "','" + decForm.getstugrade(4) +  "','" + decForm.getstumajor(4)
		  + "','" + decForm.getstuid(4) +  "','" + decForm.getstuphonenum(4)
		  + "','" + decForm.getstuemail(4)
		  + "','" + decForm.getteachername()
		  + "','" + decForm.getteacherage() +  "','" + decForm.getteacherduties()
		  + "','" + decForm.getteacherphonenum() +  "','" + decForm.getteacheremail()
		  + "','" + decForm.getresearcharea()
		  + "','" + decForm.getbriefintro() +  "','" + decForm.getprojalter() + "')";
		 System.out.println(sql);
		 int rt = conn.executeUpdate(sql);
		 return rt;
	 }
	 
	 public Collection queryNotification(String strif){
			NotificationForm notificationForm = null;
			Collection notificationColl=new ArrayList();
			String sql="";
			if(strif!="all" && strif!=null && strif!=""){
			    sql="select * from tb_notification where college_id =" + strif + " or college_id = 0";
			}else{
				sql="select * from tb_notification";
			}
			System.out.println("通知查询时的SQL："+sql);
			ResultSet rs=conn.executeQuery(sql);
			try {
			    while (rs.next()) {
			    	notificationForm = new NotificationForm();
			    	notificationForm.setNotificationId(rs.getInt(1));
			    	notificationForm.setAuthorId(rs.getString(2));
			    	notificationForm.setCollegeId(rs.getInt(3));
			    	notificationForm.setTitle(rs.getString(4));
			    	notificationForm.setContent(rs.getString(5));
			       
			    	notificationColl.add(notificationForm);
			    }
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			conn.close();
			return notificationColl;
		}
	 
	 /* * * * * * * * * * * * * * * * * * * * * * * *
	  *                 以下为成绩查询部分                                                          *
	  *  * * * * *  * * * * *  * * * * *  * * * * * */
	 /* 查询学号为stu_id的同学的所有成绩  */
	 public Collection query(String stu_id) {
	  		AchievementInfoForm info_form = null;
	  		Collection<AchievementInfoForm> coll = new ArrayList<AchievementInfoForm>();
	  		String sql = "";
	  		sql="select tb_course_info.course_id, tb_course_info.course_year, tb_course_info.semester " +
	  				"from tb_student_course_info left join tb_course_info " +
	  				"on tb_student_course_info.course_id = tb_course_info.course_id " +
	  				"where tb_student_course_info.student_id = '" + stu_id + "';";
	  		System.out.println("student -- \n"+sql);
	  		ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				info_form = new AchievementInfoForm();
	  				info_form.setCourse_id(rs.getString(1));
	  				info_form.setCourse_year(rs.getString(2));
	  				info_form.setCourse_term(rs.getString(3));
	  				info_form.setStu_id(stu_id);
	  				
	                coll.add(info_form);
	            }
	        } catch (SQLException e) {}
	        return coll;
	    }
	 	/* 辅助函数，查询所有学年和学期 */
		public Collection query_y_s(String stu_id) {
	  		AchievementInfoForm info_form = null;
	  		Collection<AchievementInfoForm> coll = new ArrayList<AchievementInfoForm>();
	  		String sql = "";
	  		/*
	  		 * select distinct tb_course_info.course_year, tb_course_info.semester 
				from tb_student_course_info left join tb_course_info 
				on tb_student_course_info.course_id = tb_course_info.course_id 
				where tb_student_course_info.student_id = '101220023' 
				order by tb_course_info.course_year desc, tb_course_info.semester desc;
	  		 * */
	  		sql="select distinct tb_course_info.course_year, tb_course_info.semester " +
	  				"from tb_student_course_info left join tb_course_info " +
	  				"on tb_student_course_info.course_id = tb_course_info.course_id " +
	  				"where tb_student_course_info.student_id = '" + stu_id + "'" +
	  				" order by tb_course_info.course_year desc, tb_course_info.semester desc;";
	  		//System.out.println(sql);
	  		ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				info_form = new AchievementInfoForm();
	  				info_form.setCourse_id("0");
	  				info_form.setCourse_year(rs.getString(1));
	  				info_form.setCourse_term(rs.getString(2));
	  				info_form.setStu_id(stu_id);
	  				
	                coll.add(info_form);
	            }
	        } catch (SQLException e) {}
	        return coll;
	    }
		/* 查询某年某学期的课程 */
		public Collection querySpecificTerm(String stu_id, String year, String term) {
	  		AchievementInfoForm info_form = null;
	  		Collection<AchievementInfoForm> coll = new ArrayList<AchievementInfoForm>();
	  		String course_id = "";
	  		String sql = "";
	  		//sql="select * from tb_student_course_info  where student_id = " + stu_id;
	  		sql="select tb_course_info.course_id " +
	  				"from tb_student_course_info left join tb_course_info " +
	  				"on tb_student_course_info.course_id = tb_course_info.course_id " +
	  				"where tb_student_course_info.student_id = '" + stu_id +
	  				"' and tb_course_info.course_year = '" + year +
	  				"' and tb_course_info.semester = '" + term + "';";
	  		System.out.println(sql + "!!!!!!!");
	  		ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				//System.out.println(rs.getString(1));
	  				info_form = new AchievementInfoForm();
	  	  			info_form.setCourse_id(rs.getString(1));
	  	  			info_form.setCourse_year(year);
	  	  			info_form.setCourse_term(term);
	  	  			info_form.setStu_id(stu_id);
	  	            coll.add(info_form);
	            }
	        } catch (SQLException e) {}
	        return coll;
	    }
		/* 查询某同学有选课记录的所有年份 */
		public Collection query_year(String stu_id) {
	  		String year = null;
	  		Collection<String> coll = new ArrayList<String>();
	  		String sql = "";
	  		sql="select distinct tb_course_info.course_year from tb_student_course_info left join tb_course_info on tb_student_course_info.course_id = tb_course_info.course_id where student_id = " + stu_id;
	  		ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				year = new String();
	  				year = rs.getString(1);
	  				//System.out.println("year:" + year);
	                coll.add(year);
	            }
	        } catch (SQLException e) {}
	        return coll;
	    }
		/* 查询某年参与选课的学期 */
		public Collection query_term(String stu_id, String year) {
			String c_id = null;
	  		String term = null;
	  		Collection<String> coll = new ArrayList<String>();
	  		String sql = "";
	  		sql="select distinct course_id from tb_student_course_info where student_id = " + stu_id;
	  		ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				c_id = new String();
	  				c_id = rs.getString(1);
	  				sql = "select semester from tb_course_info where course_id = " + c_id + " and course_year = " + year;
	  				ResultSet rs_y = conn.executeQuery(sql);
	  				term = new String();
	  				term = rs_y.getString(1);	
	                coll.add(term);
	            }
	        } catch (SQLException e) {}
	        return coll;
	    }
		/* 查询课程的总学分数 */
		public float query_academic_credit(String stu_id) {
			float total = 0;
			String course_credit;
			Collection coll = query(stu_id);
			Iterator it = coll.iterator();
			while(it.hasNext()) {
				AchievementInfoForm obj = (AchievementInfoForm)it.next();
				course_credit = query_credit(obj.getCourse_id());
				total += Float.parseFloat(course_credit);
			}
			return total;
		}
		/* 按照课程类型查询学分 */
		public Collection query_credit_by_type(String stu_id) {
			String sql = "";
			sql = "select tb_course_info.teach_mode, sum(tb_course_info.credit) " +
	  				" from tb_student_course_info left join tb_course_info " +
	  				" on tb_student_course_info.course_id = tb_course_info.course_id " +
	  				" where tb_student_course_info.student_id = '" + stu_id + "' " +
	  				" and tb_student_course_info.score >= 60.0" +
	  				" group by tb_course_info.teach_mode";
			//System.out.println(sql);
			ResultSet rs = conn.executeQuery(sql);
			AchievementInfoForm info_form = null;
	  		Collection<AchievementInfoForm> coll = new ArrayList<AchievementInfoForm>();
			try {
	  			while (rs.next()) {
	  				info_form = new AchievementInfoForm();
	  	  			info_form.setCourse_type(rs.getString(1));
	  	  			info_form.setCourse_credit(rs.getFloat(2));
	  	            coll.add(info_form);
	            }
	        } catch (SQLException e) {}
	        return coll;
			
		}
		/* 查询课程名称 */
		public String query_cname(String course_id) {
			String cname = "";
			String sql = "";
	  		sql="select distinct course_name_chs from tb_course_info where course_id = " + course_id + ";";
	  		//System.out.println(sql);
	  		ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				cname = new String();
	  				cname = rs.getString(1);
	  				break;
	            }
	        } catch (SQLException e) {}
	        return cname;
		}
		/* 查询课程类型 */
		public String query_ctype(String course_id) {
			String ctype = "";
			String sql =  "";
			sql = "select distinct teach_mode from tb_course_info where course_id = " + course_id + ";";
			ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				ctype = new String();
	  				ctype = rs.getString(1);
	  				break;
	            }
	        } catch (SQLException e) {}
	        return ctype;
		}
		/* 查询课程的学分数 */
		public String query_credit(String course_id) {
			String credit = "";
			String sql =  "";
			sql = "select distinct credit from tb_course_info where course_id = " + course_id + ";";
			ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				credit = new String();
	  				credit = rs.getString(1);
	  				break;
	            }
	        } catch (SQLException e) {}
	        return credit;
		}
		/* 查询课程分数 */
		public float query_course_score(String account, String course_id) {
			float score = 0;
			String sql =  "";
			sql = "select score from tb_student_course_info where student_id = " + account + " and course_id = " + course_id + ";";
			ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				score = rs.getFloat(1);
	  				break;
	            }
	        } catch (SQLException e) {}
	        return score;
		}
		/* 根据学号查询student_id */
		public String query_stu_id(String account) {
			String stu_id = "";
			String sql =  "";
			sql = "select stu_id from tb_student where stu_num = " + account + ";";
			ResultSet rs = conn.executeQuery(sql);
	  		try {
	  			while (rs.next()) {
	  				stu_id = Integer.toString(rs.getInt(1));
	  				break;
	            }
	        } catch (SQLException e) {}
	        return stu_id;
		}
}
