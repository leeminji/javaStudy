package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;		
import javax.servlet.http.Cookie;
import com.dto.PopupVO;
import com.util.CookieUtil;
import com.util.DBManager;

public class PopupDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private final static String TABLE = "popup";
	private int count_per_page = 10;
	private static PopupDAO instacne = new PopupDAO();
	private PopupDAO(){}
	public static PopupDAO getInstance(){
		return instacne;
	}
	
	public int getCount_per_page() {
		return count_per_page;
	}
	public void setCount_per_page(int count_per_page) {
		this.count_per_page = count_per_page;
	}
	
	//PopupVO 리스트 가져오기
	public ArrayList<PopupVO> getPopupList(int firstRow , int endRow, String sfl, String stx, String sst) {
		ArrayList<PopupVO> list = new ArrayList<>();
		//페이징 처리
		String sql = " SELECT * FROM ";
			   sql += "    ( SELECT A.* , ROWNUM AS RNUM FROM ";
			   sql += "         ( SELECT * FROM POPUP ";
			   //검색이 있을 경우 
			   if( sfl != null && !sfl.equals("")){
			   sql += "            WHERE "+ sfl + " like ? ";
			   }
			   //정렬이 있을 경우
			   if( sst != null && !sst.equals("")){
			   sql += "            ORDER BY "+sst+" DESC ";
			   //기본 정렬은 bo_num 순으로
			   }else{
			   sql += "            ORDER BY PO_IDX DESC ";
			   }
			   sql += "   ) A WHERE ROWNUM < ? ) ";
			   sql += " WHERE RNUM >= ? ";

		PopupVO pVo = null;
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
				pVo = new PopupVO();
				pVo.setPo_eq( totalCount-(firstRow-1)-i );
				pVo.setPo_idx(rs.getInt("po_idx"));
				pVo.setPo_device(rs.getString("po_device"));
				pVo.setPo_begin_time(rs.getTimestamp("po_begin_time"));
				pVo.setPo_end_time(rs.getTimestamp("po_end_time"));
				pVo.setPo_disable_hours(rs.getInt("po_disable_hours"));
				pVo.setPo_left(rs.getInt("po_left"));
				pVo.setPo_top(rs.getInt("po_top"));
				pVo.setPo_height(rs.getInt("po_height"));
				pVo.setPo_width(rs.getInt("po_width"));
				pVo.setPo_subject(rs.getString("po_subject"));
				pVo.setPo_content(rs.getString("po_content"));
				pVo.setPo_state(rs.getTimestamp("po_begin_time"), rs.getTimestamp("po_end_time"));
				list.add(pVo);
				i++;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage()+"팝업 리스트 불러오기 에러");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//전체갯수
	public int getTotalCount(String sfl, String stx){
		int result = 0;
		ResultSet rs = null;
		String sql = " SELECT count(*) as cnt FROM POPUP ";
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
	// po_idx 로  popup 데이터 불러오기
	public PopupVO getPopup(int po_idx) {
		// TODO Auto-generated method stub
		PopupVO pVo = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+ TABLE+" WHERE po_idx = ?";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, po_idx);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				pVo = new PopupVO();
				pVo.setPo_idx(rs.getInt("po_idx"));
				pVo.setPo_device(rs.getString("po_device"));
				pVo.setPo_begin_time(rs.getTimestamp("po_begin_time"));
				pVo.setPo_end_time(rs.getTimestamp("po_end_time"));
				pVo.setPo_disable_hours(rs.getInt("po_disable_hours"));
				pVo.setPo_left(rs.getInt("po_left"));
				pVo.setPo_top(rs.getInt("po_top"));
				pVo.setPo_height(rs.getInt("po_height"));
				pVo.setPo_width(rs.getInt("po_width"));
				pVo.setPo_subject(rs.getString("po_subject"));
				pVo.setPo_content(rs.getString("po_content"));
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+" 팝업 idx 로 불러오기 ");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return pVo;
	}
	
	//팝업 정보 넣기
	public int insertPopup(PopupVO pVo) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO "+ TABLE + " VALUES( ";
			   sql+= " POPUP_SEQ.NEXTVAL, ";
			   sql+= " ?, "; //po_device
			   sql+= " ?, "; //po_begin_time
			   sql+= " ?, "; //po_end_time
			   sql+= " ?, "; //po_disable_hours
			   sql+= " ?, "; //po_left
			   sql+= " ?, "; //po_top
			   sql+= " ?, "; //po_height
			   sql+= " ?, "; //po_width
			   sql+= " ?, "; //po_subject
			   sql+= " ? "; //po_content
			   sql+= " ) ";
		int result = 0;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getPo_device());
			pstmt.setTimestamp(2, (Timestamp) pVo.getPo_begin_time());
			pstmt.setTimestamp(3, (Timestamp) pVo.getPo_end_time());
			pstmt.setInt(4, pVo.getPo_disable_hours());
			pstmt.setInt(5, pVo.getPo_left());
			pstmt.setInt(6, pVo.getPo_top());
			pstmt.setInt(7, pVo.getPo_height());
			pstmt.setInt(8, pVo.getPo_width());
			pstmt.setString(9, pVo.getPo_subject());
			pstmt.setString(10, pVo.getPo_content());
			
			result = pstmt.executeUpdate();
	
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
	
	//업데이트
	public int updatePopup(PopupVO pVo) {
		// TODO Auto-generated method stub
		String sql = " UPDATE popup SET ";
			   sql+= " po_device = ?, "; //po_device
			   sql+= " po_begin_time = ?, "; //po_begin_time
			   sql+= " po_end_time = ?, "; //po_end_time
			   sql+= " po_disable_hours = ?, "; //po_disable_hours
			   sql+= " po_left = ?, "; //po_left
			   sql+= " po_top = ?, "; //po_top
			   sql+= " po_height = ?, "; //po_height
			   sql+= " po_width = ?, "; //po_width
			   sql+= " po_subject = ?, "; //po_subject
			   sql+= " po_content = ? "; //po_content
			   sql+= " WHERE po_idx = ?";
		int result = 0;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getPo_device());
			pstmt.setTimestamp(2, (Timestamp) pVo.getPo_begin_time());
			pstmt.setTimestamp(3, (Timestamp) pVo.getPo_end_time());
			pstmt.setInt(4, pVo.getPo_disable_hours());
			pstmt.setInt(5, pVo.getPo_left());
			pstmt.setInt(6, pVo.getPo_top());
			pstmt.setInt(7, pVo.getPo_height());
			pstmt.setInt(8, pVo.getPo_width());
			pstmt.setString(9, pVo.getPo_subject());
			pstmt.setString(10, pVo.getPo_content());
			pstmt.setInt(11, pVo.getPo_idx());
			result = pstmt.executeUpdate();
	
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+"팝업 업데이트 오류");
		}finally {
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
	//팝업삭제
	public void deletePopup(int po_idx) {
		String sql = "DELETE FROM popup WHERE po_idx = ?";
		try{
			PopupVO pVo = getPopup(po_idx);
			if( pVo != null ){
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, po_idx);
				pstmt.executeUpdate();
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+" 팝업 idx 로 삭제 ");
		}finally {
			DBManager.close(conn, pstmt, null);
		}
	}
	
	public ArrayList<PopupVO> getCurrentPopup(Cookie [] cookies) {
		// TODO Auto-generated method stub
		ArrayList<PopupVO> list = new ArrayList<>();
		ResultSet rs = null;
		String sql = "SELECT * FROM POPUP WHERE sysdate between po_begin_time and po_end_time ORDER BY po_idx ASC ";
		PopupVO pVo = null;
		
		//쿠키 설정
		CookieUtil cUtil = CookieUtil.getInstance();
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while( rs.next() ){
				pVo = new PopupVO();
				
				pVo.setPo_idx(rs.getInt("po_idx"));
				pVo.setPo_device(rs.getString("po_device"));
				pVo.setPo_begin_time(rs.getTimestamp("po_begin_time"));
				pVo.setPo_end_time(rs.getTimestamp("po_end_time"));
				pVo.setPo_disable_hours(rs.getInt("po_disable_hours"));
				pVo.setPo_left(rs.getInt("po_left"));
				pVo.setPo_top(rs.getInt("po_top"));
				pVo.setPo_height(rs.getInt("po_height"));
				pVo.setPo_width(rs.getInt("po_width"));
				pVo.setPo_subject(rs.getString("po_subject"));
				pVo.setPo_content(rs.getString("po_content").replace("\r\n", "<br />"));
				
				//쿠키가 없으면 저장.
				if( cUtil.getCookie(cookies, "pop"+rs.getInt("po_idx")) == null ){
					list.add(pVo);
				}
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage()+"메인 팝업띄우기 에러");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
}
