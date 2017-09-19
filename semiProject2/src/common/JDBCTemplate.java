package common;

import java.sql.*;
import java.util.*;
import java.io.*;

public class JDBCTemplate {
/*	public static Connection getConnection(){
		
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("driver.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}*/
	
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","student","student");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public static void close(Connection conn){
		try {
			if(conn !=null &&!conn.isClosed()){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt){
		try {
			if(stmt !=null &&!stmt.isClosed()){
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset){
		try {
			if(rset !=null &&!rset.isClosed()){
				rset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn){
		try {
			if(conn != null && !conn.isClosed()){
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn){
		try {
			if(conn != null && !conn.isClosed()){
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
