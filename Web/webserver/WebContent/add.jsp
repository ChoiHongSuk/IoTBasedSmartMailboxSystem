<%@ page language="java" contentType="text/html; charset = UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	String stack=null;
	if(request.getParameter("stack") != null){//stack부분에 null값이 아니면 값을 넣어줌
		stack=(String)request.getParameter("stack");	
	}
	UserDAO userDAO=new UserDAO();
	int result=userDAO.join(stack); //1, -1 반환
%>