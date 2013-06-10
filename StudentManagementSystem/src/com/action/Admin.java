package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.actionForm.AcdemicDeanForm;
import com.actionForm.AdminModifyPwdForm;
import com.actionForm.CourseForm;
import com.actionForm.GetGradesForm;
import com.actionForm.NotificationForm;
import com.actionForm.ShowGradesForm;
import com.dao.AcdemicDAO;
import com.dao.AdminDAO;


public class Admin extends HttpServlet{
	private AdminDAO adminDAO = null; // 声明erDAO的对象

	public Admin() {
		this.adminDAO = new AdminDAO(); // 实例化StudentDAO类
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("获取的查询字符串：" + action);
		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}  
		else if (action.equals("modifyPwd")) {
			modifyPwd(request, response);
		}
		else if (action.equals("addAcdemicDean")) {
			try {
				addAcdemicDeanInfo(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (action.equals("deleteAcdemicDean")) {
			deleteAcdemicDeanInfo(request, response);
		}
		else if (action.equals("queryStudentInfo")) {
			queryStudentInfo(request, response);
		}
		else if("insertCourse".equals(action)) {
			adminInsertCourse(request, response);
		}
		else if("getgrades".equals(action)) {
			GetGrades(request, response);
		}
		else if("addgrades".equals(action)) {
				AddGrades(request, response);
		}
		else if ("insertNotification".equals(action)){
			insertNotification(request, response);
		}
		else if ("updateNotification".equals(action)){
			updateNotification(request, response);
		}
		else if ("deleteNotification".equals(action)){
			deleteNotification(request, response);
		}
	}

	private void modifyPwd(HttpServletRequest request, HttpServletResponse response) {
		AdminModifyPwdForm form = new AdminModifyPwdForm();
		form.setAccount(request.getParameter("account"));
		form.setOldPwd(request.getParameter("oldPwd"));
		form.setNewPwd(request.getParameter("newPwd"));
		form.setCnewPwd(request.getParameter("cnewPwd"));
		int rs = adminDAO.modifyPwd(form);
		if (rs != 0) {
			try {
				response.sendRedirect("modify_ok.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void addAcdemicDeanInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		AcdemicDeanForm form = new AcdemicDeanForm();
		form.setUser(request.getParameter("user"));
		form.setPwd(request.getParameter("pwd"));
		form.setCollegeName(request.getParameter("collegename"));
		System.out.println(form.getCollegeName());
		int rs = adminDAO.addAcdemicDean(form);
		if (rs == -1) {
			response.sendRedirect("addAcdemic_error.jsp");
		}
		else if (rs == 1) {
			response.sendRedirect("addAcdemic_ok.jsp");
		}
	}
	
	private void deleteAcdemicDeanInfo(HttpServletRequest request, HttpServletResponse response) {
		String[] result = request.getParameterValues("choose");
		for (int i = 0; i < result.length; i ++) {
			try {
				adminDAO.deleteAcdemicDean(result[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			response.sendRedirect("deleteAcdemic_ok.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void queryStudentInfo(HttpServletRequest request, HttpServletResponse response) {
		String college_name = request.getParameter("college_name");
		String entr_time = request.getParameter("entr_time");
		try {
			request.getRequestDispatcher("/Admin/StudentManagement/studentInfo.jsp").forward(request, response);
			//response.sendRedirect("studentInfo_ok.jsp?college_name=" + college_name + "entr_time=" + entr_time);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void adminInsertCourse(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		CourseForm courseForm = new CourseForm();
		System.out.println(request.getParameter("courseId"));
		courseForm.setId(Integer.valueOf(request.getParameter("courseId")));
		courseForm.setNameC(request.getParameter("nameC"));
		courseForm.setNameE(request.getParameter("nameE"));
		courseForm.setCredit(Float.valueOf(request.getParameter("credit")));
		courseForm.setWeekHour(Integer.valueOf(request.getParameter("weekHour")));
		courseForm.setSemester(request.getParameter("semester"));
		courseForm.setTeacherMode(request.getParameter("teachMode"));
		courseForm.setCollegeId(Integer.valueOf(request.getParameter("collegeId")));
		courseForm.setYear(request.getParameter("courseYear"));
		int flag = adminDAO.insertCourse(courseForm);
		if(flag == 2){
			response.sendRedirect("idExistError.jsp");
		}
		else if(flag == 1)
			response.sendRedirect("queryCourse.jsp");
		else if(flag == 0){
			System.out.println("insert error");
		}
	}
	
	public void GetGrades(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GetGradesForm form = new GetGradesForm();
		HttpSession session=request.getSession();
		String keyword = request.getParameter("keywords");
		session.setAttribute("keyword", keyword);
		System.out.println(keyword);
		
		if(keyword.equals("0")) {
			form.setStu_num(request.getParameter("content"));
			session.setAttribute("getgrade_stu_num", form.getStu_num());
			System.out.println(form.getStu_num());
		}
		else if(keyword.equals("1")){
			form.setStu_name(request.getParameter("content"));
			session.setAttribute("getgrade_stu_name", form.getStu_name());
		}
		else if(keyword.equals("2")) {
			form.setEntr_time(request.getParameter("content"));
			session.setAttribute("getgrade_entr_time", form.getEntr_time());
		}
		else if(keyword.equals("3")) {
			form.setCourse_id(request.getParameter("content"));
			session.setAttribute("getgrade_course_id", form.getCourse_id());
			System.out.println(form.getCourse_id());
		}
		form.setCollege(request.getParameter("college"));
		
		System.out.println(form.getStu_num() + " " + form.getStu_name() + " " + form.getEntr_time() + " " + form.getCollege());		
		session.setAttribute("getgrade_college", form.getCollege());
		
		response.sendRedirect("result.jsp");
	}
	public void GetModifyGrades(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GetGradesForm form = new GetGradesForm();
		form.setStu_num(request.getParameter("stu_num"));
		form.setCollege(request.getParameter("college"));
		form.setCourse_id(request.getParameter("course_id"));
		
		System.out.println(form.getStu_num() + " " + form.getStu_name() + " " + form.getEntr_time() + " " + form.getCollege());
		HttpSession session=request.getSession();
		session.setAttribute("getgrade_stu_num", form.getStu_num());
		session.setAttribute("getgrade_college", form.getCollege());
		session.setAttribute("getgrade_course_id", form.getCourse_id());
		if( form.getStu_num() != "" && form.getCourse_id() != "" && form.getCollege() != "" )
			response.sendRedirect("domodify.jsp");
		else
			response.sendRedirect("modify.jsp");
	}
	public void ModifyGrades(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.adminDAO = new AdminDAO();
		ShowGradesForm form = new ShowGradesForm();
		HttpSession session=request.getSession();
		form.setStu_num((String)session.getAttribute("setgrades_stu_num"));
		form.setCourse_id((String)session.getAttribute("setgrades_course_id"));
		form.setScore(request.getParameter("score"));
		
		System.out.println("stu num:" + form.getStu_num());
		System.out.println("course id:" + form.getCourse_id());
		System.out.println("score:" + form.getScore());
		
		if (adminDAO.updateScore(form) != 0){
			response.sendRedirect("modify_ok.jsp");
		}
		else {
			System.out.println("修改失败");
		}
	}
	
	public void insertNotification(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		NotificationForm notificationForm = new NotificationForm();
		notificationForm.setAuthorId(request.getParameter("account"));
		notificationForm.setCollegeId(0);
		notificationForm.setTitle(request.getParameter("title"));
		notificationForm.setContent(request.getParameter("content"));
		int flag = adminDAO.insertNotification(notificationForm);
		if(flag == 1){
			System.out.println("Releasing notices success!");
			response.sendRedirect("queryNotification.jsp");
		}
		else if(flag == 2)
			response.sendRedirect("titleExistError.jsp");
		else
			System.out.println("Insert notification error!");
	}
	public void updateNotification(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		NotificationForm notificationForm1 = null;
		Collection courInfo = adminDAO.queryNotification();
		int flag = 0;
		if (courInfo != null && !courInfo.isEmpty()){
			Iterator it = courInfo.iterator();
			while(it.hasNext()){
				notificationForm1 = (NotificationForm)it.next();
				NotificationForm notificationForm2 = new NotificationForm();
				notificationForm2.setNotificationId(notificationForm1.getNotificationId());
				notificationForm2.setAuthorId(notificationForm1.getAuthorId());
				notificationForm2.setCollegeId(notificationForm1.getCollegeId());
				notificationForm2.setTitle(notificationForm1.getTitle());
				notificationForm2.setContent(request.getParameter(Integer.toString(notificationForm1.getNotificationId())));
				flag = adminDAO.updateNotification(notificationForm2);
				if(flag != 1)break;
	  		}
			if(flag == 1)
				response.sendRedirect("queryUpdateNotification.jsp");
			else
				System.out.println("Update notification error!");
	  	}
		else 
		{
			System.out.println("Notification is empty!");
		}
	}
	public void deleteNotification(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		Collection coll = adminDAO.queryNotification();
		Iterator it = coll.iterator();
		int flag = 0;
		while(it.hasNext()){
			NotificationForm notificationForm1 = (NotificationForm)it.next();
			System.out.println(request.getParameter(Integer.toString(notificationForm1.getNotificationId())));
			if(request.getParameter(Integer.toString(notificationForm1.getNotificationId()))!=null){
				if(request.getParameter(Integer.toString(notificationForm1.getNotificationId())).equals("on")){
					NotificationForm notificationForm2 = new NotificationForm();
					notificationForm2.setNotificationId(notificationForm1.getNotificationId());
					flag = adminDAO.deleteNotification(notificationForm2);
					if(flag != 1)break;
				}
			}
		}
		if(flag == 1)
			response.sendRedirect("queryDeleteNotification.jsp");
		else
			System.out.println("Delete notification error!");
	}
	
	public void AddGrades(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		this.adminDAO = new AdminDAO();
		ShowGradesForm form = new ShowGradesForm();
		form.setStu_num((String)request.getParameter("stu_num"));
		form.setCourse_id((String)request.getParameter("course_id"));
		form.setScore(request.getParameter("score"));
		String college_id = (String)session.getAttribute("adm_college");
		
		if(form.getStu_num() == null || form.getStu_num() == "" 
				|| form.getCourse_id() == null || form.getCourse_id() == "" 
				|| form.getScore() == null || form.getScore() == "") {
			//response.sendRedirect("update.jsp");
		}
		else {
			System.out.println("stu num:" + form.getStu_num());
			System.out.println("course id:" + form.getCourse_id());
			System.out.println("score:" + form.getScore());
			System.out.println("college id:"+college_id);
			
			int result = adminDAO.checkAddInfo(form.getStu_num(), form.getCourse_id(), form.getScore());
			if(result!=0) {
				System.out.println("result:"+result);
				session.setAttribute("addmode", Integer.toString(result));
				response.sendRedirect("update.jsp");
			}
			else if (adminDAO.addScore(form) != 0){
				response.sendRedirect("add_ok.jsp");
			}
			else {
				System.out.println("添加失败");
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
