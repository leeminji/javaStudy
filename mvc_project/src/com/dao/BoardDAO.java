package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.BoardFileVO;
import com.dto.BoardVO;
import com.util.DBManager;

public class BoardDAO {
	public int count_per_page = 10;
	
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO(){}
	public static BoardDAO getInstance(){
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	

	public int getCount_per_page() {
		return count_per_page;
	}
	public void setCount_per_page(int count_per_page) {
		this.count_per_page = count_per_page;
	}
	
	public boolean createBoard(String tableName){
		boolean result = false;
		String sql = " CREATE TABLE BOARD_"+ tableName +" ( ";
			   sql+= " bo_idx number(10), ";
			   sql+= " bo_writer varchar(20), ";
			   sql+= " bo_member varchar2(10), ";
			   sql+= " bo_subject varchar2(1000), ";
			   sql+= " bo_content varchar2(4000), ";
			   sql+= " bo_is_secret number(1), ";
			   sql+= " bo_is_notice number(1), ";
			   sql+= " bo_file varchar2(20), ";
			   sql+= " bo_pass varchar2(300), ";
			   sql+= " bo_ref number(10), ";
			   sql+= " bo_level number(10), ";
			   sql+= " bo_step number(5), ";
			   sql+= " bo_hit number(10), ";
			   sql+= " bo_regdate date, ";
			   sql+= " bo_is_view number(1), ";
			   sql+= " bo_ip varchar2(20), ";
			   sql+= " bo_1 varchar2(200), ";
			   sql+= " bo_2 varchar2(200), ";
			   sql+= " bo_3 varchar2(200), ";
			   sql+= " bo_4 varchar2(200), ";
			   sql+= " bo_5 varchar2(200), ";
			   sql+= " bo_tag varchar2(500), ";
			   sql+= " primary key(bo_idx) ";
			   sql+= " ) ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			result = pstmt.execute();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+"게시판 테이블 생성 실패");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
	//게시판에 따른 시퀀스 생성
	public boolean createBoardSeq(String tableName){
		boolean result = false;
		String sql = " CREATE SEQUENCE BOARD_"+tableName+"_SEQ ";
			   sql+= " INCREMENT BY 1 ";
			   sql+= " START WITH 1 ";
			   sql+= " MAXVALUE 9999999999 ";
			   sql+= " NOCACHE ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			result = pstmt.execute();
		}catch(Exception e){
			System.out.println(e.getMessage()+"시퀀스 생성 성공");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
	//테이블 삭제
	public void dropBoard(String tableName){
		String sql = "DROP TABLE BOARD_"+tableName+" PURGE ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+" 테이블 삭제 실패");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
	}
	
	//시퀀스 삭제
	public void dropBoardSeq(String tableName){
		String sql = "DROP SEQUENCE BOARD_"+tableName+"_SEQ  ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+" 시퀀스 삭제 실패");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
	}
	
	
	//게시물 리스트
	public ArrayList<BoardVO> getBoardList(String tableName, int firstRow , int endRow, String sfl, String stx, String sst){
		ArrayList<BoardVO> list = new ArrayList<>();
		ResultSet rs = null;

		//페이징 처리
		String sql = " SELECT * FROM ";
			   sql += "    ( SELECT A.* , ROWNUM AS RNUM FROM ";
			   sql += "         ( SELECT * FROM BOARD_"+tableName;
			   //검색이 있을 경우 
			   if( sfl != null && !sfl.equals("")){
			   sql += "            WHERE "+ sfl + " like ? ";
			   }
			   //정렬이 있을 경우
			   if( sst != null && !sst.equals("")){
			   sql += "            ORDER BY "+sst+" DESC ";
			   //기본 정렬은 bo_num 순으로
			   }else{
			   sql += "            ORDER BY BO_REF DESC , BO_LEVEL ASC , BO_STEP ASC ";
			   }
			   sql += "   ) A WHERE ROWNUM < ? ) ";
			   sql += " WHERE RNUM >= ? ";
							   
		BoardVO bVo = null;
		BoardFileDAO bFileDAO = BoardFileDAO.getInstance();
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			//검색이 있을 경우
			if( sfl != null && !sfl.equals("")){
				pstmt.setString(1, "%"+stx+"%");
				pstmt.setInt(2, endRow);
				pstmt.setInt(3, firstRow);
			}else{
				pstmt.setInt(1, endRow);
				pstmt.setInt(2, firstRow);
			}
			rs = pstmt.executeQuery();
			int totalCount = getBoardTotalCount(tableName, sfl, stx);
			int i = 0;
			while(rs.next()){
				bVo = new BoardVO();
				bVo.setBo_idx(rs.getInt("bo_idx"));
				bVo.setBo_eq( totalCount-(firstRow-1)-i );
				bVo.setBo_writer(rs.getString("bo_writer"));
				bVo.setBo_file(rs.getString("bo_file"));
				bVo.setBo_member(rs.getString("bo_member"));
				bVo.setBo_subject(rs.getString("bo_subject"));
				bVo.setBo_ref(rs.getInt("bo_ref"));
				bVo.setBo_level(rs.getInt("bo_level"));
				bVo.setBo_step(rs.getInt("bo_step"));
				bVo.setBo_hit(rs.getInt("bo_hit"));
				bVo.setBo_regdate(rs.getTimestamp("bo_regdate"));
				bVo.setBo_is_view(rs.getInt("bo_is_view"));
				bVo.setBo_is_notice(rs.getInt("bo_is_notice"));
				bVo.setBo_is_secret(rs.getInt("bo_is_secret"));
				bVo.setBo_1(rs.getString("bo_1"));
				bVo.setBo_2(rs.getString("bo_2"));
				bVo.setBo_3(rs.getString("bo_3"));
				bVo.setBo_4(rs.getString("bo_4"));
				bVo.setBo_5(rs.getString("bo_5"));
				bVo.setBo_tag(rs.getString("bo_tag"));
				
				//썸네일 설정
				ArrayList<BoardFileVO> fileList = bFileDAO.getFileImgList(tableName, bVo.getBo_idx());
				if( fileList != null && fileList.size() > 0 ){
					BoardFileVO thumb_file = fileList.get(0);
					bVo.setBo_thumb(thumb_file.getBf_source());
				}
				list.add(bVo);
				i++;
			}
		}catch (Exception e) {
			System.out.println(tableName + "게시물 가져오기 에러 "+e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//게시물 리스트
	public ArrayList<BoardVO> getFrontBoardList(String tableName, int firstRow , int endRow, String sfl, String stx, String sst){
		ArrayList<BoardVO> list = new ArrayList<>();
		ResultSet rs = null;

		//페이징 처리
		String sql = " SELECT * FROM ";
			   sql += "    ( SELECT A.* , ROWNUM AS RNUM FROM ";
			   sql += "         ( SELECT * FROM BOARD_"+tableName;
			   sql += "            WHERE BO_IS_VIEW = 1 ";
			   //검색이 있을 경우 
			   if( sfl != null && !sfl.equals("")){
			   sql += "            AND "+ sfl + " like ? ";
			   }
			   //정렬이 있을 경우
			   if( sst != null && !sst.equals("")){
			   sql += "            ORDER BY "+sst+" DESC ";
			   //기본 정렬은 bo_num 순으로
			   }else{
			   sql += "            ORDER BY BO_REF DESC , BO_LEVEL ASC , BO_STEP ASC ";
			   }
			   sql += "   ) A WHERE ROWNUM < ? ) ";
			   sql += " WHERE RNUM >= ? ";
							   
		BoardVO bVo = null;
		BoardFileDAO bFileDAO = BoardFileDAO.getInstance();
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			//검색이 있을 경우
			if( sfl != null && !sfl.equals("")){
				pstmt.setString(1, "%"+stx+"%");
				pstmt.setInt(2, endRow);
				pstmt.setInt(3, firstRow);
			}else{
				pstmt.setInt(1, endRow);
				pstmt.setInt(2, firstRow);
			}
			rs = pstmt.executeQuery();
			int totalCount = getFrontBoardTotalCount(tableName, sfl, stx);
			int i = 0;
			while(rs.next()){
				bVo = new BoardVO();
				bVo.setBo_idx(rs.getInt("bo_idx"));
				bVo.setBo_eq( totalCount-(firstRow-1)-i );
				bVo.setBo_writer(rs.getString("bo_writer"));
				bVo.setBo_file(rs.getString("bo_file"));
				bVo.setBo_member(rs.getString("bo_member"));
				bVo.setBo_subject(rs.getString("bo_subject"));
				bVo.setBo_ref(rs.getInt("bo_ref"));
				bVo.setBo_level(rs.getInt("bo_level"));
				bVo.setBo_step(rs.getInt("bo_step"));
				bVo.setBo_hit(rs.getInt("bo_hit"));
				bVo.setBo_regdate(rs.getTimestamp("bo_regdate"));
				bVo.setBo_is_view(rs.getInt("bo_is_view"));
				bVo.setBo_is_notice(rs.getInt("bo_is_notice"));
				bVo.setBo_is_secret(rs.getInt("bo_is_secret"));
				bVo.setBo_1(rs.getString("bo_1"));
				bVo.setBo_2(rs.getString("bo_2"));
				bVo.setBo_3(rs.getString("bo_3"));
				bVo.setBo_4(rs.getString("bo_4"));
				bVo.setBo_5(rs.getString("bo_5"));
				bVo.setBo_tag(rs.getString("bo_tag"));
				
				//썸네일 설정
				ArrayList<BoardFileVO> fileList = bFileDAO.getFileImgList(tableName, bVo.getBo_idx());
				if( fileList != null && fileList.size() > 0 ){
					BoardFileVO thumb_file = fileList.get(0);
					bVo.setBo_thumb(thumb_file.getBf_source());
				}
				list.add(bVo);
				i++;
			}
		}catch (Exception e) {
			System.out.println(tableName + "게시물 가져오기 에러 "+e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//입력
	public int insertBoard(BoardVO bVo) {
		int result = 0;
		String sql = "INSERT INTO BOARD_"+bVo.getBo_table()+" VALUES (";
			   sql+= "BOARD_"+bVo.getBo_table()+"_SEQ.NEXTVAL ,";
			   sql+= " ?,"; //bo_writer
			   sql+= " ?,"; //bo_member
			   sql+= " ?,"; //bo_subject
			   sql+= " ?,"; //bo_content
			   sql+= " ?,"; //bo_is_secret
			   sql+= " ?,"; //bo_is_notice
			   sql+= " ?,"; //bo_file
			   sql+= " ? ,"; //bo_pass
			   if( bVo.getBo_ref() == 0 ){
			   sql+= " BOARD_"+bVo.getBo_table()+"_SEQ.CURRVAL ,"; //bo_ref
			   }else{
			   sql+= " ? , ";
			   }
			   sql+= " ? ,"; //bo_level
			   sql+= " ? ,"; //bo_step
			   sql+= " 0 ,"; //bo_hit
			   sql+= " sysdate ,"; //bo_regdate
			   sql+= " ? ,"; //bo_is_view
			   sql+= " '' ,"; //bo_ip
			   sql+= " ? ,"; //bo_1
			   sql+= " ? ,"; //bo_2
			   sql+= " ? ,"; //bo_3
			   sql+= " ? ,"; //bo_4
			   sql+= " ? ,"; //bo_5
			   sql+= " ? "; //bo_tag
			   sql+= ")";
		try{
			int q = 0;
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++q, bVo.getBo_writer());
			pstmt.setString(++q, bVo.getBo_member());
			pstmt.setString(++q, bVo.getBo_subject());
			pstmt.setString(++q, bVo.getBo_content());
			pstmt.setInt(++q, bVo.getBo_is_secret());
			pstmt.setInt(++q, bVo.getBo_is_notice());
			pstmt.setString(++q, 0+""); //file
			pstmt.setString(++q, bVo.getBo_pass());
			if( bVo.getBo_ref() != 0){
				pstmt.setInt(++q, bVo.getBo_ref());
			}
			pstmt.setInt(++q, bVo.getBo_level());
			pstmt.setInt(++q, bVo.getBo_step());
			pstmt.setInt(++q, bVo.getBo_is_view());
			pstmt.setString(++q, bVo.getBo_1());
			pstmt.setString(++q, bVo.getBo_2());
			pstmt.setString(++q, bVo.getBo_3());
			pstmt.setString(++q, bVo.getBo_4());
			pstmt.setString(++q, bVo.getBo_5());
			pstmt.setString(++q, bVo.getBo_tag());
			result = pstmt.executeUpdate();

		}catch(Exception e){
			System.out.println(bVo.getBo_table() + e.getMessage()+"게시물 입력실패");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}
	
	//시퀀스값 가져오기
	public int getLastSequence(String bo_table){
		int result = 0;
		bo_table = bo_table.toUpperCase();
		String sql = "SELECT last_number FROM SEQ WHERE sequence_name ='"+"BOARD_"+bo_table+"_SEQ'";
		ResultSet rs = null;

		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs =  pstmt.executeQuery();
			if( rs.next() ){
				result = rs.getInt(1)-1;
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+"시퀀스 실패");
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	
	//idx 기준으로 게시물 가져오기
	public BoardVO getBoard(String tableName, int bo_idx) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		BoardVO bVo = null;
		
		String sql = "SELECT * FROM BOARD_"+tableName+" WHERE bo_idx = ?";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bo_idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bVo = new BoardVO();
				bVo.setBo_idx(rs.getInt("bo_idx"));
				bVo.setBo_table(tableName);
				bVo.setBo_subject(rs.getString("bo_subject"));
				bVo.setBo_content(rs.getString("bo_content"));
				bVo.setBo_member(rs.getString("bo_member"));
				bVo.setBo_writer(rs.getString("bo_writer"));
				bVo.setBo_hit(rs.getShort("bo_hit"));
				bVo.setBo_file(rs.getString("bo_file"));
				bVo.setBo_regdate(rs.getTimestamp("bo_regdate"));
				bVo.setBo_is_view(rs.getInt("bo_is_view"));
				bVo.setBo_is_notice(rs.getInt("bo_is_notice"));
				bVo.setBo_is_secret(rs.getInt("bo_is_secret"));
				bVo.setBo_pass(rs.getString("bo_pass"));
				bVo.setBo_ref(rs.getInt("bo_ref"));
				bVo.setBo_step(rs.getInt("bo_step"));
				bVo.setBo_level(rs.getInt("bo_level"));
				bVo.setBo_1(rs.getString("bo_1"));
				bVo.setBo_2(rs.getString("bo_2"));
				bVo.setBo_3(rs.getString("bo_3"));
				bVo.setBo_4(rs.getString("bo_4"));
				bVo.setBo_5(rs.getString("bo_5"));
				bVo.setBo_tag(rs.getString("bo_tag"));
			}
		}catch(Exception e){
			System.out.println(tableName + e.getMessage()+"게시물 가져오기 실패");
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		
		return bVo;
	}
	
	//비밀번호 체크( 테이블, bo_idx , bo_pass ) 
	public int chkBoardPass(String tableName, int bo_idx, String bo_pass) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "SELECT count(*) as cnt FROM BOARD_"+tableName+" WHERE bo_idx = ? and bo_pass = ?";
		ResultSet rs = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bo_idx);
			pstmt.setString(2, bo_pass);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("cnt");
		}catch (Exception e) {
			System.out.println(tableName + e.getMessage()+" 비밀번호 체크 실패 ");
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//업데이트
	public int updateBoard(BoardVO bVo) {
		int result = 0;
		String sql = " UPDATE BOARD_"+ bVo.getBo_table() +" SET ";
			   sql+= " bo_writer = ?, ";
			   sql+= " bo_member = ?, ";
			   sql+= " bo_subject = ?, ";
			   sql+= " bo_content = ?, ";
			   sql+= " bo_pass = ?, ";
			   sql+= " bo_file = ?, ";
			   sql+= " bo_is_secret = ?,";
			   sql+= " bo_is_notice = ?, ";
			   sql+= " bo_is_view = ?, ";
			   sql+= " bo_1 = ?, ";
			   sql+= " bo_2 = ?, ";
			   sql+= " bo_3 = ?, ";
			   sql+= " bo_4 = ?, ";
			   sql+= " bo_5 = ?, ";
			   sql+= " bo_tag = ? ";
			   sql+= " WHERE bo_idx = ? ";
		try{
			int q = 0;
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++q, bVo.getBo_writer());
			pstmt.setString(++q, bVo.getBo_member());
			pstmt.setString(++q, bVo.getBo_subject());
			pstmt.setString(++q, bVo.getBo_content());
			pstmt.setString(++q, bVo.getBo_pass());
			pstmt.setString(++q, bVo.getBo_file());
			pstmt.setInt(++q, bVo.getBo_is_secret());
			pstmt.setInt(++q, bVo.getBo_is_notice());
			pstmt.setInt(++q, bVo.getBo_is_view());
			pstmt.setString(++q, bVo.getBo_1());
			pstmt.setString(++q, bVo.getBo_2());
			pstmt.setString(++q, bVo.getBo_3());
			pstmt.setString(++q, bVo.getBo_4());
			pstmt.setString(++q, bVo.getBo_5());
			pstmt.setString(++q, bVo.getBo_tag());
			pstmt.setInt(++q, bVo.getBo_idx());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+" 수정 실패 ");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}

	//파일갯수업데이트
	public int updateBoardFile(String tableName, int bo_idx, int file_count) {
		int result = 0;
		String sql = " UPDATE BOARD_"+ tableName +" SET ";
			   sql+= " bo_file = ? ";
			   sql+= " WHERE bo_idx = ? ";
		try{
			int q = 0;
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++q, file_count+"");
			pstmt.setInt(++q, bo_idx);
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+" 수정 실패 ");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
		return result;
	}	
	//게시물 삭제
	public void deleteBoard(String tableName, int bo_idx) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM BOARD_"+tableName+" WHERE bo_idx = ?";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bo_idx);
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+"삭제 에러");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
	}
	
	
	//게시물 step 얻기
	public int getBoardReplyStep(BoardVO bVo) {
		String sql = "SELECT max(bo_step) as max FROM BOARD_"+bVo.getBo_table()+" WHERE bo_ref = ? and bo_level = ?";
		ResultSet rs = null;
		int result = 0;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bVo.getBo_ref());
			pstmt.setInt(2, bVo.getBo_level());
			
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("max");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+" BO_STEP ERROR ");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//총게시물수 가져오기
	public int getFrontBoardTotalCount(String tableName,String sfl, String stx) {
		String sql =  "SELECT count(*) FROM BOARD_"+tableName+" ";
			   sql += " WHERE BO_IS_VIEW = 1 ";
			   if( sfl != null && !sfl.equals("")){
			   sql += " AND "+sfl+" like ? ";
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
			System.out.println("게시물 총 갯수 가져오기 에러 "+e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return reslut;
	}
	
	//총게시물수 가져오기
	public int getBoardTotalCount(String tableName,String sfl, String stx) {
		String sql =  "SELECT count(*) FROM BOARD_"+tableName+" ";
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
			System.out.println("게시물 총 갯수 가져오기 에러 "+e.getMessage());
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return reslut;
	}
	
	//게시판 조회수 늘리기
	public void setBoardCount(String tableName, int bo_idx){
		String sql = " UPDATE BOARD_"+tableName+" SET bo_hit = bo_hit + 1 WHERE bo_idx = ? ";
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bo_idx);
			
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage()+" 조회수 증가 에러 ");
		}finally{
			DBManager.close(conn, pstmt, null);
		}
	}	
}
