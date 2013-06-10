package com.core;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnDB {
	  public Connection conn = null;
	  public Statement stmt = null;
	  public ResultSet rs = null;
	  private static String dbClassName ="com.mysql.jdbc.Driver";
	  private static String dbUrl =
	      "jdbc:mysql://127.0.0.1:3306/db_stumanagesys";
	  public static String userName = "root";
	  public static String password = "root";
	  public ConnDB(){

	  }

	  public static Connection getConnection() {
	    Connection conn = null;
	    try {
	      Class.forName(dbClassName).newInstance();
	      conn = DriverManager.getConnection(dbUrl, userName, password);
	    }
	    catch (Exception ee) {
	      ee.printStackTrace();
	    }
	    if (conn == null) {
	      System.err.println(
	          "警告: DbConnectionManager.getConnection() 获得数据库链接失败.\r\n\r\n链接类型:" +
	          dbClassName + "\r\n链接位置:" + dbUrl);
	    }
	    return conn;
	  }

	/*
	 * 功能：执行查询语句
	 */
	public ResultSet executeQuery(String sql) {
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return rs;
	}

	/*
	 * 功能:执行更新操作
	 */
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			conn = getConnection();					//调用getConnection()方法构造Connection对象的一个实例conn
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);		//执行更新操作
		} catch (SQLException ex) {
			result = 0;
		}
		return result;
	}

	/*
	 * 功能:关闭数据库的连接
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
