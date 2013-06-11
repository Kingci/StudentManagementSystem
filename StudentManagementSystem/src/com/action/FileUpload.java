package com.action;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.*;
import jxl.write.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dao.AcdemicDAO;


public class FileUpload extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");	//设置编码
		String action = request.getParameter("action");
		//获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//获取文件需要上传到的路径
		String path = request.getSession().getServletContext().getRealPath("/upload/");
		System.out.println("path:" + path);
		//如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		//设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 
		 * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的 
		 * 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(path));
		//设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024*1024) ;
		
		//高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		try {
			//可以上传多个文件
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
			for(FileItem item : list)
			{
				//获取表单的属性名字
				String name = item.getFieldName();
				System.out.println("name:" + name);
				//如果获取的 表单信息是普通的 文本 信息
				if(item.isFormField())
				{					
					//获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = item.getString() ;
					request.setAttribute(name, value);
				}
				//对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else
				{
					//获取路径名
					String value = item.getName() ;
					//索引到最后一个反斜杠
					int start = value.lastIndexOf("\\");
					//截取 上传文件的 字符串名字，加1是 去掉反斜杠，
					String filename = value.substring(start+1);
					item.write(new File(path, filename));
					//System.out.println("filename is " + filename + ", action is " + action);
					InputStream instream = new FileInputStream(path + "/" + filename);
					System.out.println(action);
					if (action.equals("importStudentInfo")) {  // 教务员端导入学生学籍信息
						AcdemicDAO dao = new AcdemicDAO();
						
						Workbook readwb = Workbook.getWorkbook(instream);
						Sheet readsheet = readwb.getSheet(0);
						int rsColumns = readsheet.getColumns();
						int rsRows = readsheet.getRows();
						System.out.println("columns: " + rsColumns + ",rows:" + rsRows);
						String stu_num = "";
						String name_en = "";
						String birth_time = "";
						String gender = "";
						String college_num = "";
						String major_num = "";
						String sch_length = "";
						String id_num = "";
						String entr_time = "";
						String stu_status = "";
						String gradu_sch = "";
						String email = "";
						String telephone = "";
						String home_addr = "";
						String pos_code =  "";
						String citizenship = "";
						String nation = "";
						boolean error = false;
						for (int i = 1; i < rsRows ; i ++) {
							stu_num = readsheet.getCell(0, i).getContents();
							name_en = readsheet.getCell(1, i).getContents();
							birth_time = readsheet.getCell(2, i).getContents();
							gender = readsheet.getCell(3, i).getContents();
							college_num = readsheet.getCell(4, i).getContents();
							major_num = readsheet.getCell(5, i).getContents();
							sch_length = readsheet.getCell(6, i).getContents();
							id_num = readsheet.getCell(7, i).getContents();
							entr_time = readsheet.getCell(8, i).getContents();
							stu_status = readsheet.getCell(9, i).getContents();
							gradu_sch = readsheet.getCell(10, i).getContents();
							email = readsheet.getCell(11, i).getContents();
							telephone = readsheet.getCell(12, i).getContents();
							home_addr = readsheet.getCell(13, i).getContents();
							pos_code = readsheet.getCell(14, i).getContents();
							citizenship = readsheet.getCell(15, i).getContents();
							nation = readsheet.getCell(16, i).getContents();
							String sql = "insert into tb_Student(stu_num,name_ch,birth_time,gender,college_num,major_num," +
									"sch_length,id_num,entr_time,stu_status,gradu_sch,email,telephone,home_addr,pos_code," +
									"citizenship,nation) values ('" + stu_num + "','" + name_en + "','" + birth_time + "','"+
									gender + "','" + college_num + "','" + major_num + "','" + sch_length + "','" + id_num +
									"','" + entr_time + "','" + stu_status + "','" + gradu_sch + "','" + email + "','" +
									telephone + "','" + home_addr + "','" + pos_code + "','" + citizenship + "','" +
									nation +
									"')";
							System.out.println("stu_num is " + stu_num);
							if (dao.isExistStudent(stu_num) == 0) {	//避免重复插入
								if (dao.insertStudentInfo(sql) <= 0) {
									error = true;
									break;
								}
							}
						}	
						System.out.println( "error is " + error);
						if (!error)
							request.getRequestDispatcher("result.jsp?para=1").forward(request, response);
						else 
							request.getRequestDispatcher("result.jsp?para=2").forward(request, response);
					}
					if (action.equals("importCourseInfo")) {  // 教务员端导入课程信息
						AcdemicDAO daocourse = new AcdemicDAO();
						
						Workbook readcourse = Workbook.getWorkbook(instream);
						Sheet readsheetcourse = readcourse.getSheet(0);
						int cColumns = readsheetcourse.getColumns();
						int cRows = readsheetcourse.getRows();
						System.out.println("columns: " + cColumns + ",rows:" + cRows);
						String course_id = "";
						String course_name_chs = "";
						String course_name_egh = "";
						String credit = "";
						String week_hour = "";
						String semester = "";
						String teach_mode = "";
						String college_id = "";
						String course_year = "";
						boolean errorcourse = false;
						for (int i = 1; i < cRows ; i ++) {
							course_id = readsheetcourse.getCell(0, i).getContents();
							course_name_chs = readsheetcourse.getCell(1, i).getContents();
							course_name_egh = readsheetcourse.getCell(2, i).getContents();
							credit = readsheetcourse.getCell(3, i).getContents();
							week_hour = readsheetcourse.getCell(4, i).getContents();
							semester = readsheetcourse.getCell(5, i).getContents();
							teach_mode = readsheetcourse.getCell(6, i).getContents();
							college_id = readsheetcourse.getCell(7, i).getContents();
							course_year = readsheetcourse.getCell(8, i).getContents();
							
							String sql = "insert into tb_course_info(course_id,course_name_chs,course_name_egh,credit,week_hour,semester,teach_mode,college_id," + 
									"course_year) values (" + course_id + ",'" + course_name_chs + "','"+ course_name_egh + "'," +
									credit + "," + week_hour + ",'" + semester + "','" + teach_mode + "'," + college_id +
									",'" + course_year + "')";
							System.out.println(sql);
							if (daocourse.insertCourseInfo(sql) <= 0) {
								errorcourse = true;
								break;
							}
						}
						System.out.println(errorcourse);
						if (!errorcourse)
							request.getRequestDispatcher("result.jsp?para=1").forward(request, response);
						else 
							request.getRequestDispatcher("result.jsp?para=2").forward(request, response);
					}
				}
			}	
		}
		catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		

	}

}
