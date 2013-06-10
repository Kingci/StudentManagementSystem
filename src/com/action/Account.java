package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.actionForm.AccountForm;
import com.dao.AccountDAO;
//import javax.jms.Session;

public class Account extends HttpServlet {
	private AccountDAO accountDAO = null; // 声明erDAO的对象

	public Account() {
		this.accountDAO = new AccountDAO(); // 实例化AccountDAO类
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("获取的查询字符串：" + action);
		if (action == null || "".equals(action)) {
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if ("login".equals(action)) {// 当action值为login时，调用AccountLogin()方法验证管理员身份
			AccountLogin(request, response);
		} /*else if ("AccountAdd".equals(action)) {
			AccountAdd(request, response);// 添加管理员信息
		} else if ("AccountQuery".equals(action)) {
			AccountQuery(request, response);// 查询管理员及权限信息
		} else if ("AccountModifyQuery".equals(action)) {
			AccountModifyQuery(request, response);// 设置管理员权限时查询管理员信息
		} else if ("AccountModify".equals(action)) {
			AccountModify(request, response);// 设置管理员权限
		} else if ("AccountDel".equals(action)) {
			AccountDel(request, response);// 删除管理员
		} else if ("querypwd".equals(action)) {
			pwdQuery(request, response);// 更改口令时应用的查询
		} else if ("modifypwd".equals(action)) {
			modifypwd(request, response); // 更改口令
		}*/
	}

	// 管理员身份验证

	public void AccountLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm accountForm = new AccountForm();
		accountForm.setName(request.getParameter("name"));
		accountForm.setPwd(request.getParameter("pwd"));
		accountForm.setRand(request.getParameter("rand"));
		
		System.out.println(accountForm.getName() + accountForm.getPwd() + accountForm.getRand());
		HttpSession session_rand=request.getSession();
		String rand = (String)session_rand.getAttribute("rand");
		if(!rand.equals(request.getParameter("rand"))){
			request.setAttribute("error", "您输入的验证码错误！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);//转到错误提示页
		}
		else{
			int ret = accountDAO.checkAccount(accountForm);//调用AccountDAO类的checkAccount()方法
			if (ret == 1) {
				String accountType = accountDAO.getAccountType(accountForm);
	            HttpSession session=request.getSession();
	            session.setAttribute("Account",accountForm.getName());
	            session.setAttribute("AccountType", accountType);
	            if (accountType.equals("Student")) {
	            	response.sendRedirect("Student/index.jsp");
	            }
	            else if (accountType.equals("Acdemic Dean")) {
	            	response.sendRedirect("AcdemicDean/index.jsp");
	            }
	            else if (accountType.equals("Administrator")) {
	            	response.sendRedirect("Admin/index.jsp");
	            }
			} else {
				request.setAttribute("error", "您输入的账户名或密码错误！");
				request.getRequestDispatcher("error.jsp")
						.forward(request, response);//转到错误提示页
			}
		}
	}

	// 查询管理员信息
/*	private void AccountQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("AccountQuery", AccountDAO.query(str));
		request.getRequestDispatcher("Account.jsp").forward(request, response);
	}*/

	// 添加管理员信息
	/*private void AccountAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm accountForm = new AccountForm();
		accountForm.setName(request.getParameter("name")); // 获取设置管理员名称
		accountForm.setPwd(request.getParameter("pwd")); // 获取并设置密码
		int ret = AccountDAO.insert(accountForm); // 调用添加管理员信息
		if (ret == 1) {
			request.getRequestDispatcher("Account_ok.jsp?para=1").forward(
					request, response); // 转到管理员信息添加成功页面

		} else if (ret == 2) {
			request.setAttribute("error", "该管理员信息已经添加！"); // 将错误信息保存到error参数中
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // 转到错误提示页面
		} else {
			request.setAttribute("error", "添加管理员信息失败！"); // 将错误信息保存到error参数中
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // 转到错误提示页面
		}
	}*/

	// 查询修改管理员信息
	/*private void AccountModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		AccountForm.setId(Integer.valueOf(request.getParameter("id")));// 获取并设置管理ID号
		System.out.print("查询到的id:" + request.getParameter("id"));
		request.setAttribute("AccountQueryif", AccountDAO
				.query_update(AccountForm));
		request.getRequestDispatcher("Account_Modify.jsp").forward(request,
				response); // 转到权限设置成功页面
	}

	// 修改密码时查询
	private void pwdQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		HttpSession session = request.getSession();
		String Account = (String) session.getAttribute("Account");
		AccountForm.setName(Account);
		System.out.print("查询到的Account:" + Account);
		request.setAttribute("pwdQueryif", AccountDAO.query_pwd(AccountForm));
		request.getRequestDispatcher("pwd_Modify.jsp").forward(request,
				response);
	}

	// 管理员权限设置
	private void AccountModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		AccountForm.setId(Integer.parseInt(request.getParameter("id"))); // 获取并设置管理员ID号
		AccountForm.setName(request.getParameter("name")); // 获取并设置管理员名称
		AccountForm.setPwd(request.getParameter("pwd")); // 获取并设置管理员密码
		AccountForm.setSysset(request.getParameter("sysset") == null ? 0
				: Integer.parseInt(request.getParameter("sysset"))); // 获取并设置系统设置权限
		AccountForm.setReaderset(request.getParameter("readerset") == null ? 0
				: Integer.parseInt(request.getParameter("readerset"))); // 获取并设置读者管理权限
		AccountForm.setBookset(request.getParameter("bookset") == null ? 0
				: Integer.parseInt(request.getParameter("bookset"))); // 获取并设置图书管理权限
		AccountForm
				.setBorrowback(request.getParameter("borrowback") == null ? 0
						: Integer.parseInt(request.getParameter("borrowback"))); // 获取并设置图书借还权限
		AccountForm.setSysquery(request.getParameter("sysquery") == null ? 0
				: Integer.parseInt(request.getParameter("sysquery"))); // 获取并设置系统查询权限
		int ret = AccountDAO.update(AccountForm); // 调用设置管理员权限的方法
		if (ret == 0) {
			request.setAttribute("error", "设置管理员权限失败！"); // 保存错误提示信息到error参数中
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // 转到错误提示页面
		} else {
			request.getRequestDispatcher("Account_ok.jsp?para=2").forward(
					request, response);// 转到权限设置成功页面
		}
	}

	// 删除管理员信息
	private void AccountDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		AccountForm.setId(Integer.valueOf(request.getParameter("id"))); // 获取并设置管理员ID号
		int ret = AccountDAO.delete(AccountForm); // 调用删除信息的方法delete()
		if (ret == 0) {
			request.setAttribute("error", "删除管理员信息失败！"); // 保存错误提示信息到error参数中
			request.getRequestDispatcher("error.jsp")
					.forward(request, response); // 转到错误提示页面
		} else {
			request.getRequestDispatcher("Account_ok.jsp?para=3").forward(
					request, response); // 转到删除管理员信息成功页面
		}
	}

	// 修改管理员密码
	private void modifypwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AccountForm AccountForm = new AccountForm();
		AccountForm.setName(request.getParameter("name"));
		AccountForm.setPwd(request.getParameter("pwd"));
		int ret = AccountDAO.updatePwd(AccountForm);
		if (ret == 0) {
			request.setAttribute("error", "更改口令失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("pwd_ok.jsp").forward(request,
					response);
		}
	}*/

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
