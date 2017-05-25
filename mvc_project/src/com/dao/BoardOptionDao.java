package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.BoardOptionVO;
import com.util.DBManager;

public class BoardOptionDao {
	private int count_per_page = 10;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private final static String TABLE = "board_option";
	
	private static BoardOptionDao instacne = new BoardOptionDao();
	private BoardOptionDao(){}
	public static BoardOptionDao getInstance(){
		return instacne;
	}
	
	public int getCount_per_page() {
		return count_per_page;
	}
	public void setCount_per_page(int count_per_page) {
		this.count_per_page = count_per_page;
	}
	//총 게시물 옵션 갯수 가져오기.
	public int getTotalCount(String sfl, String stx){
		String sql =  "SELECT count(*) FROM "+TABLE+" ";
		   if( sfl != null && !sfl.equals("")){
		   sql += " WHERE "+sfl+" like ? ";
		   }
		ResultSet rs = null;
		int reslut = 0;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			if( sfl != null && !sfl.equals("")){
				pstmt.setString(1, "%"+stx+"%");
			}
			rs = pstmt.executeQuery();
			rs.next();
			reslut = rs.getInt(1);
		}catch(Exception e){
			System.out.println("게시물옵션 총 갯수 가져오기 에러 "+e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return reslut;		
	}
	
	//Boardoption 리스트 가져오기
	public ArrayList<BoardOptionVO> getBoardOptionList(int firstRow , int endRow, String sfl, String stx, String sst){
		ArrayList<BoardOptionVO> list = new ArrayList<>();
		System.out.println(firstRow+"/"+endRow);
		//페이징 처리
		String sql = " SELECT * FROM ";
			   sql += "    ( SELECT A.* , ROWNUM AS RNUM FROM ";
			   sql += "         ( SELECT * FROM "+ TABLE;
			   //검색이 있을 경우 
			   if( sfl != null && !sfl.equals("")){
			   sql += "            WHERE "+ sfl + " like ? ";
			   }
			   //정렬이 있을 경우
			   if( sst != null && !sst.equals("")){
			   sql += "            ORDER BY "+sst+" DESC ";
			   //기본 정렬은 bo_num 순으로
			   }else{
			   sql += "            ORDER BY OP_IDX DESC ";
			   }
			   sql += "   ) A WHERE ROWNUM < ? ) ";
			   sql += " WHERE RNUM >= ? ";
			   
		BoardOptionVO optVo = null;
		ResultSet rs = null;
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
				optVo = new BoardOptionVO();
				optVo.setOp_idx(rs.getInt("op_idx"));
				optVo.setOp_name(rs.getString("op_name"));
				optVo.setOp_table(rs.getString("op_table"));
				optVo.setOp_skin(rs.getString("op_skin"));
				optVo.setOp_adm_skin(rs.getString("op_adm_skin"));
				optVo.setOp_regdate(rs.getTimestamp("op_regdate"));
				optVo.setOp_thumb(rs.getString("op_thumb"));
				optVo.setOp_img_width(rs.getInt("op_img_width"));
				optVo.setView_href("?idx="+rs.getInt("op_idx"));
				list.add(optVo);
			}
			
		}catch(Exception e){
			System.out.println("옵션 가져오기 에러:"+e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	//op_table 이 있는지 판별 ( 있으면 true, 없으면 false 반환)
	public boolean isBoardOptionOpName(String op_table){
		boolean result = false;
		ResultSet rs = null;
		String sql = "SELECT count(op_table) as cnt FROM "+ TABLE + " WHERE op_table = ? ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, op_table);
			rs = pstmt.executeQuery();
			rs.next();
			
			if( rs.getInt("cnt") != 0 ){
				result = true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	
	//boardoption 입력 
	public int insertBoardOption(BoardOptionVO optionVo) {
		// TODO Auto-generated method stub
		int result = 0;
		// OP_IDX OP_NAME OP_TABLE OP_SKIN OP_ADM_SKIN OP_IS_SECRET OP_IS_IP OP_IS_SIGN OP_NEW_DATE OP_REGDATE 
		// OP_VIEW_LEVEL OP_LIST_LEVEL OP_WRITE_LEVEL OP_COMMENT_LEVEL OP_REPLY_LEVEL OP_PAGE_LENGTH OP_IS_PREVIEW 
		// OP_IS_NOTICE OP_CATE OP_IMG_WIDTH OP_THUMB
		String sql = " INSERT INTO "+ TABLE + " VALUES ( ";
			   sql +=" BOARD_OPTION_SEQ.NEXTVAL ,";
			   sql +=" ?, ";
			   sql +=" ?, ";
			   sql +=" ?, ";
			   sql +=" ?, ";
			   sql +=" ?, ";
			   sql +=" ?, ";
			   sql +=" ?, ";
			   sql +=" ?, ";
			   sql +=" sysdate , ";
			   sql +=" ?, ";
			   sql +=" ?, ";
			   sql +=" ?, "; //OP_WRITE_LEVEL
			   sql +=" ?, "; //OP_COMMENT_LEVEL
			   sql +=" ?, "; //OP_REPLY_LEVEL
			   sql +=" ?, "; //OP_PAGE_LENGTH
			   sql +=" ?, "; //OP_IS_PREVIEW
			   sql +=" ?, "; //OP_IS_NOTICE
			   sql +=" ?, "; //OP_CATE
			   sql +=" ?, "; //OP_IMG_WIDTH
			   sql +=" ? "; //OP_THUMB
			   sql +=" ) ";
		try{
			
			//op_name 이 없으면 수행
			if( !isBoardOptionOpName(optionVo.getOp_name()) ){
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, optionVo.getOp_name());
				pstmt.setString(2, optionVo.getOp_table() );
				pstmt.setString(3, optionVo.getOp_skin() );
				pstmt.setString(4, optionVo.getOp_adm_skin() );
				pstmt.setInt(5, optionVo.getOp_is_secret() );
				pstmt.setInt(6, optionVo.getOp_is_ip() );
				pstmt.setInt(7, optionVo.getOp_is_sign() );
				pstmt.setInt(8, optionVo.getOp_new_date() );
				pstmt.setInt(9, optionVo.getOp_view_level() );
				pstmt.setInt(10, optionVo.getOp_list_level() );
				pstmt.setInt(11, optionVo.getOp_write_level() );
				pstmt.setInt(12, optionVo.getOp_comment_level() );
				pstmt.setInt(13, optionVo.getOp_reply_level() );
				pstmt.setInt(14, optionVo.getOp_page_length() );
				pstmt.setInt(15, optionVo.getOp_is_preview() );
				pstmt.setInt(16, optionVo.getOp_is_notice() );
				pstmt.setString(17, optionVo.getOp_cate() );
				pstmt.setInt(18, optionVo.getOp_img_width());
				pstmt.setString(19, optionVo.getOp_thumb());
				
				result = pstmt.executeUpdate();
				
			}
		}catch (Exception e) {
			System.out.println("옵션 입력 에러:"+e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	//옵션 정보 가져오기(idx값 기준)
	public BoardOptionVO getBoardOption(int op_idx) {
		ResultSet rs = null;
		BoardOptionVO optionVo = null;
		String sql = "SELECT * FROM " + TABLE + " WHERE op_idx = ?";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, op_idx);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				optionVo = new BoardOptionVO();
				optionVo.setOp_idx(rs.getInt("op_idx"));
				optionVo.setOp_name(rs.getString("op_name"));
				optionVo.setOp_table(rs.getString("op_table"));
				optionVo.setOp_skin(rs.getString("op_skin"));
				optionVo.setOp_adm_skin(rs.getString("op_adm_skin"));
				optionVo.setOp_is_secret(rs.getInt("op_is_secret"));
				optionVo.setOp_is_ip(rs.getInt("op_is_ip"));
				optionVo.setOp_is_sign(rs.getInt("op_is_sign"));
				optionVo.setOp_new_date(rs.getInt("op_new_date"));
				optionVo.setOp_regdate(rs.getTimestamp("op_regdate"));
				optionVo.setOp_view_level(rs.getInt("op_view_level"));
				optionVo.setOp_list_level(rs.getInt("op_list_level"));
				optionVo.setOp_write_level(rs.getInt("op_write_level"));
				optionVo.setOp_comment_level(rs.getInt("op_comment_level"));
				optionVo.setOp_reply_level(rs.getInt("op_reply_level"));
				optionVo.setOp_page_length(rs.getInt("op_page_length"));
				optionVo.setOp_is_preview(rs.getInt("op_is_preview"));
				optionVo.setOp_is_notice(rs.getInt("op_is_notice"));
				optionVo.setOp_cate(rs.getString("op_cate"));
				optionVo.setOp_img_width(rs.getInt("op_img_width"));
				optionVo.setOp_thumb(rs.getString("op_thumb"));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("옵션 게시물 가져오기 에러에러"+ e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		
		return optionVo;
	}

	//옵션 정보 가져오기(op_table값 기준)
	public BoardOptionVO getBoardOption(String op_table) {
		ResultSet rs = null;
		BoardOptionVO optionVo = null;
		String sql = "SELECT * FROM " + TABLE + " WHERE op_table = ?";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, op_table);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				optionVo = new BoardOptionVO();
				optionVo.setOp_idx(rs.getInt("op_idx"));
				optionVo.setOp_name(rs.getString("op_name"));
				optionVo.setOp_table(rs.getString("op_table"));
				optionVo.setOp_skin(rs.getString("op_skin"));
				optionVo.setOp_adm_skin(rs.getString("op_adm_skin"));
				optionVo.setOp_is_secret(rs.getInt("op_is_secret"));
				optionVo.setOp_is_ip(rs.getInt("op_is_ip"));
				optionVo.setOp_is_sign(rs.getInt("op_is_sign"));
				optionVo.setOp_new_date(rs.getInt("op_new_date"));
				optionVo.setOp_regdate(rs.getTimestamp("op_regdate"));
				optionVo.setOp_view_level(rs.getInt("op_view_level"));
				optionVo.setOp_list_level(rs.getInt("op_list_level"));
				optionVo.setOp_write_level(rs.getInt("op_write_level"));
				optionVo.setOp_comment_level(rs.getInt("op_comment_level"));
				optionVo.setOp_reply_level(rs.getInt("op_reply_level"));
				optionVo.setOp_page_length(rs.getInt("op_page_length"));
				optionVo.setOp_is_preview(rs.getInt("op_is_preview"));
				optionVo.setOp_is_notice(rs.getInt("op_is_notice"));
				optionVo.setOp_cate(rs.getString("op_cate"));
				optionVo.setOp_img_width(rs.getInt("op_img_width"));
				optionVo.setOp_thumb(rs.getString("op_thumb"));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("옵션 게시물 가져오기 에러에러"+ e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		
		return optionVo;
	}
	
	
	//업데이트
	public int updateBoardOption(BoardOptionVO optionVo) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "UPDATE "+ TABLE +" SET ";
			   sql+= " op_name = ?, ";
			   sql+= " op_skin = ? , ";
			   sql+= " op_adm_skin = ?, ";
			   sql+= " op_is_secret = ?, ";
			   sql+= " op_is_ip = ?, ";
			   sql+= " op_is_sign = ?, ";
			   sql+= " op_new_date = ?, ";
			   sql+= " op_view_level = ?, ";
			   sql+= " op_list_level = ?, ";
			   sql+= " op_write_level = ?, ";
			   sql+= " op_comment_level = ?, ";
			   sql+= " op_reply_level = ?, ";
			   sql+= " op_page_length = ?, ";
			   sql+= " op_is_preview = ?, ";
			   sql+= " op_is_notice = ?, ";
			   sql+= " op_cate = ?, ";
			   sql+= " op_img_width = ?, ";
			   sql+= " op_thumb = ? ";
			   sql+= " WHERE op_idx = ? ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, optionVo.getOp_name());
			pstmt.setString(2, optionVo.getOp_skin() );
			pstmt.setString(3, optionVo.getOp_adm_skin() );
			pstmt.setInt(4, optionVo.getOp_is_secret() );
			pstmt.setInt(5, optionVo.getOp_is_ip() );
			pstmt.setInt(6, optionVo.getOp_is_sign() );
			pstmt.setInt(7, optionVo.getOp_new_date() );
			pstmt.setInt(8, optionVo.getOp_view_level() );
			pstmt.setInt(9, optionVo.getOp_list_level() );
			pstmt.setInt(10, optionVo.getOp_write_level() );
			pstmt.setInt(11, optionVo.getOp_comment_level() );
			pstmt.setInt(12, optionVo.getOp_reply_level() );
			pstmt.setInt(13, optionVo.getOp_page_length() );
			pstmt.setInt(14, optionVo.getOp_is_preview() );
			pstmt.setInt(15, optionVo.getOp_is_notice() );
			pstmt.setString(16, optionVo.getOp_cate());
			pstmt.setInt(17, optionVo.getOp_img_width());
			pstmt.setString(18, optionVo.getOp_thumb());
			pstmt.setInt(19, optionVo.getOp_idx());

			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+"옵션 업데이트 에러");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		
		return result; 
	}
	
	//삭제
	public int deleteBoardOption(int op_idx) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM "+ TABLE + " WHERE op_idx = ?";
		int result = 0;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, op_idx);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+" 삭제 에러");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
}
