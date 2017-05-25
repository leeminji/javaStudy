package com.admin.option.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardOptionDao;
import com.dto.BoardOptionVO;
import com.util.PagingUtil;
import com.util.SearchUtil;

import javax.servlet.RequestDispatcher;

public class BoardOptionListAction implements BoardOptionAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<BoardOptionVO> list = null;
		
		String url = "/admin/setting/boardOption_list.jsp";
		String req_url = request.getRequestURL()+"?";
		
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
		
		//정렬 설정 ( 정렬 설정이 없을 경우 mb_idx(등록일) 순으로 자동 정렬 )
		request.setAttribute("sort_link", req_url);
		String sst = request.getParameter("sst");
		if( sst != null && !sst.equals("")){
			req_url += "&sst="+ sst;
		}
		
		BoardOptionDao otpDao = BoardOptionDao.getInstance();
		int totalCount = otpDao.getTotalCount(sfl, stx);
		if( totalCount > 0){
			firstRow = (page - 1) * otpDao.getCount_per_page()+1;
			endRow = firstRow + otpDao.getCount_per_page() ;
		}		
		
		System.out.println(firstRow+"/"+ endRow+"/"+ sfl+"/"+ stx+"/"+ sst);
		list = otpDao.getBoardOptionList(firstRow, endRow, sfl, stx, sst);
		
		//페이징처리
		PagingUtil paging = new PagingUtil();
		paging.setPageNo(page); //현재 페이지
		paging.setPageSize(otpDao.getCount_per_page()); //페이지당 게시물 수
		paging.setTotalCount(totalCount); //전체 게시물수
		paging.setUrl(req_url);
		
		//페이징을 request에 전달
		request.setAttribute("paging", paging);
		
		req_url += "&page="+page; //페이지 파라미터값 추가
		request.setAttribute("link", req_url); //링크값
		request.setAttribute("totalCount", totalCount); //전체 갯수
		request.setAttribute("boardOptionList", list); //리스트
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
