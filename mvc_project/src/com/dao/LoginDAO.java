package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.dto.LoginVO;
import com.util.DBManager;

public class LoginDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;

	private static LoginDAO instance = new LoginDAO();
	private LoginDAO() {
	}
	public static LoginDAO getInstance() {
		return instance;
	}

	// 로그인 정보 넣기
	public int insertLogin(LoginVO lVo) {
		int result = 0;
		String sql = "INSERT INTO login VALUES(?,?, sysdate, ?,?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lVo.getLo_ip());
			pstmt.setString(2, lVo.getMb_id());
			pstmt.setString(3, lVo.getLo_location());
			pstmt.setString(4, lVo.getLo_url());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage() + "로그인 정보 저장 에러");
		} finally {
			DBManager.close(conn, pstmt, null);
		}

		return result;
	}

}
