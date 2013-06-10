package com.dao;

import com.core.*;
import java.util.*;
import com.actionForm.AccountForm;
import java.sql.*;
import java.*;

public class AccountDAO {
    private ConnDB conn = new ConnDB();
    //查询数据
   /* public Collection query(String queryif) {
        AccountForm AccountForm = null;
      Collection Accountcoll = new ArrayList();
        String sql = "";
        if (queryif == null || queryif == "" || queryif == "all") {	//当参数queryif的值为null、all或空时查询全部数据
            sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_Account m left join tb_purview p on m.id=p.id";
            }else{
                sql="select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_Account m left join tb_purview p on m.id=p.id where m.name='"+queryif+"'";			//此处需要应用左连接
        }
        ResultSet rs = conn.executeQuery(sql);					//执行SQL语句
        try {											//捕捉异常信息
            while (rs.next()) {
                AccountForm = new AccountForm();
                AccountForm.setId(Integer.valueOf(rs.getString(1)));
                AccountForm.setName(rs.getString(2));
                AccountForm.setPwd(rs.getString(3));
                AccountForm.setSysset(rs.getInt(4));
                AccountForm.setReaderset(rs.getInt(5));
                AccountForm.setBookset(rs.getInt(6));
                AccountForm.setBorrowback(rs.getInt(7));
                AccountForm.setSysquery(rs.getInt(8));
                Accountcoll.add(AccountForm);			//将查询结果保存到Collection集合中
            }
        } catch (SQLException e) {}
        return Accountcoll;								//返回查询结果
    }*/

    /*********************************************************/
    //查询权限信息
   /* public AccountForm query_p(String str) {
        AccountForm AccountForm1 = null;
        String sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_Account m left join tb_purview p on m.id=p.id where m.name='"+str+"'";

        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                AccountForm1 = new AccountForm();
                AccountForm1.setId(Integer.valueOf(rs.getString(1)));
                AccountForm1.setName(rs.getString(2));
                AccountForm1.setPwd(rs.getString(3));
                AccountForm1.setSysset(rs.getInt(4));
                AccountForm1.setReaderset(rs.getInt(5));
                AccountForm1.setBookset(rs.getInt(6));
                AccountForm1.setBorrowback(rs.getInt(7));
                AccountForm1.setSysquery(rs.getInt(8));
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }finally{
        	conn.close();
        }
        return AccountForm1;
    }*/


