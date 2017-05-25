package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.dto.VisitVO;
import com.util.DBManager;

public class VisitDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	private static VisitDAO instacne = new VisitDAO();
	private VisitDAO(){}
	public static VisitDAO getInstance(){
		return instacne;
	}
	//VI_IDX VI_IP VI_REGDATE VI_REFERER VI_AGENT VI_BROWER VI_OS VI_DEVICE
	public void inertVisit(VisitVO vVo){
		String sql = "INSERT INTO visit VALUES(VISIT_SEQ.NEXTVAL, ?, sysdate,?,?,?,?,? )";
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vVo.getVi_ip());
			pstmt.setString(2, vVo.getVi_referer());
			pstmt.setString(3, vVo.getVi_agent());
			pstmt.setString(4, vVo.getVi_brower());
			pstmt.setString(5, vVo.getVi_os());
			pstmt.setString(6, vVo.getVi_device());
			
			pstmt.executeUpdate();
					
		}catch(Exception e){
			System.out.println(e.getMessage()+" visit 테이블 정보 넣기 에러 ");
		}finally {
			DBManager.close(conn, pstmt, null);
		}
	}
}
