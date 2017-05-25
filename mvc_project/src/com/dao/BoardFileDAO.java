package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.BoardFileVO;
import com.util.DBManager;

public class BoardFileDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private static BoardFileDAO instance = new BoardFileDAO();
	private BoardFileDAO(){};
	public static BoardFileDAO getInstance(){
		return instance;
	}
	
	//삽입
	public int insertFile(BoardFileVO bfVo){
		int result = 0;
		String sql = " INSERT INTO BOARD_FILE (bo_table, bo_idx, bf_no, bf_file, bf_source, bf_download, bf_content, bf_filesize, bf_datetime) "
				   + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, sysdate) ";
		try{
			int q = 0;
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++q, bfVo.getBo_table());
			pstmt.setInt(++q, bfVo.getBo_idx());
			pstmt.setInt(++q, bfVo.getBf_no());
			pstmt.setString(++q, bfVo.getBf_file());
			pstmt.setString(++q, bfVo.getBf_source());
			pstmt.setInt(++q, bfVo.getBf_download());
			pstmt.setString(++q, bfVo.getBf_content());
			pstmt.setInt(++q, bfVo.getBf_filesize());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("파일입력실패"+e.getMessage());
		}finally {
			DBManager.close(conn, pstmt, null);	
		}
		return result;
	}
	
	//리스트
	public ArrayList<BoardFileVO> getFileList(String bo_table, int bo_idx){
		ArrayList<BoardFileVO> fileList = new ArrayList<BoardFileVO>();
		String sql = "select * from board_file where bo_table = ? and bo_idx = ? order by bf_no desc";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo_table);
			pstmt.setInt(2, bo_idx);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ){
				BoardFileVO bfVo = new BoardFileVO();
				
				bfVo.setBo_table(rs.getString("bo_table"));
				bfVo.setBo_idx(rs.getInt("bo_idx"));
				bfVo.setBf_no(rs.getInt("bf_no"));
				bfVo.setBf_file(rs.getString("bf_file"));
				bfVo.setBf_source(rs.getString("bf_source"));
				bfVo.setBf_download(rs.getInt("bf_download"));
				bfVo.setBf_content(rs.getString("bf_content"));
				bfVo.setBf_filesize(rs.getInt("bf_filesize"));
				bfVo.setBf_datetime(rs.getTimestamp("bf_datetime"));
				
				fileList.add(bfVo);
			}	
		}catch(Exception e){
			System.out.println("file 불러오기 오류: "+e.getMessage());
		}finally {
			DBManager.close(conn, pstmt, rs);	
		}
		return fileList;
	}
	
	//파일삭제
	public int deleteFile(String bo_table, int bo_idx, String bf_source){
		int reslut = 0;
		String sql = "DELETE FROM board_file WHERE bo_table = ? and bo_idx = ? and bf_source = ?";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo_table);
			pstmt.setInt(2, bo_idx);
			pstmt.setString(3, bf_source);
			reslut = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage()+"파일 삭제 에러");
		}finally{
			DBManager.close(conn, pstmt, null);	
		}
		return reslut;
	}
	
	//리스트
	public ArrayList<BoardFileVO> getFileImgList(String bo_table, int bo_idx){
		ArrayList<BoardFileVO> fileList = new ArrayList<BoardFileVO>();
		String sql = "select * from board_file where bo_table = ? and bo_idx = ? and ( bf_source like '%.jpg' or bf_source like '%.png' or bf_source like '%.gif' )  order by bf_no desc";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo_table);
			pstmt.setInt(2, bo_idx);

			rs = pstmt.executeQuery();			
			while( rs.next() ){
				BoardFileVO bfVo = new BoardFileVO();
				
				bfVo.setBo_table(rs.getString("bo_table"));
				bfVo.setBo_idx(rs.getInt("bo_idx"));
				bfVo.setBf_no(rs.getInt("bf_no"));
				bfVo.setBf_file(rs.getString("bf_file"));
				bfVo.setBf_source(rs.getString("bf_source"));
				bfVo.setBf_download(rs.getInt("bf_download"));
				bfVo.setBf_content(rs.getString("bf_content"));
				bfVo.setBf_filesize(rs.getInt("bf_filesize"));
				bfVo.setBf_datetime(rs.getTimestamp("bf_datetime"));
				
				fileList.add(bfVo);
			}	
		}catch(Exception e){
			System.out.println("file 불러오기 오류: "+e.getMessage());
		}finally {
			DBManager.close(conn, pstmt, rs);	
		}
		return fileList;
	}
	
	//해당 게시물 파일 모두 삭제
	public void deleteFileAll(String bo_table, int bo_idx) {
		String sql = "DELETE FROM board_file WHERE bo_table = ? and bo_idx = ? ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo_table);
			pstmt.setInt(2, bo_idx);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage()+"파일 삭제 에러");
		}finally{
			DBManager.close(conn, pstmt, null);	
		}	
	}
	
	//다운로드 업데이트
	public void updateDownload(String bf_source) {
		String sql = "UPDATE BOARD_FILE SET bf_download = bf_download+1 WHERE bf_source = ?";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bf_source);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("파일 다운로드업데이트 오류");
		}finally{
			DBManager.close(conn, pstmt, null);	
		}
	}
}
