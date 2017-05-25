package com.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.board.controller.action.BoardAction;
import com.admin.board.controller.action.BoardAdmCheckPassAction;
import com.admin.board.controller.action.BoardAdmDeleteAction;
import com.admin.board.controller.action.BoardAdmListAction;
import com.admin.board.controller.action.BoardAdmReplyAction;
import com.admin.board.controller.action.BoardAdmViewAction;
import com.admin.board.controller.action.BoardAdmWriteAction;
import com.admin.board.controller.action.BoardAdmWriteOkAction;

public class AdmBoardActionFactory {
	static public AdmBoardActionFactory instance = new AdmBoardActionFactory();
	private AdmBoardActionFactory(){}
	static public AdmBoardActionFactory getInstance(){
		return instance;
	}
	public BoardAction getBoardAction(String command){
		BoardAction action =  null;

		if( command.equals("/adm/board/list.do") ){
			action = new BoardAdmListAction();
		}
		if( command.equals("/adm/board/view.do") ){
			action = new BoardAdmViewAction();
		}
		if( command.equals("/adm/board/delete.do")){
			action = new BoardAdmDeleteAction();
		}		
		if( command.equals("/adm/board/write.do") || command.equals("/adm/board/update.do") ){
			action = new BoardAdmWriteAction();
		}
		if( command.equals("/adm/board/writeOk.do") ){
			action = new BoardAdmWriteOkAction();
		}
		if( command.equals("/adm/board/reply.do") ){
			action = new BoardAdmReplyAction();
		}
		if( command.equals("/adm/board/download.do")){
			action = new BoardAdmDownloadAction();
		}
		return action;
	}
}
