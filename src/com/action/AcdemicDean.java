package com.action;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.actionForm.ChooseCourseTimeForm;
import com.actionForm.CourseForm;
import com.actionForm.GetGradesForm;
import com.actionForm.NotificationForm;
import com.actionForm.ProjForm;
import com.actionForm.ShowGradesForm;
import com.actionForm.StudentCourseForm;
import com.dao.AcdemicDAO;
import com.dao.StudentDAO;

public class AcdemicDean extends HttpServlet {
	private AcdemicDAO acdemicDeanDAO = null;
	
	public AcdemicDean(){
		acdemicDeanDAO = new AcdemicDAO();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("获取的动作字符串：" + action);
		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}  
		else if ("setSectionTime".equals(action)){
			acdemicDeanUpdateSwitch(request, response);
		}
		else if ("insertCourse".equals(action)) {
			acdemicDeanInsertCourse(request, response);
		} 
		else if ("deleteCourse".equals(action)) {
			acdemicDeanDeleteCourse(request, response);
		} 
		else if (action.equals("updateproj")){
			UpdateProj(request, response);
		}
		else if (action.equals("establish")){
			EstablishProj(request, response);
		}
		else if ("getgrades".equals(action)) {
			GetGrades(request, response);
		} else if ("modify".equals(action)) {
			GetModifyGrades(request, response);
		} else if("domodify".equals(action)) {
			ModifyGrades(request, response);
		} else if("addgrades".equals(action)) {
			AddGrades(request, response);
		} else if("creditsstatistics".equals(action)) {
			StatisticCredits(request, response);
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
		else if("setEvalTime".equals(action)){
			acdemicDeanEvalCourseSwitch(request, response);
		}
	}
	
