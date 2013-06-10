package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.actionForm.CourseForm;
import com.actionForm.EvalCourseForm;
import com.actionForm.StudentCourseForm;
import com.actionForm.StudentForm;
import com.actionForm.declarationForm;
import com.dao.StudentDAO;

public class Student extends HttpServlet {
	private StudentDAO studentDAO = null; // 声明erDAO的对象

	public Student() {
		this.studentDAO = new StudentDAO(); // 实例化StudentDAO类
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("获取的查询字符串：" + action);
		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}  
		else if (action.equals("modifyInfo")){
			studentModifyInfo(request, response);
		}
		else if ("chooseCourse".equals(action)) {
			studentChooseCourse(request, response);
		} 
		else if ("dropCourse".equals(action)) {
			studentDropCourse(request, response);
		} 
		else if ("evaluateCourse".equals(action)) {
			studentEvalCourse(request, response);
		}
		else if (action.equals("onlinedeclare")){
			studentOnlineDeclare(request, response);
		}else if ("querygrades".equals(action)) {
			getGrades(request, response);
		}
	}

	// 管理员身份验证

	private void studentModifyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection coll = studentDAO.studentInfo(request.getParameter("account"));
		Iterator it = coll.iterator();
		StudentForm form = new StudentForm();
		form.setStuNum(request.getParameter("account"));
		form.setNameEn(request.getParameter("name_en"));
		form.setGraduSch(request.getParameter("gradu_sch"));
		form.setEmail(request.getParameter("email"));
		form.setTelephone(request.getParameter("telephone"));
		form.setPosCode(request.getParameter("pos_code"));
		form.setHomeAddr(request.getParameter("home_addr"));
		System.out.println(request.getParameter("home_addr"));
		if (studentDAO.updateStudentInfo(form) != 0){
			//request.getRequestDispatcher("modify_ok.jsp").forward(request, response);
			response.sendRedirect("modify_ok.jsp");
		}
		else {
			System.out.println("保存失败");
		}
	}
	
	private void studentChooseCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection coll = studentDAO.queryCourse(null);
		Iterator it = coll.iterator();
		while(it.hasNext()){
			CourseForm courseForm = (CourseForm)it.next();
			if(request.getParameter(Integer.toString(courseForm.getId()))!=null){
				if(request.getParameter(Integer.toString(courseForm.getId())).equals("on")){
					StudentCourseForm sCF = new StudentCourseForm();
					sCF.setCourseId(courseForm.getId());
					sCF.setStudentId(Integer.valueOf(request.getParameter("account")));
					studentDAO.chooseCourse(sCF);
				}
			}
		}
		response.sendRedirect("result.jsp");
	}
	
	private void studentDropCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection coll = studentDAO.queryCourse(null);
		Iterator it = coll.iterator();
		while(it.hasNext()){
			CourseForm courseForm = (CourseForm)it.next();
			System.out.println(request.getParameter(Integer.toString(courseForm.getId())));
			if(request.getParameter(Integer.toString(courseForm.getId()))!=null){
				if(request.getParameter(Integer.toString(courseForm.getId())).equals("on")){
					StudentCourseForm sCF = new StudentCourseForm();
					sCF.setCourseId(courseForm.getId());
					sCF.setStudentId(Integer.valueOf(request.getParameter("account")));
					studentDAO.dropCourse(sCF);
				}
			}
		}
		response.sendRedirect("result.jsp");
	}
	
	private int studentEvalCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection coll = studentDAO.queryCourse(request.getParameter("account"));
		Iterator it = coll.iterator();
		while(it.hasNext()){
			CourseForm courseForm = (CourseForm)it.next();
			EvalCourseForm eCF = new EvalCourseForm();
			eCF.setNameC(courseForm.getNameC());
			eCF.setNameE(courseForm.getNameE());
			eCF.setId(Integer.valueOf(request.getParameter("account")));
			eCF.setSemester(courseForm.getSemester());
			eCF.setYear(courseForm.getYear());
			
			String strRadio = request.getParameter(Integer.toString(courseForm.getId()));
			System.out.println("单选建" + strRadio);
			if (strRadio == null || strRadio.equals("")){
				request.setAttribute("error", "请输入完整！");
				request.getRequestDispatcher("error.jsp")
						.forward(request, response);//转到错误提示页
				System.out.println("here!!!!!!!!!!");
				return -1;
			}
			if (strRadio.equals("2"))
				eCF.setAtti("基本满意");
			else if (strRadio.equals("3"))
				eCF.setAtti("不满意");
			else
				eCF.setAtti("满意");
				
			studentDAO.EvalCourse(eCF);
		}
		response.sendRedirect("finish.jsp");
		return 0;
	}
	
	private void studentOnlineDeclare(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection coll = studentDAO.declarationInfo(request.getParameter("account"));
		Iterator it = coll.iterator();
		if (it.hasNext()){
			response.sendRedirect("modify_error.jsp");
			return;
		}
		declarationForm form = new declarationForm();
		form.setprojid(Integer.parseInt(request.getParameter("account")));
        form.setprojname(request.getParameter("proj_name2"));
        form.setoutlay(request.getParameter("outlay"));
        form.setstartime(request.getParameter("starting_time"));
        form.setterminaltime(request.getParameter("terminal_time"));
        form.setstuname(1, request.getParameter("stu_name1"));
        form.setstugrade(1, request.getParameter("stu_grade1"));
        form.setstumajor(1, request.getParameter("stu_major1"));
        form.setstuid(1, request.getParameter("account"));
        form.setstuphonenum(1, request.getParameter("stu_phonenum1"));
        form.setstuemail(1, request.getParameter("stu_email1"));
        form.setstuname(2, request.getParameter("stu_name2"));
        form.setstugrade(2, request.getParameter("stu_grade2"));
        form.setstumajor(2, request.getParameter("stu_major2"));
        form.setstuid(2, request.getParameter("stu_id2"));
        form.setstuphonenum(2, request.getParameter("stu_phonenum2"));
        form.setstuemail(2, request.getParameter("stu_email2"));
        form.setstuname(3, request.getParameter("stu_name3"));
        form.setstugrade(3, request.getParameter("stu_grade3"));
        form.setstumajor(3, request.getParameter("stu_major3"));
        form.setstuid(3, request.getParameter("stu_id3"));
        form.setstuphonenum(3, request.getParameter("stu_phonenum3"));
        form.setstuemail(3, request.getParameter("stu_email3"));
        form.setstuname(4, request.getParameter("stu_name4"));
        form.setstugrade(4, request.getParameter("stu_grade4"));
        form.setstumajor(4, request.getParameter("stu_major4"));
        form.setstuid(4, request.getParameter("stu_id4"));
        form.setstuphonenum(4, request.getParameter("stu_phonenum4"));
        form.setstuemail(4, request.getParameter("stu_email4"));
        form.setteachername(request.getParameter("teacher_name"));
        form.setteacherage(request.getParameter("teacher_age"));
        form.setteacherduties(request.getParameter("teacher_duties"));
        form.setteacherphonenum(request.getParameter("teacher_phonenum"));
        form.setteacheremail(request.getParameter("teacher_email"));
        form.setresearcharea(request.getParameter("research_area"));
        form.setbriefintro(request.getParameter("brief_intro"));
        form.setprojalter(request.getParameter("proj_alternatives"));
		if (studentDAO.updatedeclarationInfo(form) != 0){
			//request.getRequestDispatcher("modify_ok.jsp").forward(request, response);
			response.sendRedirect("modify_ok.jsp");
			return;
		}
		else {
			System.out.println("保存失败");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void getGrades(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String year = (String) request.getParameter("year");
			String term = (String) request.getParameter("term");
			String seme = (term.equals("1")) ? "上学期" : "下学期";
	        HttpSession session=request.getSession();
	        session.setAttribute("achi_year", year);
	        session.setAttribute("achi_term", seme);
	        response.sendRedirect("grades.jsp");
		}
		public String getFirstYear(String account) {
			Collection coll = this.studentDAO.query_year(account);
			String f_year = null;
			String temp;
			Iterator it = coll.iterator();
			if(it.hasNext()) {
				f_year = (String)it.next();
				//System.out.println(f_year);
			}
				
			while(it.hasNext()) {
				temp = (String)it.next();
				if( temp.compareTo(f_year) < 0 )
					f_year = temp;
			}
			return f_year;
		}
		public String getCurrentYear(String account) {
			System.out.println(account);
			Collection coll = this.studentDAO.query_year(account);
			String c_year = null;
			String temp;
			Iterator it = coll.iterator();
			if(it.hasNext()) {
				c_year = (String)it.next();
				System.out.println(c_year);
			}
			while(it.hasNext()) {
				temp = (String)it.next();
				if( temp.compareTo(c_year) > 0 )
					c_year = temp;
			}
			return c_year;
		}
		public String getCurrentTerm(String account) {
			String c_year = this.getCurrentYear(account);
			Collection coll = this.studentDAO.query_term(account, c_year);
			String c_term = "上学期";
			String temp;
			Iterator it = coll.iterator();
			while(it.hasNext()) {
				temp = (String)it.next();
				if( temp.compareTo(c_term) != 0 )
					c_term = temp;
			}
			return c_term;
		}
		public String getFirstTerm(String account) {
			String f_year = this.getFirstYear(account);
			Collection coll = this.studentDAO.query_term(account, f_year);
			String f_term = "上学期";
			String temp;
			Iterator it = coll.iterator();
			while(it.hasNext()) {
				temp = (String)it.next();
				if( temp.compareTo(f_term) != 0 )
					f_term = temp;
			}
			return f_term;
		}

}
