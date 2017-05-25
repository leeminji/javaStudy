package com.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.MemberVO;
import com.util.DBManager;
import com.util.SHA256Util;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	private int count_per_page = 5;
	
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() { }
	public static MemberDAO getInstance(){
		return instance;
	}
	
	public int getCount_per_page() {
		return count_per_page;
	}
	public void setCount_per_page(int count_per_page) {
		this.count_per_page = count_per_page;
	}


	public int getLogin(String mb_id, String mb_pass){
		//result 0 : 아이디 없는경우
		//result 1 : 아이디 있고 비밀번호 맞는경우(로그인성공)
		//result -1 : 비밀번호가 아닌경우(로그인실패)
		int result = 0;
		String sql = "SELECT * FROM member WHERE mb_id = ?";
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				//로그인시 저장된 salt값으로 다시 암호화 한뒤 비교. 
				String mb_salt = rs.getString("mb_salt");
				String newPassword = SHA256Util.getEncrypt(mb_pass, mb_salt);
				if( newPassword.equals(rs.getString("mb_pass")) ){
					result = 1;
				}else{
					result = -1;
				}
			}
		}catch(Exception e){
			System.out.println("로그인 DAO 에러"+e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	
	//회원 정보 가져오기(아이디)
	public MemberVO getMember(String mb_id){
		String sql = "SELECT * FROM member WHERE mb_id = ?";
		MemberVO mVo = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if( rs.next() ){
				mVo = new MemberVO();
				mVo.setMb_id(rs.getString("mb_id"));
				mVo.setMb_name(rs.getString("mb_name"));
				mVo.setMb_pass(rs.getString("mb_pass"));
				mVo.setMb_level(rs.getInt("mb_level"));
				mVo.setMb_idx(rs.getInt("mb_idx"));
				mVo.setMb_regdate(rs.getTimestamp("mb_regdate"));
				mVo.setMb_lastest(rs.getTimestamp("mb_lastest"));
				mVo.setMb_email(rs.getString("mb_email"));
				mVo.setMb_tel(rs.getString("mb_tel"));
				mVo.setMb_hp(rs.getString("mb_hp"));
				mVo.setMb_addr1(rs.getString("mb_addr1"));
				mVo.setMb_addr2(rs.getString("mb_addr2"));
				mVo.setMb_zip(rs.getString("mb_zip"));
				mVo.setMb_mailing(rs.getInt("mb_mailing"));
				mVo.setMb_salt(rs.getString("mb_salt"));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage()+" id로 회원 정보 불러오기 에러 ");
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return mVo;
	}
	
	//회원 정보 가져오기(아이디)
	public MemberVO getMember(int mb_idx){
		String sql = "SELECT * FROM member WHERE mb_idx = ?";
		MemberVO mVo = null;
		ResultSet rs = null;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mb_idx);
			rs = pstmt.executeQuery();
			if( rs.next() ){
				mVo = new MemberVO();
				mVo.setMb_id(rs.getString("mb_id"));
				mVo.setMb_name(rs.getString("mb_name"));
				mVo.setMb_pass(rs.getString("mb_pass"));
				mVo.setMb_level(rs.getInt("mb_level"));
				mVo.setMb_idx(rs.getInt("mb_idx"));
				mVo.setMb_regdate(rs.getTimestamp("mb_regdate"));
				mVo.setMb_lastest(rs.getTimestamp("mb_lastest"));
				mVo.setMb_email(rs.getString("mb_email"));
				mVo.setMb_tel(rs.getString("mb_tel"));
				mVo.setMb_hp(rs.getString("mb_hp"));
				mVo.setMb_addr1(rs.getString("mb_addr1"));
				mVo.setMb_addr2(rs.getString("mb_addr2"));
				mVo.setMb_zip(rs.getString("mb_zip"));
				mVo.setMb_mailing(rs.getInt("mb_mailing"));
				mVo.setMb_salt(rs.getString("mb_salt"));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage()+" idx 로 회원 정보 불러오기 에러 ");
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return mVo;
	}
		
	//회원 입력하기
	public int insertMember(MemberVO mVo) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = " INSERT INTO member (mb_idx, mb_id, mb_name, mb_pass, mb_email, mb_mailing, mb_regdate, mb_lastest, mb_level, mb_addr1, mb_addr2, mb_zip, mb_tel, mb_hp, mb_salt ) ";
			   sql +=" VALUES (member_seq.nextval, ?,?,?,?,?, sysdate, sysdate, 1,?,?,?,?,?,?)";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getMb_id());
			pstmt.setString(2, mVo.getMb_name());
			pstmt.setString(3, mVo.getMb_pass());
			pstmt.setString(4, mVo.getMb_email());
			pstmt.setInt(5, mVo.getMb_mailing());
			pstmt.setString(6, mVo.getMb_addr1());
			pstmt.setString(7, mVo.getMb_addr2());
			pstmt.setString(8, mVo.getMb_zip());
			pstmt.setString(9, mVo.getMb_tel());
			pstmt.setString(10, mVo.getMb_hp());
			pstmt.setString(11, mVo.getMb_salt());
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("회원 정보 입력 에러"+ e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result; 
	}
	
	//회원 정보 수정
	public int updateMember(MemberVO mVo) {
		// TODO Auto-generated method stub
		int result = 0;
		int q = 0;
		System.out.println(mVo.toString());
		String sql = " UPDATE member SET ";
			   if( !mVo.getMb_pass().equals("") ){
			   sql+= " mb_pass = ?, ";
			   sql+= " mb_salt = ?, ";
			   }
			   sql+= " mb_email = ?, ";
			   sql+= " mb_zip = ?, ";
			   sql+= " mb_addr1 = ?, ";
			   sql+= " mb_addr2 = ?, ";
			   sql+= " mb_tel = ?, ";
			   sql+= " mb_hp = ?, ";
			   sql+= " mb_mailing = ?, ";
			   sql+= " mb_level = ? ";
			   sql+= " WHERE mb_id = ? ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			if( !mVo.getMb_pass().equals("") ){
			pstmt.setString(++q, mVo.getMb_pass());
			pstmt.setString(++q, mVo.getMb_salt());
			}
			pstmt.setString(++q, mVo.getMb_email());
			pstmt.setString(++q, mVo.getMb_zip());
			pstmt.setString(++q, mVo.getMb_addr1());
			pstmt.setString(++q, mVo.getMb_addr2());
			pstmt.setString(++q, mVo.getMb_tel());
			pstmt.setString(++q, mVo.getMb_hp());
			pstmt.setInt(++q, mVo.getMb_mailing());
			pstmt.setInt(++q, mVo.getMb_level());
			pstmt.setString(++q, mVo.getMb_id());

			result = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("회원정보 업데이트 에러"+e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	//전체갯수
	public int getTotalCount(String sfl, String stx){
		int result = 0;
		ResultSet rs = null;
		String sql = " SELECT count(*) as cnt FROM member ";
			if( sfl != null && !sfl.equals("")){
			   sql +=" WHERE "+sfl+" like ? ";
			}
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			if( sfl != null && !sfl.equals("")){
				if( sfl.equals("mb_level")){
					pstmt.setString(1, stx );	
				}else{
					pstmt.setString(1, "%"+stx+"%");
				}
			}
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("cnt");
		}catch (Exception e) {
			System.out.println(e.getMessage()+" 전체 갯수 가지고오기 에러 ");
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	
	//멤버리스트 가져오기
	public ArrayList<MemberVO> getMemberList(int firstRow , int endRow, String sfl, String stx, String sst) {
		// TODO Auto-generated method stub
		
		//페이징 처리
		String sql = " SELECT * FROM ";
			   sql += "    ( SELECT A.* , ROWNUM AS RNUM FROM ";
			   sql += "         ( SELECT * FROM MEMBER ";
			   //검색이 있을 경우 
			   if( sfl != null && !sfl.equals("")){
			   sql += "            WHERE "+ sfl + " like ? ";
			   }
			   //정렬이 있을 경우
			   if( sst != null && !sst.equals("")){
			   sql += "            ORDER BY "+sst+" DESC ";
			   //기본 정렬은 bo_num 순으로
			   }else{
			   sql += "            ORDER BY MB_IDX DESC ";
			   }
			   sql += "   ) A WHERE ROWNUM < ? ) ";
			   sql += " WHERE RNUM >= ? ";

		ResultSet rs = null;
		ArrayList<MemberVO> list = new ArrayList<>();
		MemberVO mVo = null;
		int k = 1;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			//검색이 있을 경우
			if( sfl != null && !sfl.equals("")){
			pstmt.setString(k++, "%"+stx+"%");
			}
			pstmt.setInt(k++, endRow);
			pstmt.setInt(k++, firstRow);
			rs = pstmt.executeQuery();
			int totalCount = getTotalCount(sfl, stx);
			int i = 0;			
			while(rs.next()){
				mVo = new MemberVO();
				mVo.setMb_eq( totalCount-(firstRow-1)-i );
				mVo.setMb_idx(rs.getInt("mb_idx"));
				mVo.setMb_name(rs.getString("mb_name"));
				mVo.setMb_email(rs.getString("mb_email"));
				mVo.setMb_id(rs.getString("mb_id"));
				mVo.setMb_pass(rs.getString("mb_pass"));
				mVo.setMb_level(rs.getInt("mb_level"));
				mVo.setMb_lastest(rs.getTimestamp("mb_lastest"));
				mVo.setMb_regdate(rs.getTimestamp("mb_regdate"));
				mVo.setMb_tel(rs.getString("mb_tel"));
				mVo.setMb_hp(rs.getString("mb_hp"));
				list.add(mVo);
			}

		}catch(Exception e){
			System.out.println(e.getMessage()+"멤버리스트 불러오기 에러");
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//회원 탈퇴처리
	public int outMember(int mb_idx) {
		String sql = "UPDATE MEMBER SET MB_LEVEL = 0 WHERE MB_IDX = ?";
		int result = 0;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mb_idx);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+" 회원  탈퇴처리 에러");
		}finally {
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
	//회원 삭제처리
	public int deleteMember(int mb_idx) {
		String sql = "DELETE FROM MEMBER WHERE MB_IDX = ?";
		int result = 0;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mb_idx);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+" 회원  삭제처리 에러");
		}finally {
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
	//회원 최근 접속정보 업데이트
	public int updateLastestDate(int mb_idx) {
		int result = 0;
		String sql = "UPDATE member SET mb_lastest = sysdate WHERE mb_idx = ?";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mb_idx);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+"회원 최근 접속정보 업데이트 에러");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}	
}