	private void UpdateProj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcdemicDAO acdemicDAO = new AcdemicDAO();
		StudentDAO stuDAO = new StudentDAO();
		Collection coll = acdemicDAO.queryProj(null);
		Collection coll2 = stuDAO.declarationInfo(null);
		Iterator it = coll.iterator();
		Iterator it2 = coll2.iterator();
		while(it.hasNext()){
			ProjForm projForm = (ProjForm)it.next();
			if (request.getParameter("delete" + projForm.getName()) != null){
				acdemicDAO.DeleteProj(projForm.getName());
				stuDAO.DeleteProj(projForm.getName());
				continue;
			}
			if (request.getParameter(projForm.getName()) != null){
				ProjForm tmp = new ProjForm();
				tmp.setName(projForm.getName());
				tmp.setTeacher(projForm.getTeacher());
				tmp.setIntro(projForm.getIntro());
				tmp.setSelected(true);
				acdemicDAO.UpdateProj(tmp);
			}
			else{
				ProjForm tmp = new ProjForm();
				tmp.setName(projForm.getName());
				tmp.setTeacher(projForm.getTeacher());
				tmp.setIntro(projForm.getIntro());
				tmp.setSelected(false);
				stuDAO.DeleteProj(projForm.getName());
				acdemicDAO.UpdateProj(tmp);
			}
		}
		response.sendRedirect("declaration.jsp");
	}
	
	private void EstablishProj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcdemicDAO acdemicDAO = new AcdemicDAO();
		ProjForm form = new ProjForm();
        form.setName(request.getParameter("proj_name"));
        form.setTeacher(request.getParameter("teacher_name"));
        form.setIntro(request.getParameter("brief_intro"));
        if (request.getParameter("selected") == null)
        	form.setSelected(false);
        else
        	form.setSelected(true);
		if (acdemicDAO.EstablishProj(form) != 0){
			//request.getRequestDispatcher("modify_ok.jsp").forward(request, response);
			response.sendRedirect("declaration.jsp");
		}
		else {
			System.out.println("保存失败");
		}
	}
	
	private void acdemicDeanUpdateSwitch(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		ChooseCourseTimeForm chooseCourseTimeForm = new ChooseCourseTimeForm();
		chooseCourseTimeForm.setDepartmentId(Integer.valueOf(request.getParameter("account")));
		chooseCourseTimeForm.setBeginTime(request.getParameter("beginTime"));
		chooseCourseTimeForm.setEndTime(request.getParameter("endTime"));
		System.out.println(request.getParameter("chooseCourseSwitch"));
		if(request.getParameter("chooseCourseSwitch").equals("1"))
			chooseCourseTimeForm.setState(true);
		else if(request.getParameter("chooseCourseSwitch").equals("2"))
			chooseCourseTimeForm.setState(false);
		int flag = acdemicDeanDAO.updateChooseCourseTime(chooseCourseTimeForm);
		if(flag == 1){
			response.sendRedirect("updateSuccess.jsp");
		}
		else
			System.out.println("update error");
	}
	
	private void acdemicDeanInsertCourse(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		CourseForm courseForm = new CourseForm();
		System.out.println(request.getParameter("courseId"));
		courseForm.setId(Integer.valueOf(request.getParameter("courseId")));
		courseForm.setNameC(request.getParameter("nameC"));
		courseForm.setNameE(request.getParameter("nameE"));
		courseForm.setCredit(Float.valueOf(request.getParameter("credit")));
		courseForm.setWeekHour(Integer.valueOf(request.getParameter("weekHour")));
		courseForm.setSemester(request.getParameter("semester"));
		courseForm.setTeacherMode(request.getParameter("teachMode"));
		courseForm.setCollegeId(Integer.valueOf(request.getParameter("account")));
		courseForm.setYear(request.getParameter("courseYear"));
		int flag = acdemicDeanDAO.insertCourse(courseForm);
		if(flag == 2){
			response.sendRedirect("idExistError.jsp");
		}
		else if(flag == 1)
			response.sendRedirect("queryCourse.jsp");
		else if(flag == 0){
			System.out.println("insert error");
		}
	}
	private void acdemicDeanDeleteCourse(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		Collection coll = acdemicDeanDAO.queryCourse(request.getParameter("account"));
		Iterator it = coll.iterator();
		int flag = 0;
		while(it.hasNext()){
			CourseForm courseForm = (CourseForm)it.next();
			System.out.println(request.getParameter(Integer.toString(courseForm.getId())));
			if(request.getParameter(Integer.toString(courseForm.getId()))!=null){
				if(request.getParameter(Integer.toString(courseForm.getId())).equals("on")){
					flag = acdemicDeanDAO.deleteCourse(courseForm);
					if(flag == 0)
						break;
				}
			}
		}
		if(flag == 0){
			System.out.println("delete error");
		}
		response.sendRedirect("queryCourse.jsp");
	}
	
	public void insertNotification(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		NotificationForm notificationForm = new NotificationForm();
		notificationForm.setAuthorId(request.getParameter("account"));
		notificationForm.setCollegeId(Integer.valueOf(request.getParameter("account")));
		notificationForm.setTitle(request.getParameter("title"));
		notificationForm.setContent(request.getParameter("content"));
		int flag = acdemicDeanDAO.insertNotification(notificationForm);
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
		Collection courInfo = acdemicDeanDAO.queryNotification(null);
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
				flag = acdemicDeanDAO.updateNotification(notificationForm2);
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
		Collection coll = acdemicDeanDAO.queryNotification(null);
		Iterator it = coll.iterator();
		int flag = 0;
		while(it.hasNext()){
			NotificationForm notificationForm1 = (NotificationForm)it.next();
			System.out.println(request.getParameter(Integer.toString(notificationForm1.getNotificationId())));
			if(request.getParameter(Integer.toString(notificationForm1.getNotificationId()))!=null){
				if(request.getParameter(Integer.toString(notificationForm1.getNotificationId())).equals("on")){
					NotificationForm notificationForm2 = new NotificationForm();
					notificationForm2.setNotificationId(notificationForm1.getNotificationId());
					flag = acdemicDeanDAO.deleteNotification(notificationForm2);
					if(flag != 1)break;
				}
			}
		}
		if(flag == 1)
			response.sendRedirect("queryDeleteNotification.jsp");
		else
			System.out.println("Delete notification error!");
	}
	
	private void acdemicDeanEvalCourseSwitch(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		ChooseCourseTimeForm chooseCourseTimeForm = new ChooseCourseTimeForm();
		chooseCourseTimeForm.setDepartmentId(Integer.valueOf(request.getParameter("account")));
		chooseCourseTimeForm.setBeginTime(request.getParameter("beginTime"));
		chooseCourseTimeForm.setEndTime(request.getParameter("endTime"));
		System.out.println(request.getParameter("chooseCourseSwitch"));
		if(request.getParameter("chooseCourseSwitch").equals("1"))
			chooseCourseTimeForm.setState(true);
		else if(request.getParameter("chooseCourseSwitch").equals("2"))
			chooseCourseTimeForm.setState(false);
		int flag = acdemicDeanDAO.updateEvalCourseTime(chooseCourseTimeForm);
		if(flag == 1){
			response.sendRedirect("updateSuccess.jsp");
		}
		else
			System.out.println("update error");
	}
	
	/**
	 * 以下为成绩查询修改部分
	 * */
	
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
		if( form.getStu_num() != "" && form.getCourse_id() != "" && form.getCollege() != "" ) {
			session.setAttribute("modifyMode", "0");
			response.sendRedirect("domodify.jsp");
		}
		else
			response.sendRedirect("modify.jsp");
	}
	public void ModifyGrades(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.acdemicDeanDAO = new AcdemicDAO();
		HttpSession session=request.getSession();
		int itemnum = Integer.parseInt((String) session.getAttribute("scoreItemNum"));
		boolean failed = false;
		String err = "";
		int errNum = 0;
		for(int i = 0; i < itemnum; i ++) {
			ShowGradesForm form = new ShowGradesForm();
			form.setStu_num((String)session.getAttribute("setgrades_stu_num"));
			form.setCourse_id((String)session.getAttribute("setgrades_course_id"+i));
			form.setScore(request.getParameter("score"+i));
			
			System.out.println("stu num:" + form.getStu_num());
			System.out.println("course id:" + form.getCourse_id());
			System.out.println("score:" + form.getScore());
			
			if(form.getScore()!="" && (form.getScore() == null || Float.parseFloat(form.getScore()) < 0 || Float.parseFloat(form.getScore()) > 100 )) {
				failed = true;
				//errNum++;
				System.out.println("第"+(i+1)+"条成绩信息错误！");
				err += "第"+(i+1)+"条成绩信息错误！\n";
				continue;
			}
			if (acdemicDeanDAO.updateScore(form) == 0){
				failed = true;
				//errNum++;
				System.out.println("第"+(i+1)+"条修改失败");
				err += "第"+(i+1)+"条修改失败！";
				continue;
			}
		}
		if(failed == false) {
			session.setAttribute("modifyMode", "1");
			session.setAttribute("modifyErr", "成绩修改成功！");
			response.sendRedirect("domodify.jsp");
		}
		else {
			session.setAttribute("modifyMode", "2");
			session.setAttribute("modifyErr", err);
			response.sendRedirect("domodify.jsp");
		}
	}
	public void AddGrades(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		this.acdemicDeanDAO = new AcdemicDAO();
		ShowGradesForm form = new ShowGradesForm();
		form.setStu_num((String)request.getParameter("stu_num"));
		form.setCourse_id((String)request.getParameter("course_id"));
		form.setScore(request.getParameter("score"));
		String college_id = (String)session.getAttribute("acd_college");
		
		System.out.println("stu num:" + form.getStu_num());
		System.out.println("course id:" + form.getCourse_id());
		System.out.println("score:" + form.getScore());
		System.out.println("college id:"+college_id);
		
		int result = acdemicDeanDAO.checkAddInfo(form.getStu_num(), form.getCourse_id(), form.getScore(), college_id);
		if(result!=0) {
			System.out.println("result:"+result);
			session.setAttribute("addmode", Integer.toString(result));
			response.sendRedirect("statistics.jsp");
		}
		else if (acdemicDeanDAO.addScore(form) != 0){
			response.sendRedirect("add_ok.jsp");
		}
		else {
			System.out.println("添加失败");
		}
	}
	public void StatisticCredits(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GetGradesForm form = new GetGradesForm();
		form.setEntr_time(request.getParameter("entr_time"));
		form.setCollege(request.getParameter("college"));
		
		HttpSession session=request.getSession();
		session.setAttribute("getcredits_entr_time", form.getEntr_time());
		session.setAttribute("getcredits_college", form.getCollege());
		
		response.sendRedirect("credits.jsp");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