    //管理员身份验证
    public int checkAccount(AccountForm accountForm) {
        int flag = 0;
        ChStr chStr=new ChStr();
        String sql = "SELECT * FROM tb_account where aname='" +
        chStr.filterStr(accountForm.getName()) + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                String pwd = chStr.filterStr(accountForm.getPwd());		//获取输入的密码并过滤输入字符串中的危险字符
                if (pwd.equals(rs.getString(3))) {
                    flag = 1;
                } else {
                    flag = 0;
                }
            }else{
                flag = 0;
            }
        } catch (SQLException ex) {
            flag = 0;
        }finally{
        	conn.close();
        }
        return flag;
    }
    
    public String getAccountType(AccountForm accountForm){
    	String accountType = "";
        ChStr chStr=new ChStr();
        String sql = "SELECT * FROM tb_account where aname='" +
        chStr.filterStr(accountForm.getName()) + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
			if (rs.next()) {
			    String pwd = chStr.filterStr(accountForm.getPwd());		//获取输入的密码并过滤输入字符串中的危险字符
			    if (pwd.equals(rs.getString(3))) {
			        accountType = rs.getString(4);
			    } 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return accountType;
    }

   /* //修改时应用的查询方法
    public AccountForm query_update(AccountForm AccountForm) {
        AccountForm AccountForm1 = null;
        String sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_Account m left join tb_purview p on m.id=p.id where m.id=" +
                     AccountForm.getId() + "";

        ResultSet rs = conn.executeQuery(sql);	//执行查询语句
        try {
            while (rs.next()) {
                AccountForm1 = new AccountForm();
                AccountForm1.setId(Integer.valueOf(rs.getString(1)));
                AccountForm1.setName(rs.getString(2));
                AccountForm1.setPwd(rs.getString(3));
                AccountForm1.setSysset(rs.getInt(4));
                AccountForm1.setReaderset(rs.getInt(5));
                AccountForm1.setBookset(rs.getInt(6));
                AccountForm1.setBorrowback(rs.getInt(7));
                AccountForm1.setSysquery(rs.getInt(8));
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();	//输出异常信息
        }finally{
        	conn.close();	//关闭数据库连接
        }
        return AccountForm1;
    }*/
    //更改口令时应用的查询方法
    public AccountForm query_pwd(AccountForm AccountForm) {
        AccountForm AccountForm1 = null;
        String sql = "SELECT * FROM tb_Account WHERE name='" +AccountForm.getName() + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                AccountForm1 = new AccountForm();
                AccountForm1.setId(Integer.valueOf(rs.getString(1)));
                AccountForm1.setName(rs.getString(2));
                AccountForm1.setPwd(rs.getString(3));
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }finally{
        	conn.close();
        }
        return AccountForm1;
    }
    //添加数据
    public int insert(AccountForm AccountForm) {
        String sql1="SELECT * FROM tb_Account WHERE name='"+AccountForm.getName()+"'";
        ResultSet rs = conn.executeQuery(sql1);
        String sql = "";
        int falg = 0;
            try {
                if (rs.next()) {
                    falg=2;
                } else {
                    sql = "INSERT INTO tb_Account (name,pwd) values('" +
                                 AccountForm.getName() + "','" +
                                 AccountForm.getPwd() +
                                 "')";
                    falg = conn.executeUpdate(sql);
                    System.out.println("添加管理员信息的SQL：" + sql);
                }
            } catch (SQLException ex) {
                falg=0;
            }finally{
            	conn.close();
            }
        return falg;
    }

    //设置管理员权限
    /*public int update(AccountForm AccountForm) {
        String sql1="SELECT * FROM tb_purview WHERE id="+AccountForm.getId()+"";
        ResultSet rs=conn.executeQuery(sql1);	//查询要设置权限的管理员的权限信息
        String sql="";
        int falg=0;
        try {					//捕捉异常信息
            if (rs.next()) {	//当已经设置权限时，执行更新语句
                sql = "Update tb_purview set sysset=" + AccountForm.getSysset() +
                             ",readerset=" + AccountForm.getReaderset() + ",bookset="+AccountForm.getBookset()+",borrowback="+AccountForm.getBorrowback()+",sysquery="+AccountForm.getSysquery()+" where id=" +
                     AccountForm.getId() + "";
            }else{	//未设置权限时，执行插入语句
                sql="INSERT INTO tb_purview values("+AccountForm.getId()+","+AccountForm.getSysset()+","+AccountForm.getReaderset()+","+AccountForm.getBookset()+","+AccountForm.getBorrowback()+","+AccountForm.getSysquery()+")";
            }
            falg = conn.executeUpdate(sql);
            System.out.println("修改数据时的SQL：" + sql);
        } catch (SQLException ex) {
            falg=0;			//表示设置管理员权限失败
        }finally{
        	conn.close();	//关闭数据库连接
        }
        return falg;
    }*/
    //修改管理员密码
public int updatePwd(AccountForm AccountForm){
    String sql="UPDATE tb_Account SET pwd='"+AccountForm.getPwd()+"' where name='"+AccountForm.getName()+"'";
    int ret=conn.executeUpdate(sql);
    System.out.println("修改管理员密码时的SQL："+sql);
    conn.close();
    return ret;
}

//删除数据
    public int delete(AccountForm AccountForm) {
    	int flag=0;
    	try{		//捕捉异常信息
        String sql = "DELETE FROM tb_Account where id=" + AccountForm.getId() +"";
        flag = conn.executeUpdate(sql);	//执行删除管理员信息的语句
        if (flag !=0){
            String sql1 = "DELETE FROM tb_purview where id=" + AccountForm.getId() +"";
            conn.executeUpdate(sql1);	//执行删除权限信息的语句
        }}catch(Exception e){
        	System.out.println("删除管理员信息时产生的错误："+e.getMessage());	//输出错误信息
        }finally{
        	conn.close();	//关闭数据库连接
        }
        return flag;
    }
}
