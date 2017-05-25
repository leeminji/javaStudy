package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
	public static Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "minji", "1125");
		}catch(Exception e){
			System.out.println("db연동 실패:"+e.getMessage());
		}
		return conn;		
	}
	
	//수행후 리소스 해제 메서드
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
