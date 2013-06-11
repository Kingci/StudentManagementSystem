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
		
		request.setCharacterEncoding("utf-8");	//���ñ���
		String action = request.getParameter("action");
		//��ô����ļ���Ŀ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//��ȡ�ļ���Ҫ�ϴ�����·��
		String path = request.getSession().getServletContext().getRealPath("/upload/");
		System.out.println("path:" + path);
		//���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬
		//������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ
		/**
		 * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ� 
		 * ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem ��ʽ�� 
		 * Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ����
		 */
		factory.setRepository(new File(path));
		//���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
		factory.setSizeThreshold(1024*1024) ;
		
		//��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		try {
			//�����ϴ�����ļ�
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
			for(FileItem item : list)
			{
				//��ȡ������������
				String name = item.getFieldName();
				System.out.println("name:" + name);
				//�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ
				if(item.isFormField())
				{					
					//��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�
					String value = item.getString() ;
					request.setAttribute(name, value);
				}
				//�Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ
				else
				{
					//��ȡ·����
					String value = item.getName() ;
					//���������һ����б��
					int start = value.lastIndexOf("\\");
					//��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�
					String filename = value.substring(start+1);
					item.write(new File(path, filename));
					System.out.println("filename is " + filename + ", action is " + action);
					InputStream instream = new FileInputStream(path + "/" + filename);
					if (action.equals("importStudentInfo")) {  // ����Ա�˵���ѧ��ѧ����Ϣ
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
						for (int i = 1; i < rsRows; i ++) {
							stu_num = readsheet.getCell(i, 0).getContents();
							name_en = readsheet.getCell(i, 1).getContents();
							birth_time = readsheet.getCell(i, 2).getContents();
							gender = readsheet.getCell(i, 3).getContents();
							college_num = readsheet.getCell(i, 4).getContents();
							major_num = readsheet.getCell(i, 5).getContents();
							sch_length = readsheet.getCell(i, 6).getContents();
							id_num = readsheet.getCell(i, 7).getContents();
							entr_time = readsheet.getCell(i, 8).getContents();
							stu_status = readsheet.getCell(i, 9).getContents();
							gradu_sch = readsheet.getCell(i, 10).getContents();
							email = readsheet.getCell(i, 11).getContents();
							telephone = readsheet.getCell(i, 12).getContents();
							home_addr = readsheet.getCell(i, 13).getContents();
							pos_code = readsheet.getCell(i, 14).getContents();
							citizenship = readsheet.getCell(i, 15).getContents();
							nation = readsheet.getCell(i, 16).getContents();
							String sql = "insert into tb_Student(stu_num,name_en,birth_time,gender,college_num,major_num" +
									"sch_length,id_num,entr_time,stu_status,gradu_sch,email,telephone,home_addr,pos_code" +
									"citizenship,nation) values (' + " + stu_num + "','" + name_en + "','" + birth_time + "','"+
									gender + "','" + college_num + "','" + major_num + "','" + sch_length + "','" + id_num +
									"','" + entr_time + "','" + stu_status + "','" + gradu_sch + "','" + email + "','" +
									telephone + "','" + home_addr + "','" + pos_code + "','" + citizenship + "','" +
									nation +
									"')";
							System.out.println("sql is :" + sql);
							if (dao.insertStudentInfo(sql) <= 0) {
								error = true;
								break;
							}
						}
						if (error)
							request.getRequestDispatcher("result.jsp?para=1").forward(request, response);
						else 
							request.getRequestDispatcher("result.jsp?para=2").forward(request, response);
					}
				}
			}	
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		

	}

}
