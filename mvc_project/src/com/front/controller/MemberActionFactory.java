package com.front.controller;

import javax.servlet.RequestDispatcher;

import com.front.controller.action.Action;
import com.front.member.controller.action.MemberIdCheckAction;
import com.front.member.controller.action.MemberJoinAction;
import com.front.member.controller.action.MemberJoinOkAction;
import com.front.member.controller.action.MemberLogOutAction;
import com.front.member.controller.action.MemberLoginAction;
import com.front.member.controller.action.MemberLoginOkAction;
import com.front.member.controller.action.MemberUpdateAction;
import com.front.member.controller.action.MemberUpdateOkAction;

public class MemberActionFactory {
	static private String path = "/pf";
	private static MemberActionFactory instance = new MemberActionFactory();
	private MemberActionFactory(){
		super();
	}
	public static MemberActionFactory getInstance(){
		return instance;
	}
	
	public Action getAction(String command){
		Action m_action = null;
		if( command.equals(path+"/member/login")){
			m_action = new MemberLoginAction();
		}
		if( command.equals(path+"/member/login_ok")){
			m_action = new MemberLoginOkAction();
		}
		if( command.equals(path+"/member/logout")){
			m_action = new MemberLogOutAction();
		}
		if( command.equals(path+"/member/join") || command.equals(path+"/member/join_form")){
			m_action = new MemberJoinAction();
		}
		if( command.equals(path+"/member/join_ok")){
			m_action = new MemberJoinOkAction();
		}
		if( command.equals(path+"/member/member_update")){
			m_action = new MemberUpdateAction();
		}
		if( command.equals(path+"/member/member_update_ok")){
			m_action = new MemberUpdateOkAction();
		}
		if( command.equals(path+"/member/member_id_chk")){
			m_action = new MemberIdCheckAction();
		}
		return m_action;
	}
}
