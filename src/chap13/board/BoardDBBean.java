package chap13.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BoardDBBean {
	
	private static BoardDBBean instance = new BoardDBBean();
	
	private BoardDBBean() { // 기본 생성자를 접근 금지
		
	}
	
	public static BoardDBBean getInstance() {
		return instance;
	}
	
	public String doA() {
		return "연결";
	}
	
	// DB에 전달 방법1 : dataBean에 감싸서 보내는 방법
	public void insertArticle(BoardDataBean dataBean) {
//		System.out.println(dataBean);
		Connection conn = null; // DB 연결하는 객체
		PreparedStatement pstmt = null; // sql 연결하는데 insert select update delete
		
		// jar파일(class파일 묶음) 관리하는 회사 - maven(웹) or gradle(안드로이드)
		
		/*
			1. jar 파일 연결되어 있는지 확인
			2. Connection 객체 연결 Drivermanager.getConnection()
			3. prepareStatement 생성 conn.prepareStatement();
			4. 실행
		 
		 */
		// mysql(3306), oracle(1521), sqlserver(1433)
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.200:1433;databasename=zu_20200611", "sa", "8765432!");
//			System.out.println("DB 연결 성공");
			pstmt = conn.prepareStatement("INSERT INTO [dbo].[board]" + 
					"           ([num]" + 
					"           ,[writer]" + 
					"           ,[email]" + 
					"           ,[subject]" + 
					"           ,[passwd]" + 
					"           ,[reg_date]" + 
					"           ,[readcount]" + 
					"           ,[ref]" + 
					"           ,[re_step]" + 
					"           ,[re_level]" + 
					"           ,[content]" + 
					"           ,[ip])" + 
					"     VALUES" + 
					"           ((select max(num)+1 as num from board)" + 
					"           , ?" + 
					"           , ?" + 
					"           , ?" + 
					"           , ?" + 
					"           ,getdate()" + 
					"           ,0" + 
					"           ,0" + 
					"           ,0" + 
					"           ,0" + 
					"           , ?" + 
					"           ,'192.168.0.198')");
			
			// 글쓰기 페이지에 입력된 값을 가져오기 위한 구문
			pstmt.setString(1, dataBean.getWriter());
			pstmt.setString(2, dataBean.getEmail());
			pstmt.setString(3, dataBean.getSubject());
			pstmt.setString(4, dataBean.getPasswd());
			pstmt.setString(5, dataBean.getContent());			
			
			pstmt.executeUpdate(); // 실행
			System.out.println("INSERT 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DB에 전달 방법2 : 각각 보내는 법
	public void insertDetailArticle(String name, String subject, String email, String content, String password) {
		System.out.println("subject = " + subject);
		System.out.println("name = " + name);
		System.out.println("email = " + email);
		System.out.println("content = " + content);
		System.out.println("password = " + password);
	}

}
