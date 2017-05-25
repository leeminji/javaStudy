package com.admin.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardFileDAO;
import com.dao.BoardOptionDao;
import com.dto.BoardFileVO;
import com.dto.BoardOptionVO;
import com.dto.BoardVO;
import com.util.PagingUtil;
import com.util.SearchUtil;
import com.util.ThumbNail;

import javax.servlet.RequestDispatcher;

public class BoardAdmListAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//게시판 테이블 확인
		String bo_table = request.getParameter("bo_table");
		
		//게시판 정보 가져오기
		BoardOptionDao optionDao = BoardOptionDao.getInstance();
		BoardOptionVO optionVo = optionDao.getBoardOption(bo_table);
		
		//스킨설정
		String url = "/admin/skin/"+optionVo.getOp_adm_skin()+"/list.jsp";
		String req_url = request.getRequestURL()+"?bo_table="+bo_table;

		//페이지 받아오기 없으면 1로 설정
		String get_page = request.getParameter("page");
		int page = get_page == null ? 1 : Integer.parseInt(get_page); //페이지
		int firstRow = 0; //첫번째 행
		int endRow = 0; //마지막행
		
		//검색 설정
		String sfl = request.getParameter("sfl");
		String stx = request.getParameter("stx");
		SearchUtil search = new SearchUtil(sfl, stx);
	
		if( sfl != null && !sfl.equals("") ){
			req_url += search.toString();
		}
		
		//정렬 설정 ( 정렬 설정이 없을 경우 bo_regdate(등록일) 순으로 자동 정렬 )
		request.setAttribute("sort_link", req_url);
		String sst = request.getParameter("sst");
		if( sst != null && !sst.equals("")){
			req_url += "&sst="+ sst;
		}

		if( optionVo != null ){
			url = "/admin/skin/"+optionVo.getOp_adm_skin()+"/list.jsp";
			request.setAttribute("optionVo", optionVo); //게시판 정보
			BoardDAO bDao = BoardDAO.getInstance();

			//전체 게시물 갯수 및 가져올 리스트 설정
			bDao.setCount_per_page(optionVo.getOp_page_length()); //옵션에서 페이지 가져와서 설정
			
			int totalCount = bDao.getBoardTotalCount(bo_table, sfl, stx);
			if( totalCount > 0){
				firstRow = (page - 1) * bDao.getCount_per_page()+1;
				endRow = firstRow + bDao.getCount_per_page() ;
			}
			
			request.setAttribute("totalCount", totalCount); //전체 갯수
			ArrayList<BoardVO> boardList = bDao.getBoardList(bo_table,firstRow, endRow, sfl, stx, sst);
			request.setAttribute("boardList", boardList); //게시판 리스트
			
			//썸네일 크기 조정
			if( optionVo.getOp_thumb() != null ){
	
				//업로드
				String savePath = "/data/";
				String saveThumbPath = "/data/thumb_admin/";
				String uploadFilePath = request.getSession().getServletContext().getRealPath(savePath);
				String uploadFileThumbPath = request.getSession().getServletContext().getRealPath(saveThumbPath);
				request.setAttribute("uploadFilePath", uploadFilePath);
			
				String [] op_thumb = optionVo.getOp_thumb().split(";");
				int thumb_width = 250;
				int thumb_height = 250;
			
				for( BoardVO bVo : boardList){
					String thumb = bVo.getBo_thumb();
					if( thumb != null ){
					ThumbNail thumbNail = new ThumbNail(uploadFilePath, uploadFileThumbPath, thumb, thumb_width, thumb_height);
					thumbNail.createThumbnail();
					}
				}
			}
			
			//페이징처리
			PagingUtil paging = new PagingUtil();
			paging.setPageNo(page); //현재 페이지
			paging.setPageSize(bDao.getCount_per_page()); //페이지당 게시물 수
			paging.setTotalCount(totalCount); //전체 게시물수
			paging.setUrl(req_url);
			
			//페이징을 request에 전달
			request.setAttribute("paging", paging);
			req_url += "&page="+page; //페이지 파라미터값 추가
			request.setAttribute("link", req_url); //링크값
			
			//게시물 리스트를 boardList 에 넣음
			request.setAttribute("boardList", boardList);
		}
		
		//View 설정 ( skin )
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
