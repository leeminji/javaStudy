package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.MenuVO;
import com.util.DBManager;

public class MenuDAO {
	
	private static MenuDAO instance = new MenuDAO();
	private MenuDAO(){}
	public static MenuDAO getInstance(){
		return instance;
	}
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	
	//메뉴 리스트 물어오기
	public ArrayList<MenuVO> getMenuList(){
		ArrayList<MenuVO> list = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT * FROM menu ORDER BY ME_NUM ASC";
		MenuVO mVo = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				mVo = new MenuVO();
				mVo.setMe_idx(rs.getInt("me_idx"));
				mVo.setMe_code(rs.getString("me_code"));
				mVo.setMe_link(rs.getString("me_link"));
				mVo.setMe_name(rs.getString("me_name"));
				mVo.setMe_is_view(rs.getInt("me_is_view"));
				mVo.setMe_is_target(rs.getInt("me_is_target"));
				mVo.setMe_content(rs.getString("me_content"));
				list.add(mVo);
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+"메뉴 리스트 불러오기 에러");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//메뉴 리스트 물어오기
	public ArrayList<MenuVO> getFrontMenuList(){
		ArrayList<MenuVO> list = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT * FROM menu WHERE me_is_view = 1 ORDER BY ME_NUM ASC";
		MenuVO mVo = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				mVo = new MenuVO();
				mVo.setMe_idx(rs.getInt("me_idx"));
				mVo.setMe_code(rs.getString("me_code"));
				mVo.setMe_link(rs.getString("me_link"));
				mVo.setMe_name(rs.getString("me_name"));
				mVo.setMe_is_view(rs.getInt("me_is_view"));
				mVo.setMe_is_target(rs.getInt("me_is_target"));
				mVo.setMe_content(rs.getString("me_content"));
				list.add(mVo);
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+"메뉴 리스트 불러오기 에러");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//업데이트
	public int updateMenu(MenuVO mVo) {
		System.out.println(mVo.toString());
		int result = 0;
		// TODO Auto-generated method stub
		String sql = " UPDATE menu SET ";
			   sql +=" me_num = ?, ";
			   sql +=" me_code = ?, ";
			   sql +=" me_name = ?, ";
			   sql +=" me_link = ?, ";
			   sql +=" me_is_view = ?, ";
			   sql +=" me_is_target = ?, ";
			   sql +=" me_content = ? ";
			   sql +=" WHERE me_idx = ? ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mVo.getMe_num());
			pstmt.setString(2, mVo.getMe_code());
			pstmt.setString(3, mVo.getMe_name());
			pstmt.setString(4, mVo.getMe_link());
			pstmt.setInt(5, mVo.getMe_is_view());
			pstmt.setInt(6, mVo.getMe_is_target());
			pstmt.setString(7, mVo.getMe_content());
			pstmt.setInt(8, mVo.getMe_idx());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage()+"메뉴 업데이트 오류");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
	//입력
	public int insertMenu(MenuVO mVo) {
		int result = 0;
		String sql = "INSERT INTO menu VALUES (menu_seq.nextval, ?,?,?,?,?,?,?)";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mVo.getMe_num());
			pstmt.setString(2, mVo.getMe_code());
			pstmt.setString(3, mVo.getMe_name());
			pstmt.setString(4, mVo.getMe_link());
			pstmt.setInt(5, mVo.getMe_is_view());
			pstmt.setInt(6, mVo.getMe_is_target());
			pstmt.setString(7, mVo.getMe_content());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+"메뉴 입력 에러");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
	//메뉴 삭제
	public int deleteMenu(int me_idx) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "DELETE FROM menu WHERE me_idx = ?";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, me_idx);
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage()+"메뉴삭제 에러");
		}finally {
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
}
