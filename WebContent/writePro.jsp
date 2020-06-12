<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="chap13.board.BoardDBBean" %>
<%@ page import="chap13.board.BoardDataBean" %>

<%
	// request.getParameter : 주소줄에 있는 데이터 값 가져오는 함수
	String sub = request.getParameter("subject");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String content = request.getParameter("content");
	String password = request.getParameter("password");
	
	System.out.println("subject = " + sub);
	System.out.println("name = " + name);
	System.out.println("email = " + email);
	System.out.println("content = " + content);
	System.out.println("password = " + password);
	
	BoardDBBean bdb = BoardDBBean.getInstance(); // 하나씩 넣는 방법1
	bdb.insertDetailArticle(name, sub, email, content, password);
	
	BoardDataBean dataBean = new BoardDataBean(); // dataBean에 감싸서 넣는 방법2
	dataBean.setContent(content);
	dataBean.setWriter(name);
	dataBean.setEmail(email);
	dataBean.setPasswd(password);
	dataBean.setSubject(sub);
	
	bdb.insertArticle(dataBean);
	

//	BoardDataBean dataBean = new BoardDataBean();
//	dataBean.setSubject(sub);
//	out.println("sub = " + sub);
//	out.println(dataBean); // dataBean.toString()에서 toString() 생략가능해짐 오버로딩함으로써

//	BoardDBBean bdb = new BoardDBBean();
//	bdb.insertArticle(dataBean);
	
// 	BoardDBBean bdb = BoardDBBean.getInstance();
// 	bdb.insertArticle(dataBean);
	

	out.println("writePro.jsp");
%>