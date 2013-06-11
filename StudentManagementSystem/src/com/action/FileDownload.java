package com.action;

import java.io.*;

import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;


import com.actionForm.CourseForm;
import com.actionForm.StudentForm;
import com.dao.AcdemicDAO;


public class FileDownload extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String account = request.getParameter("account");
		System.out.println("account: " +account);
		String path = request.getSession().getServletContext().getRealPath("/download");	
		System.out.println("dpath: " + path);
		if (action.equals("exportStudentInfo")) {	// 导出学生表信息
			try {
				// 添加第一行
				String filename = "exportStudent.xls";
				OutputStream outStream = new FileOutputStream(new File(path + "/" + filename));
				WritableWorkbook wwb = Workbook.createWorkbook(outStream);
				WritableSheet ws = wwb.createSheet("Sheet 1", 0);
				Label label0 = new Label(0, 0, "学生学号");
				Label label1 = new Label(1, 0, "中文姓名");
				Label label2 = new Label(2, 0, "出生日期");
				Label label3 = new Label(3, 0, "性别");
				Label label4 = new Label(4, 0, "院系名称");
				Label label5 = new Label(5, 0, "专业名称");
				Label label6 = new Label(6, 0, "学制长度");
				Label label7 = new Label(7, 0, "身份证号");
				Label label8 = new Label(8, 0, "入学时间");
				Label label9 = new Label(9, 0, "学籍状态");
				Label label10 = new Label(10, 0, "毕业学校");
				Label label11 = new Label(11, 0, "邮箱");
				Label label12 = new Label(12, 0, "电话");
				Label label13 = new Label(13, 0, "家庭住址");
				Label label14= new Label(14, 0, "邮政编码");
				Label label15= new Label(15, 0, "国籍信息");
				Label label16= new Label(16, 0, "民族信息");
				ws.addCell(label0);
				ws.addCell(label1);
				ws.addCell(label2);
				ws.addCell(label3);
				ws.addCell(label4);
				ws.addCell(label5);
				ws.addCell(label6);
				ws.addCell(label7);
				ws.addCell(label8);
				ws.addCell(label9);
				ws.addCell(label10);
				ws.addCell(label11);
				ws.addCell(label12);
				ws.addCell(label13);
				ws.addCell(label14);
				ws.addCell(label15);
				ws.addCell(label16);
				
				// 学生信息
				String sql = "select * from tb_student where college_num in (select college_name from" +
						" tb_college_info where college_id = " + account + ")";
				System.out.println(sql);
				AcdemicDAO dao = new AcdemicDAO();
				ArrayList<StudentForm> al = dao.queryStudentInfo(sql);
				for (int i = 0; i < al.size(); i ++) {
					Label[] label = new Label[17];
					label[0] = new Label(0, i + 1, al.get(i).getStuNum());
					label[1] = new Label(1, i + 1, al.get(i).getNameCh());
					label[2] = new Label(2, i + 1, al.get(i).getBirthTime());
					label[3] = new Label(3, i + 1, al.get(i).getGender());
					label[4] = new Label(4, i + 1, al.get(i).getCollegeNum());
					label[5] = new Label(5, i + 1, al.get(i).getMajorNum());
					label[6] = new Label(6, i + 1, al.get(i).getSchLength());
					label[7] = new Label(7, i + 1, al.get(i).getIdNum());
					label[8] = new Label(8, i + 1, al.get(i).getEntrTime());
					label[9] = new Label(9, i + 1, al.get(i).getStuStatus());
					label[10] = new Label(10, i + 1, al.get(i).getGraduSch());
					label[11] = new Label(11, i + 1, al.get(i).getEmail());
					label[12] = new Label(12, i + 1, al.get(i).getTelephone());
					label[13] = new Label(13, i + 1, al.get(i).getHomeAddr());
					label[14] = new Label(14, i + 1, al.get(i).getPosCode());
					label[15] = new Label(15, i + 1, al.get(i).getCitizenShip());
					label[16] = new Label(16, i + 1, al.get(i).getNation());
					for (int j = 0; j < 17; j ++) ws.addCell(label[j]);
				}
				wwb.write();
				wwb.close();
				outStream.close();
				request.getRequestDispatcher("result.jsp?para=3").forward(request, response);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if (action.equals("exportCourseInfo")) {	// 导出课程表信息
			try {
				// 添加第一行
				String filename = "exportCourse.xls";
				OutputStream outStream = new FileOutputStream(new File(path + "/" + filename));
				WritableWorkbook wwb = Workbook.createWorkbook(outStream);
				WritableSheet ws = wwb.createSheet("Sheet 1", 0);
				Label label0 = new Label(0, 0, "课程编号");
				Label label1 = new Label(1, 0, "课程中文名");
				Label label2 = new Label(2, 0, "课程英文名");
				Label label3 = new Label(3, 0, "学分");
				Label label4 = new Label(4, 0, "周学时");
				Label label5 = new Label(5, 0, "学期");
				Label label6 = new Label(6, 0, "教学方式");
				Label label7 = new Label(7, 0, "开设院系");
				Label label8 = new Label(8, 0, "开设年份");
				ws.addCell(label0);
				ws.addCell(label1);
				ws.addCell(label2);
				ws.addCell(label3);
				ws.addCell(label4);
				ws.addCell(label5);
				ws.addCell(label6);
				ws.addCell(label7);
				ws.addCell(label8);
				
				// 学生信息
				String sql = "select * from tb_course_info where college_id in (select college_id from" +
						" tb_college_info where college_id = " + account + ")";
				System.out.println(sql);
				AcdemicDAO dao = new AcdemicDAO();
				ArrayList<CourseForm> al = dao.queryCourseInfo(sql);
				for (int i = 0; i < al.size(); i ++) {
					Label[] label = new Label[9];
					label[0] = new Label(0, i + 1, Integer.toString(al.get(i).getId()));
					label[1] = new Label(1, i + 1, al.get(i).getNameC());
					label[2] = new Label(2, i + 1, al.get(i).getNameE());
					label[3] = new Label(3, i + 1, String.valueOf(al.get(i).getCredit()));
					label[4] = new Label(4, i + 1, Integer.toString(al.get(i).getWeekHour()));
					label[5] = new Label(5, i + 1, al.get(i).getSemester());
					label[6] = new Label(6, i + 1, al.get(i).getTeacherMode());
					label[7] = new Label(7, i + 1, Integer.toString(al.get(i).getCollegeId()));
					label[8] = new Label(8, i + 1, al.get(i).getYear());
					for (int j = 0; j < 9; j ++) ws.addCell(label[j]);
				}
				wwb.write();
				wwb.close();
				outStream.close();
				request.getRequestDispatcher("result.jsp?para=3").forward(request, response);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
