package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;

import com.dto.ConfigVO;
import com.util.DBManager;

public class ConfigDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;

	private static ConfigDAO instance = new ConfigDAO();
	private ConfigDAO() {}
	public static ConfigDAO getInstance() {
		return instance;
	}

	// config 테이블 정보 가져오기
	public ConfigVO getConfig() {
		ResultSet rs = null;
		String sql = "SELECT * FROM config";
		ConfigVO cVo = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cVo = ConfigVO.getInstance();
				cVo.setCf_title(rs.getString("cf_title"));
				cVo.setCf_admin(rs.getString("cf_admin"));
				cVo.setCf_admin_name(rs.getString("cf_admin_name"));
				cVo.setCf_admin_email(rs.getString("cf_admin_email"));
				cVo.setCf_addr(rs.getString("cf_addr"));
				cVo.setCf_tel(rs.getString("cf_tel"));
				cVo.setCf_info1(rs.getString("cf_info1"));
				cVo.setCf_info2(rs.getString("cf_info2"));
				cVo.setCf_info3(rs.getString("cf_info3"));
				cVo.setCf_use_addr(rs.getInt("cf_use_addr"));
				cVo.setCf_use_hp(rs.getInt("cf_use_hp"));
				cVo.setCf_use_addr(rs.getInt("cf_use_addr"));
				cVo.setCf_privacy(rs.getString("cf_privacy"));
				cVo.setCf_service(rs.getString("cf_service"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("config 정보 가져오기 에러" + e.getMessage());
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return cVo;
	}

	// 환경설정 입력
	public int insertConfig(ConfigVO cVo) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = " UPDATE config SET ";
			   sql+= " cf_title = ? ,";
			   sql+= " cf_admin = ? ,";
			   sql+= " cf_admin_email = ? ,";
			   sql+= " cf_admin_name = ? ,";
			   sql+= " cf_addr = ? ,";
			   sql+= " cf_tel = ? ,";
			   sql+= " cf_info1 = ? ,";
			   sql+= " cf_info2 = ? ,";
			   sql+= " cf_info3 = ? ,";
			   sql+= " cf_use_addr = ? ,";
			   sql+= " cf_use_tel = ? ,";
			   sql+= " cf_use_hp = ? ,";
			   sql+= " cf_privacy = ? ,";
			   sql+= " cf_service = ? ";			   
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cVo.getCf_title());
			pstmt.setString(2, cVo.getCf_admin());
			pstmt.setString(3, cVo.getCf_admin_email());
			pstmt.setString(4, cVo.getCf_admin_name());
			pstmt.setString(5, cVo.getCf_addr());
			pstmt.setString(6, cVo.getCf_tel());
			pstmt.setString(7, cVo.getCf_info1());
			pstmt.setString(8, cVo.getCf_info2());
			pstmt.setString(9, cVo.getCf_info3());
			pstmt.setInt(10, cVo.getCf_use_addr());
			pstmt.setInt(11, cVo.getCf_use_tel());
			pstmt.setInt(12, cVo.getCf_use_hp());
			pstmt.setString(13, cVo.getCf_privacy());
			pstmt.setString(14, cVo.getCf_service());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+" 환경설정 업데이트 오류 ");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
}
